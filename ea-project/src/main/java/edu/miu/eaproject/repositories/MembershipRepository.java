package edu.miu.eaproject.repositories;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.enums.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership,Long> {
    List<Membership> findByMember_IdAndMembershipType(Long memberId, MembershipType membershipType);
}
