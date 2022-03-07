package com.app;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Survey extends JpaRepository<Question, Long>{
    List<Question> findByQuestion(String question);
    Question findById(long id);
}