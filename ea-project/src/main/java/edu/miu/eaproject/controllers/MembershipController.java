package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.MembershipDTO;
import edu.miu.eaproject.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.resource.ResourceException;
import java.util.List;

@RestController
    @RequestMapping("/api/memberships")
    public class MembershipController {
        @Autowired
        private MembershipService membershipService;
        @PostMapping
        public ResponseEntity<MembershipDTO> addNewMembership(@RequestBody MembershipDTO membership) throws ResourceException {
            return ResponseEntity.status(HttpStatus.OK).body(membershipService.createNewMembership(membership));
        }
    @GetMapping("/members/{memberId}/memberships")
        public ResponseEntity<List<MembershipDTO>> getMembershipsByMemberId(@PathVariable long memberId) throws ResourceException {
        List<MembershipDTO> memberships = membershipService.getMembershipsByMemberId(memberId);
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }


    @GetMapping
        public ResponseEntity<List<MembershipDTO>> getAllMemberships(){
            return ResponseEntity.status(HttpStatus.OK).body(membershipService.getAllMemberships());
        }

        @GetMapping("/{id}")
        public ResponseEntity<MembershipDTO> getMembership(@PathVariable Long id) throws ResourceException {
            return ResponseEntity.status(HttpStatus.OK).body(membershipService.getMembership(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<MembershipDTO> updateMembership(@PathVariable Long id, @RequestBody MembershipDTO membership) throws ResourceException {
            return ResponseEntity.status(HttpStatus.OK).body(membershipService.updateMembership(id,membership));
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteMembership(@PathVariable Long id) throws ResourceException {
            membershipService.deleteMembership(id);
            return new ResponseEntity<String> ("Membership Successfully deleted",HttpStatus.OK);
        }
    }


