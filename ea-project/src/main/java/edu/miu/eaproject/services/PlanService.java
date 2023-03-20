package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Plan;
import edu.miu.eaproject.entities.PlanDTO;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    PlanDTO createPlan(PlanDTO planDTO);
    PlanDTO getPlanById(Long planId);
    List<PlanDTO> getAllPlans();
    PlanDTO updatePlan(Long planId, PlanDTO planDTO);
    void deletePlan(Long planId);
}
