package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;

import java.util.List;

public interface MemberService {
    public MemberDTO save(MemberDTO memberDTO);
    public List<MemberDTO> getAllMembers();
    public MemberDTO getMemberById(long id);
    public MemberDTO updateMemberbyId(long id,MemberDTO memberDTO);

    public void deleteById(long id);

    public List<Transaction> getTransactionsByMember(long memberId);

    public List<Membership> getMembershipsByMember(long memberId);
    List<Plan> getPlansByMember(long memberId);
    List<Badge> getBadgesByMember(long memberId);






}
