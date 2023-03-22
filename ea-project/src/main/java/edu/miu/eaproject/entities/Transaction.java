package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_table")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;//USED FOR COMPLEX USE CASE
    private boolean allowed; //USED FOR COMPLEX USE CASE
    @ManyToOne
    private Member member;// member USED FOR COMPLEX USE CASE
    @ManyToOne
    private Plan plan; // PLAN USED FOR COMPLEX USE CASE
    private LocalDateTime transactionDateTime;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    @ManyToOne
    private Membership membership;
    @ManyToOne
    @JoinColumn(name="badgeId")
    private Badge badge;

    public Transaction(LocalDateTime transactionDateTime, TransactionType transactionType, Location location, Membership membership, Badge badge) {
        this.transactionDateTime = transactionDateTime;
        this.transactionType = transactionType;
        this.location = location;
        this.membership = membership;
        this.badge = badge;
    }

    public Transaction(LocalDateTime timestamp, boolean allowed, TransactionType transactionType, Member member, Plan plan, Location location) {
        this.timestamp = timestamp;
        this.allowed = allowed;
        this.transactionType = transactionType;
        this.member = member;
        this.plan = plan;
        this.location = location;
    }

}
