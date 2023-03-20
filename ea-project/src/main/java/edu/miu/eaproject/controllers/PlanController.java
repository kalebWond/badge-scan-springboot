package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;


    @GetMapping
    public ResponseEntity<Plans> getAllPlans(){
        Plans plans= new Plans();
        plans.setPlanDTOList(planService.getAllPlans());

        return new ResponseEntity(plans, HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanDTO> getPlanById(@PathVariable Long planId){
        return new ResponseEntity<>(planService.getPlanById(planId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanDTO planDTO){
        return new ResponseEntity<>(planService.createPlan(planDTO), HttpStatus.OK);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanDTO> updatePlan(@PathVariable Long planId, @RequestBody PlanDTO planDTO){
        return new ResponseEntity<>(planService.updatePlan(planId, planDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Long planId){
        planService.deletePlan(planId);

    }
}
