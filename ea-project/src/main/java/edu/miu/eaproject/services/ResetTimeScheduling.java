package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Location;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.enums.ResetTime;
import edu.miu.eaproject.repositories.MemberRepository;
import edu.miu.eaproject.repositories.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResetTimeScheduling {

    @Autowired
    MembershipRepository membershipRepository;

    //schedule for weekly resetTime

    @Scheduled(cron = "0 0 1 * ?") //other option: 0 0 1 * *
    public void resetNumberOfCurrentUsageForWeekly() {
        membershipRepository.resetCurrentUsageCountByResetTime(ResetTime.WEEKLY);
    }

    //schedule for monthly resetTime, at midnight 1st of every month
    @Scheduled(cron = "0 0 1 * ?") //other option: 0 0 1 * *
    public void resetNumberOfCurrentUsageForMonthly() {
        membershipRepository.resetCurrentUsageCountByResetTime(ResetTime.MONTHLY);
    }



//    @Scheduled(cron = "0 0 ? * MON")
//    public void resetNumberOfCurrentUsageForStudent() {
//        //only student,  membership for dinning hall
//        //membership type = limited
//        //reset time = weekly
//        //roletype = student
//        List<Member> members = memberRepository.getAllMembersWithStudentRole("STUDENT");
//        for (Member member : members) {
//            List<Membership> memberships = member.getMemberships();
//            for (Membership membership : memberships) {
//                List<Location> locations = membership.getPlan().getLocations();
//                for (Location location : locations) {
//                    if (location.getLocationType().equals("DINING_HALL")) {
//                        membership.setCurrentUsageCount(0);
//                    }
//                }
//            }
//        }
//    }

    //    @Scheduled(cron = "0 0 ? * MON") //other option: 0 0 * * 1
//    public void resetNumberOfCurrentUsageForWeekly() {
//        List<Member> members = memberRepository.findAll();
//        for (Member member : members) {
//            List<Membership> memberships = member.getMemberships();
//            for (Membership membership : memberships) {
//                if (membership.getResetTime().equals("WEEKLY")) {
//                    membership.setCurrentUsageCount(0);
//                }
//            }
//        }
//    }


}