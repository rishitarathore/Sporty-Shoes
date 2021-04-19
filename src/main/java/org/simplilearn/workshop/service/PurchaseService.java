package org.simplilearn.workshop.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.simplilearn.workshop.model.Purchase;
import org.simplilearn.workshop.model.User;
import org.simplilearn.workshop.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public long updatePurchase(Purchase purchase) {
		 purchaseRepository.save(purchase);
		 return purchase.getId();
	 }
	 
    public List<Purchase> findAllByUser(User user) {
        return purchaseRepository.findAllByUser(user);
    }

	
	public List<Purchase> findAllByUserId(long userId) {
		 return purchaseRepository.findAllByUserId(userId);
	}	
	 
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> findByDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        return purchaseRepository.findByDate(date);
    }
    
    
    public Purchase findByid(long id)
    {
    	return purchaseRepository.findById(id).orElse(null);
    }
  }