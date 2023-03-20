package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.BadgeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "badge_table")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BadgeStatus status;
    @OneToMany
    @JoinColumn(name = "badgeId")
    private List<Transaction> transactionList;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;


}
