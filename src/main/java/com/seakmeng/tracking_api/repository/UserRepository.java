package com.seakmeng.tracking_api.repository;

import com.seakmeng.tracking_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);
  
}

