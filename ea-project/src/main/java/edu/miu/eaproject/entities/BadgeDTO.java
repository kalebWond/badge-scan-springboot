package edu.miu.eaproject.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.eaproject.entities.enums.BadgeStatus;
import lombok.Data;

import java.util.List;


@Data
public class BadgeDTO {

    private Long id;
    private BadgeStatus status;

    private MemberResponseDTO member;


}
