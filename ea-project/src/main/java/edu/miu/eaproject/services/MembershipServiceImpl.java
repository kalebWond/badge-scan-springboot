package edu.miu.eaproject.services;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.MembershipDTO;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.Plan;
import edu.miu.eaproject.repositories.MemberRepository;
import edu.miu.eaproject.repositories.MembershipRepository;
import edu.miu.eaproject.repositories.PlanRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.resource.ResourceException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    PlanRepository planRepository;

    @Autowired
    private ModelMapper modelMapper;


// COMPLEX USECASE A
    @Override
    public List<MembershipDTO> getMembershipsByMemberId(long memberId) throws ResourceException {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResourceException("Member Not found"));
        List<Membership> memberships = member.getMemberships();
        return memberships.stream().map(membership -> modelMapper.map(membership, MembershipDTO.class)).collect(Collectors.toList());
    }


    @Override
    public MembershipDTO createNewMembership(MembershipDTO membershipDTO) throws ResourceException {
        Member member = memberRepository.findById(membershipDTO.getMemberId()).orElseThrow(()->new ResourceException("Member Not found"));
        Plan plan = planRepository.findById(membershipDTO.getPlanId()).orElseThrow(()->new ResourceException("Plan Not found"));
        Membership membership = modelMapper.map(membershipDTO, Membership.class);

        membership.setPlan(plan);
        membership.setMember(member);
//        List<Membership> memberships = member.getMemberships();
//        if(memberships == null) {
        List<Membership> memberships = new ArrayList<>();
//        }
//        memberships.add(membership);
//        member.setMemberships(memberships);
////        membershipRepository.save(memberships);
//        memberRepository.save(member);
       membership= membershipRepository.save(membership);
        return modelMapper.map(membership,MembershipDTO.class);
    }

    @Override
    public MembershipDTO updateMembership(long id, MembershipDTO membershipDTO) throws ResourceException {
        Membership existingMembership = membershipRepository.findById(id).orElseThrow(()->new ResourceException("Membership Not found"));
        Membership membership = modelMapper.map(membershipDTO,Membership.class);
        membership.setId(existingMembership.getId());
        Membership updatedMembership = membershipRepository.save(membership);
        return modelMapper.map(updatedMembership, MembershipDTO.class);
    }

    @Override
    public void deleteMembership(long id) throws ResourceException {
        Membership membership = membershipRepository.findById(id).orElseThrow(() -> new ResourceException("Membership Not found"));
        membershipRepository.delete(membership);
    }

    @Override
    public MembershipDTO getMembership(long id) throws ResourceException {
        return membershipRepository.findById(id).map(membership -> modelMapper.map(membership, MembershipDTO.class)).orElseThrow(() -> new ResourceException("Membership Not found"));
    }

    @Override
    public List<MembershipDTO> getAllMemberships() {
        List<Membership> memberships = membershipRepository.findAll();
        return memberships.stream().map(membership -> modelMapper.map(membership, MembershipDTO.class)).collect(Collectors.toList());
    }
}