package com.app;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result, Long>{
    List<Result> findByQuestion(String question);
    Result findById(long id);
}
