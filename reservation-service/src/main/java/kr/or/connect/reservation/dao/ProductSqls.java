package kr.or.connect.reservation.dao;

public class ProductSqls {
	
    final static String SELECT_COUNT = "select count(*) from product";
    
    final static String SELECT_COUNT_ID = "select count(*) from product where category_id = :category_id";
    
    final static String SELECT_LIMIT = "select product.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, product.create_date, product.modify_date  "
    										+ "from product inner join display_info "
    										+ "on product.id = product_id order by id limit :start, 10";
    
    final static String SELECT_ALL = "select product.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, product.create_date, product.modify_date  "
    										+ "from product inner join display_info "
    										+ "on product.id = product_id order by id limit :start, 10";
    
    final static String SELECT_CATEGORY_ID = "select product.id, category_id, name, description, place_name, sales_start, sales_end, sales_flag, event, product.create_date, product.modify_date  "
    												+ "from product inner join display_info "
    												+ "on product.id = product_id where product.category_id = :category_id order by id limit :start, 10";
}
