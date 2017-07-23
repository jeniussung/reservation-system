package kr.or.connect.reservation.dao;

public class ProductSqls {
	
    final static String SELECT_COUNT = "select count(*) from product";
    
    final static String SELECT_COUNT_ID = "select count(*) from product where category_id = :category_id";
    
    final static String SELECT_LIMIT = "SELECT pro.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, pro.create_date, pro.modify_date, file_id "
								    		+ "FROM product_image "
								    		+ "RIGHT OUTER JOIN "
								    		+ "(SELECT product.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, product.create_date, product.modify_date "
								    		+ "FROM product "
								    		+ "INNER JOIN display_info ON product.id = product_id)pro "
								    		+ "ON pro.id = product_image.product_id "
								    		+ "WHERE type = 2 ORDER BY pro.id LIMIT :start, 10";
    
    final static String SELECT_ALL = "select product.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, product.create_date, product.modify_date  "
    										+ "from product inner join display_info "
    										+ "on product.id = product_id order by id limit :start, 10";
    
    final static String SELECT_CATEGORY_ID = "SELECT pro.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, pro.create_date, pro.modify_date, file_id "
    											+ "FROM product_image "
    											+ "RIGHT OUTER JOIN "
    											+ "(select product.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, product.create_date, product.modify_date "
    											+ "FROM product "
    											+ "INNER JOIN display_info "
    											+ "ON product.id = product_id WHERE product.category_id = :category_id)pro "
    											+ "ON pro.id = product_image.product_id WHERE type = 2 ORDER BY pro.id LIMIT :start, 10";
    		
}
