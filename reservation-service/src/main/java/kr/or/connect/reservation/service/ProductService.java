package kr.or.connect.reservation.service;

import java.util.Collection;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public Collection<Product> getLimit(Integer start, Integer id);
	public Collection<Product> getAll(Integer start);
	public int getCountAll();
	public int getCountId(Integer id);
}