package org.simplilearn.workshop.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.simplilearn.workshop.service.ProductPurchaseService;
import org.simplilearn.workshop.service.ProductService;
import org.simplilearn.workshop.service.PurchaseService;
import org.simplilearn.workshop.service.UserService;
import org.simplilearn.workshop.model.Product;
import org.simplilearn.workshop.model.ProductPurchase;
import org.simplilearn.workshop.model.Purchase;
import org.simplilearn.workshop.model.User;
import org.simplilearn.workshop.model.CartItem;

@Controller
public class CartController {

	private ProductService productService;
	private UserService userService;
	private PurchaseService purchaseService;
	private ProductPurchaseService productPurchaseService;
	
	 @Autowired
	    public CartController(ProductService productService, UserService userService, PurchaseService purchaseService,ProductPurchaseService productPurchaseService) {
	        this.productService = productService;
	        this.userService = userService;
	        this.purchaseService = purchaseService;
	        this.productPurchaseService = productPurchaseService;
	    }
	
	@SuppressWarnings("unchecked")
	@GetMapping("/cart")
	 public String cart(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    { 
		// check if session is still alive
				HttpSession session = request.getSession();
				if (session.getAttribute("user_id") == null) {
					
					return "userLogin";
				}
				else {
					  List<CartItem> cartItems = new ArrayList<CartItem>();
					  
					if (session.getAttribute("cart_items") != null)
				cartItems = (List<CartItem>) session.getAttribute("cart_items");
					// get total of all cart items
					  
					  BigDecimal totalValue = getCartValue(cartItems);
					  map.addAttribute("totalValue", totalValue);
					  map.addAttribute("cartItems", cartItems);
				}
	
		 return "cart";
	    }
	
	
	 @SuppressWarnings({ "unchecked" })
	 @GetMapping("/cart/addItem")
	    public String cartAddItem(ModelMap model, javax.servlet.http.HttpServletRequest request,
	    		@RequestParam(value="id", required=true) String productId) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
				
				return "userLogin";
		  } 
		  
		  else {
			  
			  long idValue = Long.parseLong(productId);
		
//			   if cart is already in session then retrieve it else create a new cart list and 
//			   save it to session
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  
				  int qty=1;
				  Product product = productService.findById(idValue);
				  CartItem item = new CartItem();
				  item.setProductId(idValue);
				  long catid = product.category.getId();
				  item.setCategoryId(catid);
				  
				  
				  
				  if (isItemInCart(cartItems, idValue)) {
	  
					  qty++;
					  item.setQty(qty);  
					  item.setRate(new BigDecimal(product.getPrice()));
					  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty())); 
					  item.setPrice(dprice); 
					  item.setName(product.getName()); 				  
					  cartItems.remove(item);
					  cartItems.add(item);
					
				  }
				  else {

					  item.setQty(qty);
					  item.setRate(new BigDecimal(product.getPrice()));
					  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty())); 
					  item.setPrice(dprice); 
					  item.setName(product.getName()); 
					  cartItems.add(item);
				  }
				  
				 session.setAttribute("cart_items", cartItems);
		  }
		  
	        return "redirect:/cart"; 
	    }	  
	  
	 
	 
	  @SuppressWarnings("unchecked")
	  @GetMapping("/cart/deleteItem")
	public String cartDeleteItem(ModelMap map, javax.servlet.http.HttpServletRequest request, 
	    		@RequestParam(value="id", required=true) String id) {
		  
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
				return "userLogin";
		  } 
		  else {
			  long idValue = Long.parseLong(id);
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  
			  if (session.getAttribute("cart_items") != null)
			cartItems = (List<CartItem>) session.getAttribute("cart_items");
			 
			  for(CartItem item: cartItems) {
				  if (item.getProductId() == idValue) {
					  cartItems.remove(item);
					  session.setAttribute("cart_items", cartItems);
					  break;
				  }
			   }
			  
		  }
		  
		  return "redirect:/cart";
	  }
	 
	 
	  @SuppressWarnings("unchecked")
	@GetMapping("/cart/checkout")
	  public String checkout(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		  
		  
		// check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
				return "userLogin";
		  } 
		  else
		  {
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  map.addAttribute("totalValue", totalValue);
			  
		  }
		  
		  //used to prepopulate email-id and address
		  User user = userService.findById((Long) session.getAttribute("user_id"));
		  map.addAttribute("user", user);
		  
		  
		  return "checkout";
	  }
	  
	 
	  
	  //yaha pe meko cart empty wala add krna h
	  @SuppressWarnings("unchecked")
		@GetMapping("/cart/orderPlacedAction")
	    public String orderPlacedAction(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
	    	HttpSession session = request.getSession();
			  if (session.getAttribute("user_id") == null) {
					return "userLogin";
			  } 
			  else {
			  // take items from cart and update the database
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				  cartItems = (List<CartItem>) session.getAttribute("cart_items");
			  BigDecimal totalValue = getCartValue(cartItems);
			  
			  long userId = (Long) session.getAttribute("user_id") ;
			  
			  Purchase purchase = new Purchase();
			  purchase.setUser(new User());
			  purchase.user.setId(userId);
			  purchase.setDate(LocalDate.now());
			  purchase.setGrossTotal(totalValue);
			  long purchaseId = purchaseService.updatePurchase(purchase);
			  
			  for(CartItem item: cartItems) {
				  ProductPurchase pItem = new ProductPurchase();
				  pItem.setPurchaseId(purchaseId);
				  pItem.setProductId(item.getProductId());
				  pItem.setCategoryId(item.getCategoryId());
				  pItem.setUserId(userId);
				  pItem.setRate(item.getRate());
				  pItem.setQty(item.getQty());
				  pItem.setPrice(item.getPrice());
				  
				  productPurchaseService.save(pItem);
			  }
			  map.addAttribute("cartValue", totalValue);
			  map.addAttribute("cartItems", cartItems);

		  }
			  
		  
	        return "redirect:/cart/orderSuccess";  
	    }
	  
	
	  
	   @SuppressWarnings("unchecked")
	@GetMapping("/cart/orderSuccess")
	    private String orderSuccess(ModelMap map, javax.servlet.http.HttpServletRequest request) {
	    	HttpSession session = request.getSession();
			if (session.getAttribute("user_id") == null) {
				return "userLogin";
			}
			
			
			// clear cart once order is confirmed
			  List<CartItem> cartItems = new ArrayList<CartItem>();
			  if (session.getAttribute("cart_items") != null)
				cartItems = (List<CartItem>) session.getAttribute("cart_items");
			 
			
			
			cartItems.clear();
			session.setAttribute("cart_items", null);
				return "orderSuccess";
	    	
	    }
	
	 private boolean isItemInCart(List<CartItem> list, long item) {
		  boolean retVal = false;
		  
		  for(CartItem thisItem: list) {
			  if (item == thisItem.getProductId()) {
				  retVal = true;
				  break;
			  }
		  }
		  return retVal;
	  } 

	
	
		private BigDecimal getCartValue(List<CartItem> list) {
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(CartItem item: list) {
			  BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQty()));
			  total= total.add(dprice);
		   }
		  return total;
	  }
	
}
