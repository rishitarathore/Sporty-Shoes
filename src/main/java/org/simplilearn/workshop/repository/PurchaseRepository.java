package org.simplilearn.workshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.simplilearn.workshop.model.Purchase;
import org.simplilearn.workshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
    List<Purchase> findByDate(LocalDate date);
    List<Purchase> findAllByUserId(long userId);

}
