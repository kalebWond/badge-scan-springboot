package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.exceptions.NotFoundException;
import edu.miu.eaproject.repositories.LocationRepository;
import edu.miu.eaproject.repositories.MembershipRepository;
import edu.miu.eaproject.repositories.PlanRepository;
import edu.miu.eaproject.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MembershipRepository membershipRepository;


    @Override
    public PlanResponseDTO createPlan(PlanDTO planDTO) {
        Location location=locationRepository.findById(planDTO.getLocationId()).get();
        Role role = roleRepository.findById(planDTO.getRoleId()).get();
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
        Optional<Plan> plan= planRepository.findById(planId);
        if(plan.isEmpty()){
            throw new NotFoundException("E422","Plan not found with id: " + planId);
        }
        return modelMapper.map(plan.get(), PlanResponseDTO.class);
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
        Optional<Plan> planOptional = planRepository.findById(planId);
        if(planOptional.isEmpty()){
            throw new NotFoundException("E423","Plan not found with id: " + planId);
        }
        Plan plan1 = planOptional.get();
        plan1 = modelMapper.map(planDTO, Plan.class);
        plan1.setId(planId);
        planRepository.save(plan1);
        return modelMapper.map(plan1, PlanResponseDTO.class);
    }

    @Override
    public void deletePlan(Long planId) {
        Optional<Plan> plan = planRepository.findById(planId);
        if(plan.isEmpty()){
            throw new NotFoundException("E424","Plan not found with id: " + planId);
        }
        planRepository.delete(plan.get());
    }

    @Override
    public PlanDTO addLocationToPlan(long planId, long locationId) {
        Plan plan1= planRepository.findById(planId).get();
        Location location=locationRepository.findById(locationId).get();
        plan1.addLocation(location);
        planRepository.save(plan1);
        return modelMapper.map(plan1, PlanDTO.class);
    }
}
