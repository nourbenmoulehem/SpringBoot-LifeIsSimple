package com.simply.simple_life.Repository;

import com.simply.simple_life.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> getUsersById(int id);
}
