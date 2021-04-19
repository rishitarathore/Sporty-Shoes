package org.simplilearn.workshop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.simplilearn.workshop.model.Category;
import org.simplilearn.workshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

	private CategoryService categoryService;

	// flag to print added successfully message
	int flag = 0;
	int flag1 = 0;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/admin/categoryPanel")
	public String categoryPanel(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}
		map.addAttribute("categoryPanel", "categoryPanel");
		return "categoryPanel";
	}

	@GetMapping("/admin/editCategory")
	public String editCategory(ModelMap map, @RequestParam(value = "id", required = true) String id,
			javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}
		long idValue = Long.parseLong(id);
		Category category = new Category();
		if (idValue > 0) {
			category = categoryService.findById(idValue);
		} else {
			category.setId((long) 0);
		}

		// added code for title change for Add/ Edit
		if (idValue > 0) {
			map.addAttribute("titlemsg", "Edit Category");
		} else {
			map.addAttribute("titlemsg", "Add Category");
		}

		if (flag == 1) {
			map.addAttribute("successmsg", "Added Successfully");
			flag = 0;
		}
		map.addAttribute("category", category);
		return "categoryForm";
	}

	@PostMapping("/admin/saveCategory")
	public String saveCategory(ModelMap map, @RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name, javax.servlet.http.HttpServletRequest request) {
		long idValue = Long.parseLong(id);
		Category category = new Category();
		category.setId(idValue);
		category.setName(name);

		categoryService.save(category);
		if (idValue == 0) {
			flag = 1;
			return "redirect:/admin/editCategory?id=0";
		} else {
			flag1 = 1;
			return "redirect:/admin/categoryList";
		}

	}

	@GetMapping("/admin/categoryList")
	public String categoryList(ModelMap map, javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}

		List<Category> list = categoryService.findAll();
		map.addAttribute("list", list);

		if (flag1 == 1) {
			map.addAttribute("successeditmsg", "Edited Successfully");
			flag1 = 0;
		} else if (flag1 == 2) {
			map.addAttribute("successdeletemsg", "Deleted Successfully");
			flag1 = 0;

		}
		return "categoryList";
	}

	@GetMapping("/admin/deleteCategory")
	public String deleteCategory(ModelMap map, @RequestParam(value = "id", required = true) String id,
			javax.servlet.http.HttpServletRequest request) {
		// check if session is still alive
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_id") == null) {
			return "adminlogin";
		}
		long idValue = Long.parseLong(id);
		if (idValue > 0) {

			flag1 = 2;
			categoryService.deleteById(idValue);
		}
		return "redirect:/admin/categoryList";
	}
}
