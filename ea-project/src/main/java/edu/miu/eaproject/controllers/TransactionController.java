package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.MemberDTO;
import edu.miu.eaproject.entities.Transaction;
import edu.miu.eaproject.entities.TransactionDTO;
import edu.miu.eaproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transactionDTO){
        long badgeId= transactionDTO.getBadgeId();
        long locationId = transactionDTO.getLocationId();
        TransactionDTO transaction = transactionService.createTransaction(badgeId, locationId);
        return new ResponseEntity<TransactionDTO>(transaction, HttpStatus.OK);
    }
}
