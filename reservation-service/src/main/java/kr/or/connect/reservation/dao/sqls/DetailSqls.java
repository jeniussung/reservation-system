package kr.or.connect.reservation.dao.sqls;

public class DetailSqls {

	public final static String SELECT_DETAIL_TOP = "SELECT file.id AS file_id, p_file.product_id, category_id, name, description, sales_start, sales_end, sales_flag, event, observation_time, display_start, display_end, place_name, place_lot, place_street, tel, homepage, email, save_file_name, type "
											+ "FROM file "
											+ "RIGHT OUTER JOIN "
											+ "(SELECT type, dis_info.product_id, category_id, name, description, sales_start, sales_end, sales_flag, event, observation_time, display_start, display_end, place_name, place_lot, place_street, tel, homepage, email, file_id "
											+ "FROM product_image "
											+ "RIGHT OUTER JOIN "
											+ "(SELECT product_id, category_id, name, description, sales_start, sales_end, sales_flag, event, observation_time, display_start, display_end, place_name, place_lot, place_street, tel, homepage, email  "
											+ "FROM product "
											+ "RIGHT OUTER JOIN "
											+ "display_info ON product.id = product_id WHERE product_id = :id)dis_info "
											+ "ON product_image.product_id = dis_info.product_id)p_file ON file.id = p_file.file_id ORDER BY type desc";



	public final static String SELECT_COMMENT_INFO = "SELECT comment_info.id , COUNT(comment_info.id) AS img_count ,nickname, product_id, user_id, MIN(file_id) AS file_id, score, comment, comment_info.create_date, comment_info.modify_date "
												+ "FROM users "
												+ "INNER JOIN "
												+ "(SELECT DISTINCT reservation_user_comment.id, product_id, user_id, file_id, score, comment, create_date, modify_date "
												+ "FROM reservation_user_comment "
												+ "LEFT OUTER JOIN "
												+ "reservation_user_comment_image ON reservation_user_comment.id = reservation_user_comment_id "
												+ "WHERE product_id = :id)comment_info ON users.id = comment_info.user_id GROUP BY comment_info.id LIMIT 0,4";

	public final static String SELECT_COMMENT_IMAGE = "SELECT FILE_ID.id AS commentId , file.id as fileId "
												+ "FROM file "
												+ "INNER JOIN "
												+ "(SELECT reservation_user_comment.id, file_id "
												+ "FROM reservation_user_comment "
												+ "INNER JOIN "
												+ "reservation_user_comment_image ON reservation_user_comment.id = reservation_user_comment_id "
												+ "WHERE reservation_user_comment.id = :id)file_id ON file.id = file_id.file_id";

	public final static String SELEECT_DETAIL_BOTTOM_CONTENT = "SELECT content FROM product_detail WHERE product_id = :id";
}
