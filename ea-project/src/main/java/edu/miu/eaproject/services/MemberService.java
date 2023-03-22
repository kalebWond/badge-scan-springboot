package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;

import java.util.List;

public interface MemberService {
    public MemberResponseDTO save(MemberDTO memberDTO);
    public List<MemberResponseDTO> getAllMembers();
    public MemberResponseDTO getMemberById(long id);
    public MemberResponseDTO updateMemberbyId(long id,MemberDTO memberDTO);

    public void deleteById(long id);

    public List<Transaction> getTransactionsByMember(long memberId);

    public List<Membership> getMembershipsByMember(long memberId);
    List<Plan> getPlansByMember(long memberId);
    List<Badge> getBadgesByMember(long memberId);
}
