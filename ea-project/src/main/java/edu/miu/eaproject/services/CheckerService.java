package edu.miu.eaproject.services;
import edu.miu.eaproject.entities.LocationDTO;
import edu.miu.eaproject.entities.MembershipDTO;
import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.entities.TransactionDTO;

import java.util.List;

public interface CheckerService {
    List<MembershipDTO> getCheckerMemberships(Long checkerMemberId);
    List<PlanDTO> getAllPlans();
    List<LocationDTO> getLocationsByPlanId(Long planId);
//    TransactionDTO handleBadgeScan(Long memberId, Long checkerId, Long planId, Long locationId, boolean isAllowed);
//    TransactionDTO createTransaction(Long memberId, Long planId, Long locationId, boolean isAllowed);
//    List<TransactionDTO> getMemberTransactions(Long memberId);
}

