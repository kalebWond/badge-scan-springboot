package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.BadgeStatus;
import lombok.Data;

import java.util.List;


@Data
public class BadgeDTO {

    private Long id;
    private BadgeStatus status;

    private List<Transaction> transactionList;


}
