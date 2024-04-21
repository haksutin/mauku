package com.example.mauku;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mauku.domain.AppUser;
import com.example.mauku.domain.AppUserRepository;
import com.example.mauku.domain.Cat;
import com.example.mauku.domain.CatRepository;
import com.example.mauku.domain.Colour;
import com.example.mauku.domain.ColourRepository;
import com.example.mauku.domain.Location;
import com.example.mauku.domain.LocationRepository;

@SpringBootTest
public class MaukuTest {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private ColourRepository colourRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Test
    public void testCatRepository() {
        catRepository.save(new Cat("Test", 2.0, LocalDate.of(2024, 1, 1), null, null));

        List<Cat> foundCat = catRepository.findByName("Test");
        assertThat(foundCat).isNotNull();

        catRepository.delete(foundCat.get(0));
        assertThat(catRepository.findByName("Test")).isNotNull();
    }

    @Test
    public void testColourRepository() {
        colourRepository.save(new Colour("Test"));

        List<Colour> foundColour = colourRepository.findByName("Test");
        assertThat(foundColour).isNotNull();

        colourRepository.delete(foundColour.get(0));
        assertThat(colourRepository.findByName("Test")).isEmpty();
    }

    @Test
    public void testLocationRepository() {
        locationRepository.save(new Location("Test"));

        List<Location> foundLocation = locationRepository.findByName("Test");
        assertThat(foundLocation).isNotNull();

        locationRepository.delete(foundLocation.get(0));
        assertThat(locationRepository.findByName("Test")).isEmpty();
    }

    @Test
    public void testUserRepository() {
        userRepository.save(new AppUser("test", "$2a$10$yYNsQoXwaJW9wcnqV0iN0.EezI4olxJgc/XgiMnwdWSP3lFDy9vBy", "test@email.com", "USER"));

        AppUser foundUser = userRepository.findByUsername("test");
        assertThat(foundUser).isNotNull();

        userRepository.delete(foundUser);
        assertThat(userRepository.findByUsername("test")).isNull();
    }

}
