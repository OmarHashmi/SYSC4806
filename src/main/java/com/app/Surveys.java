package com.app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Surveys extends JpaRepository<Survey, Long>{
    List<Survey> findByUserID(long userID);
    Survey findByUserIDAndId(long userID, long id);
    Survey findById(long id);
}
