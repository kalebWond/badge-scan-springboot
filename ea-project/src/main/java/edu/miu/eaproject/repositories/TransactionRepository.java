package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public List<Transaction> findByMembershipId(Long id);
    @Query("select ms from Member m JOIN m.memberships ms join ms.plan p join p.locations l where l.id=:locationId and m.id=:memberId")
    public Membership findMembershipByMemberIdAndLocationId(long memberId, long locationId);

//    @Query("Select t from Member m JOIN m.badgeList bd JOIN bd.transactionList t where m.id=:memberId ")

}
