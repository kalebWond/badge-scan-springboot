package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Transaction;
import edu.miu.eaproject.entities.TransactionDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface TransactionService {
    public List<TransactionDTO> getAllTransactions();
    public TransactionDTO getTransactionByID(Long id);
    public List<Transaction> findTransactionsByMemberId(Long memberId);
    public TransactionDTO createTransaction(long badgeId, long locationId);
    }
