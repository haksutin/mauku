package com.example.mauku;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mauku.domain.AppUserRepository;
import com.example.mauku.domain.Cat;
import com.example.mauku.domain.CatRepository;
import com.example.mauku.domain.ColourRepository;
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
        catRepository.save(new Cat("Test", 2.0, "2024-01-01", null, null));

        List<Cat> foundCat = catRepository.findByName("Test");
        assertThat(foundCat).isNotNull();

        catRepository.delete(foundCat.get(0));
        assertThat(catRepository.findByName("Test")).isNotNull();
    }

    
}
