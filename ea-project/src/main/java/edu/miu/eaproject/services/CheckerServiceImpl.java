package edu.miu.eaproject.services;
import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckerServiceImpl implements CheckerService {

    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream().map(p -> modelMapper.map(p, PlanDTO.class)).collect(Collectors.toList());
    }
    //USE CASE D
    @Override
    public List<LocationDTO> getLocationsByPlanId(Long planId) {
        List<Location> locations = locationRepository.findByPlans_Id(planId);
        return locations.stream().map(location -> modelMapper.map(location, LocationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MembershipDTO> getCheckerMemberships(Long checkerMemberId) {
        List<Membership> memberships = membershipRepository.findByMemberIdAndMembershipType(checkerMemberId, MembershipType.CHECKER);
        return memberships.stream().map(membership -> modelMapper.map(membership, MembershipDTO.class)).collect(Collectors.toList());
    }

}

