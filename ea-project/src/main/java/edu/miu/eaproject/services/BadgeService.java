package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.BadgeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BadgeService {
    BadgeDTO createBadge(long memberId);

    BadgeDTO readBadge(Long id);

    BadgeDTO updateBadge(Badge badge);

    void deleteBadge(Badge badge);

    List<BadgeDTO> getAllBadges();
    BadgeDTO getActiveBadgeForMember(long memberId);

}
