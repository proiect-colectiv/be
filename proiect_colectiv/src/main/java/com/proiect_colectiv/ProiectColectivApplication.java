package com.proiect_colectiv;

import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryImplementations.UserRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.IUserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProiectColectivApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectColectivApplication.class, args);
	}
}
