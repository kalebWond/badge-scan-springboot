package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Badge;

public interface BadgeService {
    Badge createBadge();

    Badge readBadge(Long id);

    Badge updateBadge(Badge badge);

    void deleteBadge(Badge badge);
}
