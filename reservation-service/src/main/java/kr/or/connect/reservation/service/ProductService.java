package kr.or.connect.reservation.service;

import java.util.Collection;
import java.util.List;

import kr.or.connect.reservation.domain.Product;

public interface ProductService {
	List<Product> getLimit(Integer start, Integer id);
	List<Product> getAll(Integer start);
	Integer getCountAll();
	Integer getCountId(Integer id);
}
