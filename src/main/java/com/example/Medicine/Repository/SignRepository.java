package com.example.Medicine.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Medicine.Model.Logins;

public interface SignRepository extends JpaRepository<Logins, Long> {
    Optional<Logins> findByUserName(String userName);
}
