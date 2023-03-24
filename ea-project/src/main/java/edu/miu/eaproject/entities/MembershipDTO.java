package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.MembershipType;
import edu.miu.eaproject.entities.enums.ResetTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private MembershipType membershipType;
    private int numberOfAllowances;
//   private Member member;
//   private PlanDTO plan;
    private long memberId;
    private long planId;
    private ResetTime resetTime;

}
