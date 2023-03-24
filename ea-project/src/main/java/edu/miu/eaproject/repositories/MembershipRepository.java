package edu.miu.eaproject.repositories;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.entities.enums.ResetTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership,Long> {
//    @Query("select ms from Member m JOIN m.memberships ms join ms.plan p join p.locations l where l.id=:locationId and m.id=:memberId")
    @Query("select ms from Membership ms join ms.plan p join p.locations l where l.id=:locationId and ms.member.id=:memberId ")
    public List<Membership> findMembershipByMemberIdAndLocationId(long memberId, long locationId);
    List<Membership> findByMemberIdAndMembershipType(long memberId, MembershipType membershipType);

    @Modifying
    @Query("update Membership m set m.currentUsageCount = 0 where m.resetTime =:resetTime")
    public  void resetCurrentUsageCountByResetTime(ResetTime resetTime);

}
