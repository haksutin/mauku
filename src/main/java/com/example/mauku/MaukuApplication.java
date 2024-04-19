package com.example.mauku;

import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mauku.domain.AppUser;
import com.example.mauku.domain.AppUserRepository;
import com.example.mauku.domain.Cat;
import com.example.mauku.domain.CatRepository;
import com.example.mauku.domain.Colour;
import com.example.mauku.domain.ColourRepository;
import com.example.mauku.domain.Location;
import com.example.mauku.domain.LocationRepository;

@SpringBootApplication
public class MaukuApplication {
	private static final Logger log = LoggerFactory.getLogger(MaukuApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MaukuApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CatRepository catRepository, ColourRepository colourRepository, LocationRepository locationRepository, AppUserRepository appUserRepository) {
		return (args) -> {

			Colour colour1 = new Colour("White");
            Colour colour2 = new Colour("Orange");
			Colour colour3 = new Colour("Black");
			Colour colour4 = new Colour("Grey");
			Colour colour5 = new Colour("Brown");

            colourRepository.save(colour1);
			colourRepository.save(colour2);
			colourRepository.save(colour3);
			colourRepository.save(colour4);
			colourRepository.save(colour5);

			Location location1 = new Location("Foster home");
			Location location2 = new Location("Shelter");
			Location location3 = new Location("Adopted");

			locationRepository.save(location1);
			locationRepository.save(location2);
			locationRepository.save(location3);

			Cat cat1 = new Cat("Fluff", 3.9, "2024-02-10", colour1, location1);
			Cat cat2 = new Cat("Tiger", 3.0, "2024-03-21", colour2, location3);
			Cat cat3 = new Cat("Batman", 2.8, "2024-03-25", colour3, location1);
			Cat cat4 = new Cat("Chibi", 2.5, "2024-04-07", colour5, location2);

			catRepository.save(cat1);
			catRepository.save(cat2);
			catRepository.save(cat3);
			catRepository.save(cat4);

			AppUser user1 = new AppUser("user", "$2a$10$4kWiiL7c188te3sqTZpKiOJMC8AJR2X4EAH8mKF5OOq/u1LOixz8e", "user@email.com", "USER" );
			AppUser user2 = new AppUser("admin", "$2a$10$jrCuPRY1/ZGHA9d5jzkN9.OOk.b4j.fMRc0u.uGK4WK8Bio.5r6qW", "admin@email.com", "ADMIN" );

			appUserRepository.save(user1);
			appUserRepository.save(user2);

			for (Cat cat : catRepository.findAll()) {
			log.info(cat.toString());
			}

		};
	}

}
