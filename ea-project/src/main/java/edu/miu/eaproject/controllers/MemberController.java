package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.services.BadgeService;
import edu.miu.eaproject.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private BadgeService badgeService;

    @GetMapping("{memberId}/badges/active")
    public ResponseEntity<BadgeDTO> getActiveBadgesForMember(@PathVariable long memberId) {
        BadgeDTO activeBadge = badgeService.getActiveBadgeForMember(memberId);

        return new ResponseEntity<>(activeBadge, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody MemberDTO memberDTO){
        MemberResponseDTO member=memberService.save(memberDTO);
        return new ResponseEntity<MemberResponseDTO>(member, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllMembers(){
        List<MemberResponseDTO> members=memberService.getAllMembers();
        return new ResponseEntity<List<MemberResponseDTO>>(members, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody MemberDTO memberDTO){
         memberService.updateMemberbyId(id,memberDTO);
         return new ResponseEntity<String>("Member successfully updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        memberService.deleteById(id);
        return new ResponseEntity<String>("Member successfylly deleted",HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable long id){
        return new ResponseEntity<MemberResponseDTO>(memberService.getMemberById(id),HttpStatus.OK);

    }

    @GetMapping("/{memberId}/transactions")
    public ResponseEntity<?> getTrasactionsByMember(@PathVariable long memberId){
        return new ResponseEntity<List<Transaction>>(memberService.getTransactionsByMember(memberId),HttpStatus.OK);
    }

    @GetMapping("/{memberId}/memberships")
    public ResponseEntity<?> getMembershipsByMember(@PathVariable long memberId){
        return new ResponseEntity<List<Membership>>(memberService.getMembershipsByMember(memberId),HttpStatus.OK);
    }


    @GetMapping("/{memberId}/plans")
    public ResponseEntity<?> getPlansByMember(@PathVariable long memberId){
        return new ResponseEntity<List<Plan>>(memberService.getPlansByMember(memberId),HttpStatus.OK);
    }
    @GetMapping("/{memberId}/badges")
    public ResponseEntity<?> getBadgesByMember(@PathVariable long memberId){
        return new ResponseEntity<List<Badge>>(memberService.getBadgesByMember(memberId),HttpStatus.OK);
    }


}
