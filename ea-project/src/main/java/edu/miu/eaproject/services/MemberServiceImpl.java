package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.repositories.MemberRepository;
import edu.miu.eaproject.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        Member member=mapper.map(memberDTO,Member.class);
        Role role= roleRepository.findRoleByRole(memberDTO.getRole());
        member.setRole(role);
        memberRepository.save(member);
        return memberDTO;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return getDtoList(memberRepository.findAll());
    }

    @Override
    public MemberDTO getMemberById(long id) {
        return getDto(memberRepository.findById(id).get());
    }

    @Override
    public MemberDTO updateMemberbyId(long id,MemberDTO memberDTO) {
        memberRepository.findById(id).orElseThrow(()->new RuntimeException("Member not found"));
        Member member=mapper.map(memberDTO,Member.class);
        member.setId(id);
        memberRepository.save(member);
        return memberDTO;
    }

    @Override
    public void deleteById(long id) {
        memberRepository.findById(id).orElseThrow(()->new RuntimeException("Member not found"));
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
        return memberRepository.getAllBadges(memberId);
    }


    private List<MemberDTO> getDtoList(List<Member> members) {
        return members.stream().map(this::getDto).toList();
    }
    private MemberDTO getDto(Member job) {
        return mapper.map(job, MemberDTO.class);
    }

}
