package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.LocationDTO;
import edu.miu.eaproject.entities.MembershipDTO;
import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.entities.TransactionDTO;
import edu.miu.eaproject.services.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checker")
public class DoorCheckerController {
    @Autowired
    private CheckerService checkerService;

    // a. Return a list of memberships for a checker
    @GetMapping("/memberships/{checkerMemberId}")
    public List<MembershipDTO> getCheckerMemberships(@PathVariable Long checkerMemberId) {
        return checkerService.getCheckerMemberships(checkerMemberId);
    }

    // c. Present the list of plans
    @GetMapping("/plans")
    public List<PlanDTO> getAllPlans() {
        return checkerService.getAllPlans();
    }

    // d. Present the list of locations for the selected plan
    @GetMapping("/plans/{planId}/locations")
    public List<LocationDTO> getLocationsByPlanId(@PathVariable Long planId) {
        return checkerService.getLocationsByPlanId(planId);
    }

}
