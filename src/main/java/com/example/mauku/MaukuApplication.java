package com.example.mauku;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mauku.domain.Cat;
import com.example.mauku.domain.CatRepository;
import com.example.mauku.domain.Colour;
import com.example.mauku.domain.ColourRepository;
import com.example.mauku.domain.Location;
import com.example.mauku.domain.LocationRepository;

@SpringBootApplication
public class MaukuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaukuApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CatRepository catRepository, ColourRepository colourRepository, LocationRepository locationRepository) {
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
			
		};
	}

}
