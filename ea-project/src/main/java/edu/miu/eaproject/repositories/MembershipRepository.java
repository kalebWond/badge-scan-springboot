package edu.miu.eaproject.repositories;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.entities.enums.ResetTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership,Long> {
    @Query("select ms from Member m JOIN m.memberships ms join ms.plan p join p.locations l where l.id=:locationId and m.id=:memberId")
    public Membership findMembershipByMemberIdAndLocationId(long memberId, long locationId);

     List<Membership> findByMember_IdAndMembershipType(Long memberId, MembershipType membershipType);

     @Query(value = "update membership_table set currentUsageCount = 0 where resetTime=:resetTime", nativeQuery = true)
    public  void resetCurrentUsageCountByResetTime(ResetTime resetTime);

}
