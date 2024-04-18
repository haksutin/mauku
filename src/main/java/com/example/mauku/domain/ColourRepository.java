package com.example.mauku.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ColourRepository extends JpaRepository<Colour, Long> {

    List<Colour> findByName(String name);

}
