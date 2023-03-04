package com.exemple.template;

import com.exemple.template.entities.Category;
import com.exemple.template.entities.Curiculum;
import com.exemple.template.repository.CategoryRepository;
import com.exemple.template.repository.CuriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TemplateApplication {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CuriculumRepository curiculumRepository;

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CuriculumRepository curiculumRepository) {
		return args -> {
			Category category1 = new Category(null, "Informatique", "dev de plus de 5ans d'experience");
			Category category2 = new Category(null, "Reseau", "avoir une certification CNA");
			Category category3 = new Category(null, "philosophe", "etre une docteur");
			Category category4 = new Category(null, "makeing", "savoir dialogué");

			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);
			categoryRepository.save(category4);

			curiculumRepository.save(new Curiculum(null, "Ngor", "Seck", 40, "Fass", "email", "92956925", "Mster1", "Dev junior", category1));
			curiculumRepository.save(new Curiculum(null, "Ali", "Yaou", 19, "Pikine", "email", "92956925", "Mster2", "Dev simple", category2));
			curiculumRepository.save(new Curiculum(null, "Kadi", "Abdou", 20, "Medina", "email", "92956925", "Mster3", "Dev pro max", category3));
			curiculumRepository.save(new Curiculum(null, "Fatina", "Sarr", 60, "Sam", "email", "92956925", "Mster4", "Dev minu", category4));

			// Affichage de la liste des CV
			curiculumRepository.findAll().forEach(curriculum -> {
				System.out.println(curriculum.getNom() + " (" + curriculum.getCategory().getName() + ")");
			});
		};
	}
}
