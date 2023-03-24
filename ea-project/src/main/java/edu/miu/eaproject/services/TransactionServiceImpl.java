package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.entities.enums.RoleType;
import edu.miu.eaproject.entities.enums.TransactionType;
import edu.miu.eaproject.exceptions.*;
import edu.miu.eaproject.repositories.BadgeRepository;
import edu.miu.eaproject.repositories.MembershipRepository;
import edu.miu.eaproject.repositories.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    MembershipRepository membershipRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public TransactionDTO getTransactionByID(Long id){
        Transaction transaction = transactionRepository.findById(id).get();
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Override
    public List<TransactionDTO> getAllTransactions(){
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList.stream().map(transaction -> modelMapper.map(transaction,TransactionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findTransactionsByMemberId(Long memberId) {
        return null;
    }
    @Override
    public TransactionDTO createTransaction(long badgeId, long locationId) {
        Badge badge = null;
        Membership membership = null;
        Location location = null;
        Plan plan = null;

        try {
            badge = badgeRepository.findByIdAndStatus(badgeId, BadgeStatus.ACTIVE);

            if (badge == null) {
                throw new BadgeNotAcceptedException("E410", "Active Badge doesn't exist");
            }

            Member member = badge.getMember();
            List<Membership> membershipList = membershipRepository.findMembershipByMemberIdAndLocationId(member.getId(), locationId);
            if(membershipList == null || membershipList.isEmpty()) {
                throw new BadgeNotAcceptedException("E411", "Membership for the location is not found!");
            }

            membership = membershipList.get(0);

            if (!checkMembershipExpiration(membership)) {
                throw new BadgeNotAcceptedException("E412", "Membership has expired!");
            }

            if (membership.getMembershipType().equals(MembershipType.LIMITED) && !checkAllowanceUsage(membership)) {
                throw new BadgeNotAcceptedException("E413", "Membership allowance used up!");
            }

            plan = membership.getPlan();
            for (Location lc : plan.getLocations()) {
                if (lc.getId().equals(locationId)) {
                    location = lc;
                }
            }

            // add check if location is null

            TimeSlot openHour = getCurrentTimeslot(location);
            if (openHour == null) {
                throw new BadgeNotAcceptedException("E413", "Location is not open at this hour");
            }

            if (membership.getMembershipType().equals(MembershipType.LIMITED) && member.getRole().equals(RoleType.STUDENT) && checkMultipleEntranceFromTransaction(badgeId, locationId, openHour)) {
                throw new BadgeNotAcceptedException("E414", "Member has already entered location at this timeslot");
            }
        } catch (BadgeSystemException e) {
            saveTransaction(location, badge, TransactionType.DECLINED, membership, plan);
            throw e;
        }

        Transaction transaction = saveTransaction(location, badge, TransactionType.ALLOWED, membership, plan);
        membership.setCurrentUsageCount(membership.getCurrentUsageCount()+1);
        membershipRepository.save(membership);
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    private boolean checkMembershipExpiration(Membership membership) {
        LocalDate today = LocalDate.now();
        if (membership.getStartDate().isAfter(today) || membership.getEndDate().isBefore(today)) {
            return false;
        }
        return true;
    }

    private boolean checkAllowanceUsage(Membership membership) {
        if(membership.getCurrentUsageCount() < membership.getNumberOfAllowances()) {
            return true;
        }
        return false;
    }

    private TimeSlot getCurrentTimeslot(Location location) {
        List<TimeSlot> timeslots = location.getTimeSlots();
        LocalDateTime todayNow = LocalDateTime.now();
        for (TimeSlot timeslot : timeslots) {
            if (timeslot.getDayOfWeek().equals(todayNow.getDayOfWeek())) {
                LocalTime now = todayNow.toLocalTime();
                Boolean nowInBetween = now.isAfter(timeslot.getStartTime()) && now.isBefore(timeslot.getEndTime());
                if(nowInBetween) {
                    return timeslot;
                }
            }
        }
        return null;
    }

    private boolean checkMultipleEntranceFromTransaction(Long badgeId, long locationId, TimeSlot openHour) {
        LocalDateTime startDateTime = openHour.getStartTime().atDate(LocalDate.now());
        LocalDateTime endDateTime = openHour.getEndTime().atDate(LocalDate.now());
        int count = transactionRepository.countByBadgeIdAndLocationIdInTimeSlot(badgeId, locationId, startDateTime, endDateTime);
        if(count > 0) {
            return true;
        }
        return false;
    }

    private Transaction saveTransaction(Location location, Badge badge, TransactionType transactionType, Membership membership, Plan plan) {
        Transaction transaction = new Transaction(LocalDateTime.now(), transactionType, location, membership, badge, plan);
        transactionRepository.save(transaction);
        return transaction;
    }
}