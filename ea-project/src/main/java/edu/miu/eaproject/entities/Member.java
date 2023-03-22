package edu.miu.eaproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_table")
@ToString
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


    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "member_membership_table")@
    @JoinColumn(name = "memberId")
    private List<Membership> memberships;
}
