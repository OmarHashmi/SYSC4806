package com.app;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBook extends JpaRepository<BuddyInfo, Long>{
    List<BuddyInfo> findByName(String name);
    BuddyInfo findById(long id);
}
