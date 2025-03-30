package com.ODSMTS.Controller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ODSMTS.Controller.Entity.User;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // Check if username exists
    Optional<User> findByEmail(String email);        // Check if email exists

    // ✅ Fetch multiple emails for a given hospital ID
    @Query("SELECT u.email FROM User u WHERE u.hospitalId = :hospitalId")
    List<String> findEmailsByHospitalId(@Param("hospitalId") Long hospitalId);
}

