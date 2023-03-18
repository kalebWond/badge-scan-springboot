package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public void save(@RequestBody Member member){
        memberService.save(member);
    }
}
