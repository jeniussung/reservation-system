package kr.or.connect.reservation.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.CategoryFormParam;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    //@RequestBody Category category
    //@ModelAttribute CategoryFormParam categoryparam
    @PostMapping
    public Category create(@RequestBody Category category) {
        if (category.getName() == null || category.getName().length() == 0 ) {
            return null; 
        }else {
                //Category category = new Category();
                //category.setName(categoryparam.getName());
                Category resultCategory = categoryService.addMember(category);
                	//RedirectView view = new RedirectView("hello");
                return resultCategory;
                
            }
        }
    
    @GetMapping
	Collection<Category> readList() {
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
