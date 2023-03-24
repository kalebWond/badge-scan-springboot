package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.BadgeDTO;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import edu.miu.eaproject.exceptions.NotFoundException;
import edu.miu.eaproject.repositories.BadgeRepository;
import edu.miu.eaproject.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

// complex usecase 2
    @Override
    public BadgeDTO getActiveBadgeForMember(long memberId) {
        List<Badge> badges = memberRepository.getAllBadges(memberId);
        BadgeDTO activeBadge = null;

        for (Badge badge : badges) {
            if (badge.getStatus().equals(BadgeStatus.ACTIVE)) {
                activeBadge=getDto(badge);
            }
        }
        return activeBadge;
    }

    @Override
    public BadgeDTO createBadge(long memberId) {
        List<Badge> badges = memberRepository.getAllBadges(memberId);
        for(Badge badge : badges){
            if(badge.getStatus().equals(BadgeStatus.ACTIVE)){
                badge.setStatus(BadgeStatus.INACTIVE);
                badgeRepository.save(badge);
            }
        }
        Badge badge = new Badge();
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isEmpty()){
            throw new NotFoundException("E414", "Member not found with id: " + memberId);
        }
        badge.setMember(member.get());
        badge.setStatus(BadgeStatus.ACTIVE);
        Badge badgeCreated =  badgeRepository.save(badge);
        return getDto(badgeCreated);
    }


    @Override
    public BadgeDTO readBadge(Long id) {
        Optional<Badge> badge = badgeRepository.findById(id);
        if(badge.isEmpty()){
            throw new NotFoundException("E415","Badge not found with id: " + id);
        }
        return getDto(badge.get());
    }

    @Override
    public BadgeDTO updateBadge(Badge badge) {
        badgeRepository.save(badge);
        BadgeDTO badgeDTO = getDto(badge);
        return badgeDTO;
    }

    @Override
    public void deleteBadgeById(Long id) {
        badgeRepository.findById(id).orElseThrow(()->new NotFoundException("E421","Badge not found"));
        badgeRepository.deleteById(id);
    }



    public void deactivateBadge(Long id) {
        BadgeDTO badgeDTO = readBadge(id);
        Badge badge = removeDto(badgeDTO);
        badge.setStatus(BadgeStatus.INACTIVE);
        updateBadge(badge);
    }

    public void activateBadge(Long id) {
        BadgeDTO badgeDTO = readBadge(id);
        Badge badge = removeDto(badgeDTO);
        badge.setStatus(BadgeStatus.ACTIVE);
        updateBadge(badge);
    }
    @Override
    public List<BadgeDTO> getAllBadges() {
            return getDTOList(badgeRepository.findAll());
    }

    private List<BadgeDTO> getDTOList(List<Badge> badgelist){
        List<BadgeDTO> badgeDTOList = new ArrayList<>();
        for(Badge badge: badgelist){
            badgeDTOList.add(modelMapper.map(badge, BadgeDTO.class));
        }
        return badgeDTOList;
    }

    private BadgeDTO getDto(Badge badge) {
        return modelMapper.map(badge, BadgeDTO.class);
    }

    private Badge removeDto(BadgeDTO badgeDTO) {
        return modelMapper.map(badgeDTO, Badge.class);
    }
}
