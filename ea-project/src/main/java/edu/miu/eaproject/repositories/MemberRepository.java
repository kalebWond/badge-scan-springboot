package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

//    @Query("Select t from Member m JOIN m.badgeList bd JOIN bd.transactionList t where m.id=:memberId ")
    @Query("select t from Transaction t join t.badge bd join bd.member m where m.id=:memberId")
    List<Transaction> getTransactionByMember(long memberId);
    @Query("Select bd from Member m JOIN m.badgeList  bd where m.id=:memberId ")
    List<Badge> getAllBadges(long memberId);

    @Query("Select ms from Member m JOIN m.memberships ms where m.id=:memberId ")
    List<Membership> getAllMemberships(long memberId);
    @Query("Select ms.plan from Member m JOIN m.memberships ms where m.id=:memberId ")
    List<Plan> getPlansByMember(long memberId);

    Member findMemberByEmailIgnoreCase(String email);
}
