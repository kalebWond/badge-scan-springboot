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

    // USE CASE 2 IS IN BADGE SERVICE

    // USE CASE A IS IN MEMBERSHIP SERVICE

//USE CASE B
    @Override
    public List<MembershipDTO> getCheckerMemberships(Long checkerMemberId) {
        List<Membership> memberships = membershipRepository.findByMemberIdAndMembershipType(checkerMemberId, MembershipType.CHECKER);
        return memberships.stream().map(membership -> modelMapper.map(membership, MembershipDTO.class)).collect(Collectors.toList());
    }
    //USE CASE C we update
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
              // usecase E // we update
//    @Override
//    public TransactionDTO handleBadgeScan(Long memberId, Long checkerId, Long planId, Long locationId, boolean isAllowed) {
//        if (isAllowed) {
//            return createTransaction(memberId, planId, locationId, true);
//        } else {
//            return createTransaction(memberId, planId, locationId, false);
//        }
//    }
//// THIS IS USE CASE F AND KALEBO WILL HANDEL THE LACKING ATTERBUIT IN THE TRANSACTION  ENTITY
//    @Override
//    public TransactionDTO createTransaction(Long memberId, Long planId, Long locationId, boolean isAllowed) {
//        Transaction transaction = new Transaction();
//        transaction.setMember(new Member(memberId));
//        //KALEB WILL HANDLE THIS
//        transaction.setPlan(new Plan(planId));
//        transaction.setLocation(new Location(locationId));
//        transaction.setTimestamp(LocalDateTime.now());
//        transaction.setAllowed(isAllowed);
//        Transaction savedTransaction = transactionRepository.save(transaction);
//        return modelMapper.map(savedTransaction, TransactionDTO.class);
//    }
//   // usecase g
//    @Override
//    public List<TransactionDTO> getMemberTransactions(Long memberId) {
//        List<Transaction> transactions = transactionRepository.findByMember_Id(memberId);
//        return transactions.stream().map(t -> modelMapper.map(t, TransactionDTO.class)).collect(Collectors.toList());
//    }
}

