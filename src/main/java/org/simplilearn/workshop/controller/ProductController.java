package org.simplilearn.workshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.simplilearn.workshop.model.Category;
import org.simplilearn.workshop.model.Product;
import org.simplilearn.workshop.service.CategoryService;
import org.simplilearn.workshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

	int flag = 0;
	int flag1 = 0;

	private ProductService productService;
	private CategoryService categoryService;

	@Autowired
	public ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("/admin/productPanel")
	public String productPanel(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}
		map.addAttribute("productPanel", "productPanel");
		return "productPanel";
	}

	@GetMapping("/admin/editProduct")
	public String editProduct(ModelMap map, @RequestParam(value = "id", required = true) String id,
			javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}
		long idValue = Long.parseLong(id);
		Product product = new Product();
		if (idValue > 0) {
			product = productService.findById(idValue);
		} else {
			product.setId((long) 0);
		}

		// added code for title change for Add/ Edit
		if (idValue > 0) {
			map.addAttribute("titlemsg", "Edit Product");
		} else {
			map.addAttribute("titlemsg", "Add Product");
		}

		List<Category> categoryList = categoryService.findAll();

		map.addAttribute("categoryList", categoryList);
		map.addAttribute("product", product);

		if (flag == 1) {
			map.addAttribute("successmsg", "Added Successfully");
			flag = 0;
		}
		return "productForm";
	}

	@PostMapping("/admin/saveProduct")
	public String saveProduct(ModelMap map, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "price", required = true) String price,
			@RequestParam(value = "details", required = true) String details,
			@RequestParam(value = "categoryId", required = true) String categoryId,
			javax.servlet.http.HttpServletRequest request) throws IOException {
		long idValue = Long.parseLong(id);
		double pricee = Double.parseDouble(price);

		Product product = new Product();
		product.setId(idValue);
		product.setName(name);
		product.setPrice(pricee);
		product.setDetails(details);
		Category category = new Category();
		category.setId(Long.parseLong(categoryId));
		product.setCategory(category);

		productService.save(product);

		if (idValue == 0) {
			flag = 1;
			return "redirect:/admin/editProduct?id=0";
		} else {
			flag1 = 1;
			return "redirect:/admin/productList";
		}
	}

	@GetMapping("/admin/productList")
	public String productList(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}

		List<Product> list = productService.findAll();
		map.addAttribute("list", list);

		if (flag1 == 1) {
			map.addAttribute("successeditmsg", "Edited Successfully");
			flag1 = 0;
		} else if (flag1 == 2) {
			map.addAttribute("successdeletemsg", "Delete Successfully");
			flag1 = 0;
		}

		return "productList";
	}

	@GetMapping("/admin/deleteProduct")
	public String deleteProduct(ModelMap map, @RequestParam(value = "id", required = true) String id,
			javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}
		long idValue = Long.parseLong(id);
		if (idValue > 0) {

			productService.deleteById(idValue);
			flag1 = 2;
		}
		return "redirect:/admin/productList";
	}
}
