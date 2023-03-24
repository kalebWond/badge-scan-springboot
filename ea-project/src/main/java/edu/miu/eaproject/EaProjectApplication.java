package edu.miu.eaproject;

import edu.miu.eaproject.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EaProjectApplication implements CommandLineRunner {

	@Autowired
	TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(EaProjectApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//transactionService.createTransaction(1, 1);
	}
}
