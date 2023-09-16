package com.esp.manilla.repository;

import com.esp.manilla.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByEmail(String email);
}
