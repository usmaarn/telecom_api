package com.telzz.sub.repository;

import com.telzz.sub.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);

    Optional<User> findByEmailAddress(String emailAddress);
}
