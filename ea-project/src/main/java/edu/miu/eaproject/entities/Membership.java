package edu.miu.eaproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.entities.enums.ResetTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership_table")
@ToString
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private MembershipType membershipType;

    private int numberOfAllowances;
    @Column(columnDefinition = "integer default 0")
    private int currentUsageCount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResetTime resetTime;

//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "membership")
//  //  @JoinColumn(name = "membership_id")
//    @JsonIgnore
//    private List<Transaction> transactionList;

    @ManyToOne
    @JoinColumn(name = "memberId")
    @JsonIgnore
    private Member member;

@JsonIgnore
    @ManyToOne
    private Plan plan;

}
