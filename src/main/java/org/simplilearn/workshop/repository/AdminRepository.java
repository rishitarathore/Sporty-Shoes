package org.simplilearn.workshop.repository;

import org.simplilearn.workshop.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByLoginUsernameAndLoginPassword(String username, String password);
}
