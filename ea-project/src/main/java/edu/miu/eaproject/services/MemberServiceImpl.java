package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
}
