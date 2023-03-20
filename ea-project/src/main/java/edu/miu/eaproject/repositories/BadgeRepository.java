package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Long> {{
}
