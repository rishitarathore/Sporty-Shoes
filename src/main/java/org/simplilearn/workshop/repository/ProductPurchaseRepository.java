package org.simplilearn.workshop.repository;

import java.util.List;

import org.simplilearn.workshop.model.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {
	
	  List<ProductPurchase> findAllByPurchaseId(long purchase_id);
	 
	  @Query("select pi from ProductPurchase pi where pi.purchaseId=?1 and pi.categoryId=?2")
	  List<ProductPurchase> filteredPurchase(long purchase_id, long category_id);

}


