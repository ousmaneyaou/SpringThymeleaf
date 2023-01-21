package com.exemple.template;

import com.exemple.template.entities.Professeur;
import com.exemple.template.repository.ProfesseurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}
	//@Bean //demarrage automatique
	CommandLineRunner commandLineRunner(ProfesseurRepository patientRepository){
		return args -> {
			patientRepository.save(
					new Professeur(null, "Ngor", "Seck", "email", new Date(),  1));
			patientRepository.save(
					new Professeur(null, "Ousmane", "Yaou", "email", new Date(),  1));
			patientRepository.save(
					new Professeur(null, "M2gl", "Isi", "email", new Date(),  1));
			patientRepository.save(
					new Professeur(null, "Kenda", "Sall", "email", new Date(),  1));
			// affichage de la liste des patients
			patientRepository.findAll().forEach(patient -> {
				System.out.println(patient.getNom());
			});
		};
	}

}
