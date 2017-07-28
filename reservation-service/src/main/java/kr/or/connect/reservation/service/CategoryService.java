package kr.or.connect.reservation.service;

import java.util.Collection;
import java.util.List;

import kr.or.connect.reservation.domain.Category;

public interface CategoryService {
    Category get(Long id);
	List<Category> getAll();
    Category addMember(Category category);
    Boolean delete(Integer id);
    Boolean update(Category category);
    List<Category> getLimit(Integer start);
}
