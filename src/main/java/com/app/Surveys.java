package com.app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Surveys extends JpaRepository<Survey, Long>{
    List<Survey> findByCreator(String creator);
    Survey findById(long id);
}