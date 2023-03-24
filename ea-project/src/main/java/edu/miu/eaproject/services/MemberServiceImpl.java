package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.exceptions.NotFoundException;
import edu.miu.eaproject.repositories.BadgeRepository;
import edu.miu.eaproject.repositories.MemberRepository;
import edu.miu.eaproject.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public MemberDTO save(MemberDTO memberDTO) {
//        Member member=mapper.map(memberDTO,Member.class);
//        Role role= roleRepository.findRoleByRole(memberDTO.getRole());
//        member.setRole(role);
//        memberRepository.save(member);
//        return memberDTO;
//    }

    @Override
    public MemberResponseDTO save(MemberDTO memberDTO) {
        Member member = mapper.map(memberDTO, Member.class);
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        Role role = roleRepository.findRoleByRole(memberDTO.getRole());
        member.setRole(role);
        member = memberRepository.save(member);
        return mapper.map(member, MemberResponseDTO.class);
    }

    @Override
    public List<MemberResponseDTO> getAllMembers() {
        return getDtoList(memberRepository.findAll());
    }

    @Override
    public MemberResponseDTO getMemberById(long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()){
            throw new NotFoundException("E415", "Member not found with id: " + id);
        }
        return getDto(member.get());
    }

    @Override
    public MemberResponseDTO updateMemberbyId(long id,MemberDTO memberDTO) {
        memberRepository.findById(id).orElseThrow(()->new NotFoundException("E416", "Member not found"));
        Member member=mapper.map(memberDTO,Member.class);
        member.setId(id);
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member.setRole(roleRepository.findRoleByRole(memberDTO.getRole()));
        member=memberRepository.save(member);
        return mapper.map(member,MemberResponseDTO.class);
    }

    @Override
    public void deleteById(long id) {
        memberRepository.findById(id).orElseThrow(()->new NotFoundException("E417", "Member not found"));
        memberRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getTransactionsByMember(long memberId) {

     return memberRepository.getTransactionByMember(memberId);

    }

    @Override
    public List<Membership> getMembershipsByMember(long memberId) {
        return memberRepository.getAllMemberships(memberId);
    }

    @Override
    public List<Plan> getPlansByMember(long memberId) {
        return memberRepository.getPlansByMember(memberId);
    }

    @Override
    public List<Badge> getBadgesByMember(long memberId) {
        return badgeRepository.findByMemberId(memberId);

       // return memberRepository.getAllBadges(memberId);
    }

    private List<MemberResponseDTO> getDtoList(List<Member> members) {
        return members.stream().map(this::getDto).toList();
    }
    private MemberResponseDTO getDto(Member job) {
        return mapper.map(job, MemberResponseDTO.class);
    }


}
