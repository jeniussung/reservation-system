package kr.or.connect.reservation.dao.sqls;

public class CategorySqls {
	public final static String SELECT_BY_ID = "select id, name from category where id = :id";
    public final static String UPDATE_BY_ID = "update category set name = :name where id = :id";
    public final static String DELETE_BY_ID = "delete from category where id = :id";
    public final static String SELECT = "select id, name from category order by id ";
    public final static String SELECT_LIMIT = "select id, name from category order by id limit :start, :count";
}