package com.parrot.customers.pointsalewebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parrot.customers.pointsalewebapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query()
    public User findByEmail(@Param("email") String email);
}
