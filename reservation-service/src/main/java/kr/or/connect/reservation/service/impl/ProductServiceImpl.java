package kr.or.connect.reservation.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
    ProductDao productDao;
	
	@Transactional(readOnly = true)
	@Override
	public Collection<Product> getLimit(Integer start, Integer id) {
		// TODO Auto-generated method stub
		return productDao.selectLimit(start, id);
	}
	
	 @Transactional(readOnly = true)
	    public Collection<Product> getAll(Integer start)
	    {
	    		return productDao.selectAll(start);
	    }
	 
	 @Transactional(readOnly = true)
	    public int getCountAll()
	    {
	    		return productDao.selectCount();
	    }
	 
	 @Transactional(readOnly = true)
	    public int getCountId(Integer id)
	    {
	    		return productDao.selectCountId(id);
	    }
	
}
