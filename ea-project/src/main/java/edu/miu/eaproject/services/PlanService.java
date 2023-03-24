package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Location;
import edu.miu.eaproject.entities.Plan;
import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.entities.PlanResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    PlanResponseDTO createPlan(PlanDTO planDTO);
    PlanResponseDTO getPlanById(Long planId);
    List<PlanResponseDTO> getAllPlans();
    PlanResponseDTO updatePlan(Long planId, PlanDTO planDTO);
    void deletePlan(Long planId);

    PlanDTO addLocationToPlan(long planId, long locationId);
}
