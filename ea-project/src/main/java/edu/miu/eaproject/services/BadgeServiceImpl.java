package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import edu.miu.eaproject.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Override
    public Badge createBadge() {
        Badge badge = new Badge();
        badge.setStatus(BadgeStatus.ACTIVE);
        return badgeRepository.save(badge);
    }

    @Override
    public Badge readBadge(Long id) {
        Optional<Badge> badge = badgeRepository.findById(id);
        return badge.isEmpty() ?  null : badge.get();
    }

    @Override
    public Badge updateBadge(Badge badge) {
        badgeRepository.save(badge);
        return badge;
    }

    @Override
    public void deleteBadge(Badge badge) {
        badgeRepository.delete(badge);
    }

    public void deactivateBadge(Long id) {
        Badge badge = readBadge(id);
        badge.setStatus(BadgeStatus.INACTIVE);
        updateBadge(badge);
    }

    public void activateBadge(Long id) {
        Badge badge = readBadge(id);
        badge.setStatus(BadgeStatus.ACTIVE);
        updateBadge(badge);
    }
}
