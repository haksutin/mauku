package com.example.mauku.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findByName(String name);

}
