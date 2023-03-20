package edu.miu.eaproject.services;
import edu.miu.eaproject.entities.MembershipDTO;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.repositories.MembershipRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.resource.ResourceException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MembershipDTO createNewMembership(MembershipDTO membershipDTO) {
        Membership membership = modelMapper.map(membershipDTO, Membership.class);
        Membership newMembership = membershipRepository.save(membership);
        return modelMapper.map(newMembership,MembershipDTO.class);
    }

    @Override
    public MembershipDTO updateMembership(long id, MembershipDTO membershipDTO) throws ResourceException {
        Membership oldMembership = membershipRepository.findById(id).orElseThrow(()->new ResourceException("Membership Not found"));
        Membership membership = modelMapper.map(membershipDTO,Membership.class);
        membership.setId(oldMembership.getId());
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