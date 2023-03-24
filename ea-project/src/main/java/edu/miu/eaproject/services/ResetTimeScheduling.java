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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ResetTimeScheduling {

    @Autowired
    MembershipRepository membershipRepository;

    //schedule for weekly resetTime, at midnight every monday
    @Scheduled(cron = "0 0 0 ? * MON")
    @Transactional
    public void resetNumberOfCurrentUsageForWeekly() {
        membershipRepository.resetCurrentUsageCountByResetTime(ResetTime.WEEKLY);
    }

    //schedule for monthly resetTime, at midnight 1st of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetNumberOfCurrentUsageForMonthly() {
        membershipRepository.resetCurrentUsageCountByResetTime(ResetTime.MONTHLY);
    }

}