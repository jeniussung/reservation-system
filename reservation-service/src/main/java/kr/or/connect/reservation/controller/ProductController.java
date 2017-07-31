package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@GetMapping("/{start}/{id}")
	Map<String,Object> getLimit(@PathVariable("start") Integer start, @PathVariable("id") Integer id) {
		Map<String,Object> map = new HashMap<>();
		map.put("count", productService.getCountId(id));
		map.put("product", productService.getLimit(start,id));
		return map;
	}
	
	@GetMapping("/{start}")
	Map<String,Object> readList(@PathVariable("start") Integer start) {
		Map<String,Object> map = new HashMap<>();
		map.put("count", productService.getCountAll());
		map.put("product", productService.getAll(start));
		return map;
	}
	
}
