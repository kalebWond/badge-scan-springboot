package edu.miu.eaproject.repositories;

import edu.miu.eaproject.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
