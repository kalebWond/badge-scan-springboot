package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.LocationType;
import edu.miu.eaproject.entities.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransactionDTO {
	private long id;
	private LocalDateTime transactionDateTime;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	private LocationDTO locationDTO;
	private MembershipDTO membershipDTO;
}
