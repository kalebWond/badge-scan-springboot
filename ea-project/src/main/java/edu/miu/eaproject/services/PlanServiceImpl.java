package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.repositories.LocationRepository;
import edu.miu.eaproject.repositories.PlanRepository;
import edu.miu.eaproject.repositories.RoleRepositoy;
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

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoleRepositoy roleRepositoy;


    @Override
    public PlanResponseDTO createPlan(PlanDTO planDTO) {
        Location location=locationRepository.findById(planDTO.getLocationId()).get();
        Role role = roleRepositoy.findById(planDTO.getRoleId()).get();
            List<Role> roles = new ArrayList<>();
            roles.add(role);

            List<Location> locations=new ArrayList<>();
            locations.add(location);

            Plan plan1= modelMapper.map(planDTO, Plan.class);
            plan1.setLocations(locations);
            plan1.setRoles(roles);
            plan1= planRepository.save(plan1);
            return modelMapper.map(plan1, PlanResponseDTO.class);


    }

    @Override
    public PlanResponseDTO getPlanById(Long planId) {
        Plan plan= planRepository.findById(planId).get();

        return modelMapper.map(plan, PlanResponseDTO.class);
    }

    @Override
    public List<PlanResponseDTO> getAllPlans() {
        List<Plan> planList= planRepository.findAll();

        return getDTOList(planList);
    }
    private List<PlanResponseDTO> getDTOList(List<Plan> planList){
        List<PlanResponseDTO> planDTOList = new ArrayList<>();
        for(Plan plan: planList){
            planDTOList.add(modelMapper.map(plan, PlanResponseDTO.class));
        }
        return planDTOList;

    }

    @Override
    public PlanResponseDTO updatePlan(Long planId, PlanDTO planDTO) {
        Plan plan1= planRepository.findById(planId).get();
        plan1 = modelMapper.map(planDTO, Plan.class);
        plan1.setId(planId);
        planRepository.save(plan1);

        return modelMapper.map(plan1, PlanResponseDTO.class);
    }

    @Override
    public void deletePlan(Long planId) {
        Plan plan1= planRepository.findById(planId).get();
        planRepository.delete(plan1);
    }
}
