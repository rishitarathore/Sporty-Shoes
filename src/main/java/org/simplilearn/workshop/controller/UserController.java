package org.simplilearn.workshop.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.simplilearn.workshop.model.Login;
import org.simplilearn.workshop.model.User;
import org.simplilearn.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.simplilearn.workshop.model.Category;
import org.simplilearn.workshop.model.Product;
import org.simplilearn.workshop.model.ProductPurchase;
import org.simplilearn.workshop.model.Purchase;


import org.simplilearn.workshop.service.CategoryService;
import org.simplilearn.workshop.service.ProductPurchaseService;
import org.simplilearn.workshop.service.ProductService;
import org.simplilearn.workshop.service.PurchaseService;


@Controller
public class UserController {
	
	private ProductService productService;
    private PurchaseService purchaseService;
    private UserService userService;
    private ProductPurchaseService productPurchaseService;
    private CategoryService categoryService;

    
    
    @Autowired
    public UserController(ProductService productService,
                              PurchaseService purchaseService,
                              UserService userService,
                              ProductPurchaseService productPurchaseService,
                              CategoryService categoryService) {
        this.productService = productService;
        this.purchaseService = purchaseService;
        this.userService = userService;
        this.productPurchaseService = productPurchaseService;
        this.categoryService = categoryService;
    }
	
	

	@GetMapping("/user")
	public String login(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			return "userLogin";
		}

		map.addAttribute("userlogin", "userlogin");
		return "userLogin";
	}

	@PostMapping("/user/login")
	public String loginAction(ModelMap map, @ModelAttribute Login login, javax.servlet.http.HttpServletRequest request,
			@RequestParam String username, @RequestParam String password) {

		User user = userService.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if (user == null) {
			map.addAttribute("errorMessage", "User login failed");
			return "userLogin";
		}
		// store user id in session
		HttpSession session = request.getSession();
		session.setAttribute("user_id", user.getId());
		
		//store user name in session to display in nav bar 
		session.setAttribute("user_username", user.login.getUsername());


		return "redirect:/user/dashboard";
	}

	@GetMapping("/user/dashboard")
	public String dashboard(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null  && session.getAttribute("admin_id") == null) {
			return "userLogin";
		}
		
		List<Product> productList = productService.findAll();
		 HashMap<Long, String> mapCats = new HashMap<Long, String>();
  
		 for(Product product: productList) {
	
			  Long id = product.category.getId();
			  Category category = categoryService.findById(id);
			  if (category != null)
				  mapCats.put(product.getId(), category.getName());
		  }
		 
		 List<Category> categorylist = categoryService.findAll();

		 map.addAttribute("categorylist", categorylist);
		map.addAttribute("mapCats", mapCats);
		map.addAttribute("productList", productList);
		return "userDashboard";
	}
	

	@GetMapping("/register")
    public String signup(ModelMap map) 
    {	
        return "userRegister"; 
    }	
	
	
	@PostMapping("/user/register")
	public String registerAction(ModelMap map,  javax.servlet.http.HttpServletRequest request,
			@RequestParam(value="firstname", required=true) String firstname,
			@RequestParam(value="lastname", required=true) String lastname,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="age", required=true) String age,
			@RequestParam(value="address", required=true) String address,
			@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password) {

			User user = userService.findByUsername(username);
			if(user !=null) {
				map.addAttribute("error", "This username already exists. ");
				return "/userRegister";
			}
			
			
			user = new User();
			user.setLogin(new Login());
			user.setId((long) 0);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setAge(Integer.parseInt(age));
			user.setEmail(email);
			user.setAddress(address);		
			user.login.setUsername(username);
			user.login.setPassword(password);

			userService.saveUser(user);
			
		return "redirect:/user/registerconfirm";
	}
	
	@GetMapping("/user/registerconfirm")
	//return register-confirm.jsp view
	public String registerConfirm(ModelMap map) {
		
		return "registerConfirm";
	}
	
	
	 @GetMapping("/user/editprofile")
	    public String editProfile(ModelMap map, HttpSession session) 
	    {
		  if (session.getAttribute("user_id") == null) {
			  return "userlogin";
		  }
		  User user = userService.findById((Long) session.getAttribute("user_id"));

		  map.addAttribute("user", user);

	        return "editUser"; 
	    }		 	  

	 	
	//yaha pe edit profile ka action add krna  	  

	 @PostMapping("/user/editProfileAction")
	    public String editProfileAction(ModelMap map,
	    		javax.servlet.http.HttpServletRequest request, 
	    		 @RequestParam(value="firstname", required=true) String first_name,
	    		 @RequestParam(value="lastname", required=true) String last_name,
	    		 @RequestParam(value="age", required=true) String age,
	    		 @RequestParam(value="email", required=true) String email,
	    		 @RequestParam(value="address", required=true) String address,
	    		 @RequestParam(value="username", required=true) String username) 
	    {
		  
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  return "userLogin";
		  }
		  
		  User user = userService.findById((Long) session.getAttribute("user_id"));
		  map.addAttribute("user", user);
		  
		  user.setFirstName(first_name);
		  user.setLastName(last_name);
		  user.setAge(Integer.parseInt(age));
		  user.setAddress(address);
		  user.setEmail(email);
		  user.setLogin(new Login());
		  user.login.setUsername(username);
		  
		  userService.saveUser(user);
		  
		//update session
			session.setAttribute("user_username", user.login.getUsername());
		  
	        return "userEditProfConfirm"; 
	    }
	 
	 	@GetMapping("/user/yourOrders")
		public String yourOrders(ModelMap map, HttpSession session) 
		{
		if (session.getAttribute("user_id") == null) {
			return "userLogin";
		}
		long userId = (Long) session.getAttribute("user_id");
		
		List<Purchase> list = purchaseService.findAllByUserId(userId);
		
		BigDecimal total = new BigDecimal(0.0);
		// map purchase items to each purchase for display
		HashMap<Long, String> mapItems = new HashMap<Long, String>();
		
		for(Purchase purchase: list) {
			total = total.add(purchase.getGrossTotal());
			
			List<ProductPurchase> itemList = productPurchaseService.findAllByPurchaseId(purchase.getId());
			StringBuilder sb = new StringBuilder("");
			for(ProductPurchase item: itemList) {
				Product product = productService.findById(item.getProductId());
				if (product != null) {
					sb.append(product.getName() + ", " + 
							item.getQty() + " units @" + item.getRate() + " = " 
							+ item.getPrice() + "<br>");
				}
			}
			mapItems.put(purchase.getId(), sb.toString());
		}
		map.addAttribute("totalAmount", total);
		map.addAttribute("list", list);
		map.addAttribute("mapItems", mapItems);
	
		return "userOrder"; 
		}		  
	 
	 
  
  
	
}
