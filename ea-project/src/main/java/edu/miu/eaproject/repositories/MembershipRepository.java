package edu.miu.eaproject.repositories;
import edu.miu.eaproject.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership,Long> {
    @Query("select ms from Member m JOIN m.memberships ms join ms.plan p join p.locations l where l.id=:locationId and m.id=:memberId")
    public Membership findMembershipByMemberIdAndLocationId(long memberId, long locationId);
}
