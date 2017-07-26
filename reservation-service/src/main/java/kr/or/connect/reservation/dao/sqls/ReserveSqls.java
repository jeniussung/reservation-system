package kr.or.connect.reservation.dao.sqls;

public class ReserveSqls {

	public final static String SELECT_RESERVE_INFO = "SELECT info.product_id, name, event, price_type, price, discount_rate, observation_time, display_start, display_end, place_name, file_id "
													+ "FROM product_image "
													+ "INNER JOIN "
													+ "(SELECT display_info.product_id, name, event, price_type, price, discount_rate, observation_time, display_start, display_end, place_name "
													+ "FROM display_info "
													+ "INNER JOIN "
													+ "(SELECT name, event, product_id, price_type, price, discount_rate "
													+ "FROM product "
													+ "INNER JOIN product_price "
													+ "ON product.id = product_id "
													+ "WHERE product_id = :id)price "
													+ "ON display_info.product_id = price.product_id)info "
													+ "ON info.product_id = product_image.product_id WHERE type = 2";
}
