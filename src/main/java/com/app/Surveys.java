package com.app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Surveys extends JpaRepository<Poll, Long>{
    List<Poll> findByCreator(String creator);
    Poll findById(long id);
}
