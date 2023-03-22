package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.TransactionDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
@Data
public class Transactions {
   private Collection<TransactionDTO> transactions = new ArrayList<>();
}
