package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Transaction;

import java.util.List;

public interface TransactionService {
    public List<Transaction> findTransactionsByMemberId(Long memberId);
    public Transaction createTransaction(long badgeId, long locationId);
}
