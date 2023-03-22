package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.MemberDTO;
import edu.miu.eaproject.entities.MembershipDTO;

import javax.resource.ResourceException;
import java.util.List;

public interface MembershipService {
    public MembershipDTO createNewMembership(MembershipDTO membershipDTO) throws ResourceException;
    public MembershipDTO updateMembership(long id, MembershipDTO membershipDTO) throws ResourceException;
    public void deleteMembership(long id) throws ResourceException;
    public MembershipDTO getMembership(long id) throws ResourceException;
    public List<MembershipDTO> getAllMemberships();
    public List<MembershipDTO> getMembershipsByMemberId(long memberId) throws ResourceException;
}
