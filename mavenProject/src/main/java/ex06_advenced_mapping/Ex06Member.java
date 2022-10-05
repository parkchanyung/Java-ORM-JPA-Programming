package ex06_advenced_mapping;

import ex05_associations_mapping.Ex05Locker;
import ex05_associations_mapping.Ex05MemberProduct;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ex06Member extends Ex06BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 다대일 양방향
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Ex06Team team;

    // 일대일 양방향
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Ex05Locker locker;

    // 다대다 양방향
    @OneToMany(mappedBy = "member")
    private List<Ex05MemberProduct> mamberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Ex06Team getTeam() {
        return team;
    }

    public void setTeam(Ex06Team team) {
        // 기존 팀과 관계를 제거
        if(this.team != null){
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }
}