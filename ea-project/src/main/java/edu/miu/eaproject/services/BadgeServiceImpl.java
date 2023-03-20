package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.BadgeDTO;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.MemberDTO;
import edu.miu.eaproject.entities.enums.BadgeStatus;
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
        Member member=memberRepository.findById(memberId).get();
        badge.setMember(member);
        badge.setStatus(BadgeStatus.ACTIVE);
        Badge badge1 =  badgeRepository.save(badge);
        return getDto(badge1);
    }


    @Override
    public BadgeDTO readBadge(Long id) {
        Optional<Badge> badge = badgeRepository.findById(id);
        if(badge.isPresent()){
            return getDto(badge.get());
        } else {
            return null;
        }
    }

    @Override
    public BadgeDTO updateBadge(Badge badge) {
        badgeRepository.save(badge);
        BadgeDTO badgeDTO = getDto(badge);
        return badgeDTO;
    }

    @Override
    public void deleteBadge(Badge badge) {
        badgeRepository.delete(badge);
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

    public List<BadgeDTO> getAllBadges() {
        List<Badge> badgeList= badgeRepository.findAll();
        return getDTOList(badgeList);
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
