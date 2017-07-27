package kr.or.connect.reservation.service;

import java.util.Collection;

import kr.or.connect.reservation.domain.Category;

public interface CategoryService {
	public Category get(Long id);
	public Collection<Category> getAll();
    public Category addMember(Category category);
    public boolean delete(Integer id);
    public boolean update(Category category);
    public Collection<Category> getLimit(Integer start);
}
