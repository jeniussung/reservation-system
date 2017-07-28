package kr.or.connect.reservation.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @PostMapping
    public Category create(@RequestBody Category category) {
        if (category.getName() == null || category.getName().length() == 0 ) {
            return null; 
        }else {
                Category resultCategory = categoryService.addMember(category);
                return resultCategory;
                
            }
        }
    
    @GetMapping
    public List<Category> readList() {
		return categoryService.getAll();
	}
    
 
    @DeleteMapping("/{id}")
    boolean delete(@PathVariable Integer id){
		return categoryService.delete(id);
	}
    
    @PutMapping
	boolean update(@RequestBody Category category){		
		return categoryService.update(category);
	}
}
