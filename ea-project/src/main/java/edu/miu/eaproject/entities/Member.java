package edu.miu.eaproject.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_table")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    private String password;
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Badge> badgeList;
    @OneToOne
    private Role role;


    @OneToMany
    @JoinTable(name = "member_membership_table")
    private List<Membership> memberships;
}
