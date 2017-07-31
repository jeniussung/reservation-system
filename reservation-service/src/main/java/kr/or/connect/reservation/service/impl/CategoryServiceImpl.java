package kr.or.connect.reservation.service.impl;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Transactional(readOnly = true)
	public Category get(Long id) {
		// TODO Auto-generated method stub
		return categoryDao.selectById(id);
	}

    
    @Transactional
	public Category addMember(Category category) {
		// TODO Auto-generated method stub
		Long insert = categoryDao.insert(category);
		category.setId(insert);
        return category;

	}
    
    @Transactional(readOnly = true)
    public List<Category> getAll()
    {
    		return categoryDao.selectAll();
    }
    
    @Transactional
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		int affected = categoryDao.deleteById(id);
		return affected == 1;
	}

    @Transactional
	public Boolean update(Category category) {
		// TODO Auto-generated method stub
		int affected = categoryDao.update(category);
		return affected == 1;
	}
    
    @Transactional(readOnly = true)
	@Override
	public List<Category> getLimit(Integer start) {
		// TODO Auto-generated method stub
		return categoryDao.selectLimit(start);
	}
}
