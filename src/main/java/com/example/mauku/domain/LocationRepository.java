package com.example.mauku.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByName (String name);

}
