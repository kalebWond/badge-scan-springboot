package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Long> {
    public Badge findByIdAndStatus(long id, BadgeStatus status);
    @Query("select b from Badge b where b.member.id=:id")
    public List<Badge> findByMemberId(Long id);
}
