package org.simplilearn.workshop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.simplilearn.workshop.model.Admin;
import org.simplilearn.workshop.model.Category;
import org.simplilearn.workshop.model.Login;
import org.simplilearn.workshop.model.User;
import org.simplilearn.workshop.service.AdminService;
import org.simplilearn.workshop.service.CategoryService;
import org.simplilearn.workshop.service.ProductPurchaseService;
import org.simplilearn.workshop.service.ProductService;
import org.simplilearn.workshop.service.PurchaseService;
import org.simplilearn.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.simplilearn.workshop.model.Product;
import org.simplilearn.workshop.model.Purchase;
import org.simplilearn.workshop.model.ProductPurchase;

@Controller
public class AdminController {

	private AdminService adminService;
	private ProductService productService;
	private PurchaseService purchaseService;
	private UserService userService;
	private ProductPurchaseService productPurchaseService;
	private CategoryService categoryService;

	@Autowired
	public AdminController(ProductService productService, PurchaseService purchaseService, UserService userService,
			ProductPurchaseService productPurchaseService, CategoryService categoryService, AdminService adminService) {
		this.adminService = adminService;
		this.userService = userService;
		this.userService = userService;
		this.productService = productService;
		this.purchaseService = purchaseService;
		this.userService = userService;
		this.productPurchaseService = productPurchaseService;
		this.categoryService = categoryService;
	}

	@GetMapping("/admin")
	public String login(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminLogin";
		}

		map.addAttribute("adminlogin", "adminlogin");
		return "adminLogin";
	}

	@PostMapping("/admin/login")
	public String loginAction(ModelMap map, @ModelAttribute Login login, javax.servlet.http.HttpServletRequest request,
			@RequestParam String username, @RequestParam String password) {

		Admin admin = adminService.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if (admin == null) {
			map.addAttribute("errorMessage", "Admin login failed");
			return "adminLogin";
		}
		// store admin id in session
		HttpSession session = request.getSession();
		session.setAttribute("admin_id", admin.getId());

		// store user name in session to display in nav bar
		session.setAttribute("admin_username", admin.login.getUsername());
		return "adminDashboard";
	}

	@GetMapping("/admin/dashboard")
	public String dashboard(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminLogin";
		}

		return "adminDashboard";
	}

	@GetMapping("/admin/userList")
	public String users(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminLogin";
		}
		
	
		
		List<User> list = userService.findAll();
		map.addAttribute("list", list);
		return "userList";
	}

	@GetMapping("/admin/changePassword")
	public String changePassword(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminLogin";
		}

		Admin admin = adminService.findById((Long) session.getAttribute("admin_id"));

		map.addAttribute("admin", admin);
		return "changePassword";
	}

	@PostMapping("/admin/changePassAction")
	public String updatePassword(ModelMap map, javax.servlet.http.HttpServletRequest request,
			@RequestParam(value = "password1", required = true) String password1,
			@RequestParam(value = "password2", required = true) String password2) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminLogin";
		}

		if (password1 == null || password2 == null || password1.equals("") || password1.equals("")) {
			map.addAttribute("errorMessage", "Error , Incomplete password submitted.");
			return "changePassword";
		}
		if (!password1.equals(password2)) {
			map.addAttribute("errorMessage", "Error , Password do not match.");
			return "changePassword";
		}

		Admin admin = adminService.findById((Long) session.getAttribute("admin_id"));

		admin.login.setPassword(password1);
		adminService.updatePassword(admin);

		return "redirect:/admin/changePassConfirm";
	}

	@GetMapping("/admin/changePassConfirm")
	public String changepassConfirm(ModelMap map) {

		return "changePassConfirm";
	}

	@RequestMapping(value = "/admin/purchaseReport", method = RequestMethod.GET)
	public String purchases(ModelMap map, javax.servlet.http.HttpServletRequest request,
			@RequestParam(required = false) String dateFilter,
			@RequestParam(value = "categoryId", required = false) Long categoryId) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminLogin";
		}

		// basic apporach for applying filter by category is we make another object of
		// list purchase type and add those specific purchases in which that item is
		// present.

		// Local Variable definition

		List<Purchase> defaultList = null;
		List<Purchase> filteredList = new ArrayList<Purchase>();
		List<ProductPurchase> itemList = null;

		BigDecimal totalAmount = new BigDecimal(0.0);

		// use MAPs to map users to each purchase and item names to each purchase item
		// row
		HashMap<Long, String> mapItems = new HashMap<Long, String>();
		HashMap<Long, String> mapUsers = new HashMap<Long, String>();

		if (dateFilter != null && !dateFilter.isEmpty()) {
			defaultList = purchaseService.findByDate(dateFilter);
		} else {
			defaultList = purchaseService.findAll();

		}

		for (Purchase purchase : defaultList) {
			User user = userService.findById(purchase.getUser().getId());
			if (user != null)
				mapUsers.put(purchase.getId(), user.getFirstName() + " " + user.getLastName());

			// category id is null when there is no filter by category
			if (categoryId == null) {
				itemList = productPurchaseService.findAllByPurchaseId(purchase.getId());
				// this will by default add all purchases in the filteredList
				filteredList.add(purchase);
				totalAmount = totalAmount.add(purchase.getGrossTotal());
			} else {
	

				itemList = productPurchaseService.filteredPurchase(purchase.getId(), categoryId);

				// itemList size is greater than 0 when item filtered by category is present in
				// that specific purchase id
				if (itemList.size() > 0) {
					// this will add only that purchases which has that specified category ( filter
					// category)
					filteredList.add(purchase);
					totalAmount = totalAmount.add(purchase.getGrossTotal());
				}
			}
			if (filteredList != null) {
				StringBuilder sb = new StringBuilder("");
				for (ProductPurchase item : itemList) {
					Product product = productService.findById(item.getProductId());
					if (product != null)
						sb.append(product.getName() + ", " + item.getQty() + " units @" + item.getRate() + " = "
								+ item.getPrice() + "<br>");
				}
				mapItems.put(purchase.getId(), sb.toString());
			}
		}
		List<Category> categoryList = categoryService.findAll();
		map.addAttribute("categoryList", categoryList);
		map.addAttribute("totalAmount", totalAmount);
		map.addAttribute("list", filteredList);
		map.addAttribute("mapItems", mapItems);
		map.addAttribute("mapUsers", mapUsers);

		return "purchaseReport";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/";
	}
}
