package edu.miu.eaproject.controllers;

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

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionByID(@PathVariable Long transactionId){
        return new ResponseEntity<>(transactionService.getTransactionByID(transactionId), HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<?> getAllTransactions(){
        Transactions transactions=new Transactions();
        transactions.setTransactions(transactionService.getAllTransactions());

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transactionDTO){
        long badgeId= transactionDTO.getBadgeId();
        long locationId = transactionDTO.getLocationId();
        TransactionDTO transaction = transactionService.createTransaction(badgeId, locationId);
        return new ResponseEntity<TransactionDTO>(transaction, HttpStatus.OK);
    }
}
