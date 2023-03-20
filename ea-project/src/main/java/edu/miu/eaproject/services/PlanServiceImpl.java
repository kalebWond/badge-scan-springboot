package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Plan;
import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.repositories.PlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlanRepository planRepository;


    @Override
    public PlanDTO createPlan(PlanDTO planDTO) {
        try{
            Plan plan1= modelMapper.map(planDTO, Plan.class);
            plan1= planRepository.save(plan1);
            return modelMapper.map(plan1, PlanDTO.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    @Override
    public PlanDTO getPlanById(Long planId) {
        Plan plan= planRepository.findById(planId).get();
        return modelMapper.map(plan, PlanDTO.class);
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        List<Plan> planList= planRepository.findAll();

        return getDTOList(planList);
    }
    private List<PlanDTO> getDTOList(List<Plan> planList){
        List<PlanDTO> planDTOList = new ArrayList<>();
        for(Plan plan: planList){
            planDTOList.add(modelMapper.map(plan, PlanDTO.class));
        }
        return planDTOList;

    }

    @Override
    public PlanDTO updatePlan(Long planId, PlanDTO planDTO) {
        Plan plan1= planRepository.findById(planId).get();
        plan1 = modelMapper.map(planDTO, Plan.class);
        plan1.setId(planId);
        planRepository.save(plan1);

        return modelMapper.map(plan1, PlanDTO.class);
    }

    @Override
    public void deletePlan(Long planId) {
        Plan plan1= planRepository.findById(planId).get();
        planRepository.delete(plan1);
    }
}
