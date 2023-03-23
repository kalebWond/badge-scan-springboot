package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.entities.PlanResponseDTO;
import edu.miu.eaproject.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
    @Autowired
    private PlanService planService;


    @GetMapping
    public ResponseEntity<PlanResponseDTO> getAllPlans(){
        Plans plans= new Plans();
        plans.setPlanDTOList(planService.getAllPlans());

        return new ResponseEntity(plans, HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseDTO> getPlanById(@PathVariable Long planId){
        return new ResponseEntity<>(planService.getPlanById(planId), HttpStatus.OK);
    }
    @GetMapping("/{planId}/locations")
    public ResponseEntity<PlanResponseDTO> getLocationsByPlanId(@PathVariable Long planId){
        return new ResponseEntity<>(planService.getPlanById(planId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody PlanDTO planDTO){
        return new ResponseEntity<>(planService.createPlan(planDTO), HttpStatus.OK);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponseDTO> updatePlan(@PathVariable Long planId, @RequestBody PlanDTO planDTO){
        return new ResponseEntity<>(planService.updatePlan(planId, planDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Long planId){
        planService.deletePlan(planId);
        return new ResponseEntity<>("Plan deleted Successfully",HttpStatus.OK);

    }

    @PostMapping("/{planId}/locations/{locationId}")
    public ResponseEntity<PlanDTO> addLocationToPlan(@PathVariable Long planId, @PathVariable Long locationId){
        return new ResponseEntity<>(planService.addLocationToPlan(planId, locationId), HttpStatus.OK);
    }
}
