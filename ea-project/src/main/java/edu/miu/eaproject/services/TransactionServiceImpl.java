package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.*;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.entities.enums.RoleType;
import edu.miu.eaproject.entities.enums.TransactionType;
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
        Badge badge = badgeRepository.findByIdAndStatus(badgeId, BadgeStatus.ACTIVE);
        System.out.println(badge);

        if(badge == null) {
            saveTransaction(null, badge, TransactionType.DECLINED, null, null);
            throw new RuntimeException("Active Badge doesn't exist");
        }
        Member member = badge.getMember();
//        System.out.println(member);
        Membership membership = membershipRepository.findMembershipByMemberIdAndLocationId(member.getId(), locationId).get(0);
//        System.out.println(membership);
        if(!checkMembershipExpiration(membership)) {
            saveTransaction(null, badge, TransactionType.DECLINED, membership, null);
            throw new RuntimeException("Membership has expired");
        }
        if(membership.getMembershipType().equals(MembershipType.LIMITED) && !checkAllowanceUsage(membership)) {
            saveTransaction(null, badge, TransactionType.DECLINED, membership, null);
            throw new RuntimeException("Membership allowance used up");
        }
        Plan plan = membership.getPlan();
        Location location = null;
        for (Location lc : plan.getLocations()) {
            if (lc.getId().equals(locationId)) {
                location = lc;
            }
        }
        //
        // add check if location is null
        //
//        System.out.println(location);
        TimeSlot openHour = getCurrentTimeslot(location);
//        System.out.println(openHour);
        if(openHour == null) {
            saveTransaction(location, badge, TransactionType.DECLINED, membership, plan);
            throw new RuntimeException("Location is not open at this hour");
        }
        if(membership.getMembershipType().equals(MembershipType.LIMITED) && member.getRole().equals(RoleType.STUDENT) && checkMultipleEntranceFromTransaction(badgeId, locationId, openHour)) {
            saveTransaction(location, badge, TransactionType.DECLINED, membership, plan);
            throw new RuntimeException("Member has already entered location at this timeslot");
        }
        Transaction transaction = saveTransaction(location, badge, TransactionType.ALLOWED, membership, plan);
        membership.setCurrentUsageCount(membership.getCurrentUsageCount()+1);
        membershipRepository.save(membership);
//        System.out.println(transaction);
//        System.out.println(membership);
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

//if(!(membership.getStartDate().isBefore(todayNow) && membership.getEndDate().isAfter(todayNow))) {
//        return false;
//        }