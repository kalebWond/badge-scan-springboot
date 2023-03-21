package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Badge;
import edu.miu.eaproject.entities.Member;
import edu.miu.eaproject.entities.Membership;
import edu.miu.eaproject.entities.Transaction;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import edu.miu.eaproject.repositories.BadgeRepository;
import edu.miu.eaproject.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TransactionServiceImpl implements TransactionService{

    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactionsByMemberId(Long memberId) {
        return null;
    }


    // badge id find badge
    // check validity of badge

    // get member and check membership validity for that member from the badge
    // if membership is limited, check the numberofAllowances and current count
    // via membership, get plan and match with locationid from request
    // check time slots with current time for that location
    // add transaction
    // increment current count on membership entity
    // consider using @Transactional for the above 2
    @Override
    @Transactional
    public Transaction createTransaction(long badgeId, long locationId) {
        Badge badge = badgeRepository.findByIdAndStatus(badgeId, BadgeStatus.ACTIVE);
        System.out.println(badge);

        if(badge == null) {
            throw new RuntimeException("Active Badge doesn't exist");
        }
//        List<Membership> memberships = badge.getMember().getMemberships();
//        Membership membership;
//        for (Membership memship:
//             memberships) {
//
//        }
        Member member = badge.getMember();
        System.out.println(member);
        Membership membership = transactionRepository.findMembershipByMemberIdAndLocationId(member.getId(), locationId);
        System.out.println(membership);
        return null;
    }

    private Membership findMembershipByLocationId(long memberId, long locationId) {

        return null;
    }
}
