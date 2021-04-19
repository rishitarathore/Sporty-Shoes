package org.simplilearn.workshop.repository;

import java.util.List;

import org.simplilearn.workshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginUsernameAndLoginPassword(String username, String password);
    User findByLoginUsername(String username);
    List<User> findByFirstNameContainsOrLastNameContainsOrEmailContains(String firstName, String lastName, String email);
}
