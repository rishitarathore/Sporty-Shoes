package org.simplilearn.workshop.service;

import java.util.List;

import org.simplilearn.workshop.model.Category;
import org.simplilearn.workshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
    
//    public String getCategoriesDropDown(long id) {
//		 StringBuilder sb = new StringBuilder("");
//		 List<Category> list = categoryRepository.findAll();
//		 for(Category cat: list) {
//			 if (cat.getId() == id)
//				 sb.append("<option value=" + String.valueOf(cat.getId()) + " selected>" + cat.getName() + "</option>");
//			 else
//				 sb.append("<option value=" + String.valueOf(cat.getId()) + ">" + cat.getName() + "</option>");
//				 
//		 }
//		 return sb.toString();
//	 }
}