package edu.miu.eaproject.services;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.UserDetailsSecurity;
import edu.miu.eaproject.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByEmailIgnoreCase(username);
        var userDetails = new UserDetailsSecurity(member);
        return userDetails;
    }
}
