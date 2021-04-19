package org.simplilearn.workshop.service;

import java.util.List;

import org.simplilearn.workshop.model.ProductPurchase;
import org.simplilearn.workshop.repository.ProductPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPurchaseService {
    private ProductPurchaseRepository productPurchaseRepository;

    @Autowired
    public ProductPurchaseService(ProductPurchaseRepository productPurchaseRepository) {
        this.productPurchaseRepository = productPurchaseRepository;
    }
    

	public List<ProductPurchase> findAllByPurchaseId(long purchaseId) {
		return productPurchaseRepository.findAllByPurchaseId(purchaseId);
	}	
	
    public ProductPurchase save(ProductPurchase productPurchase) {
        return productPurchaseRepository.save(productPurchase);
    }
    
    public List<ProductPurchase> filteredPurchase(long purchaseId, long categoryId){
    	return productPurchaseRepository.filteredPurchase(purchaseId, categoryId);
    }
    
}
