package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dao.sqls.CategorySqls;
import kr.or.connect.reservation.dao.sqls.ProductSqls;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Category;

import javax.sql.DataSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {
    private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.

    // Spring은 생성자를 통하여 주입을 하게 된다. 빈을 따로 등록하지 않고 선언해서 사용했음.
    
    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
        this.insertAction = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
                .withTableName("category")   // table명을 지정
                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
    }
    
    public Long insert(Category member){
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        return insertAction.executeAndReturnKey(params).longValue();
    }
   
    
    public Category selectById(long id){
    		try {    		
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("id", id);
	        return jdbc.queryForObject(CategorySqls.SELECT_BY_ID,params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
    		}catch(EmptyResultDataAccessException e)
    		{
    			return null;
    		}
    }
    
    public List<Category> selectAll(){
        return jdbc.query(CategorySqls.SELECT,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
    }
        
    public int update(Category member){
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        return jdbc.update(CategorySqls.UPDATE_BY_ID, params);
    }

    public int deleteById(Integer id){
        Map<String, ?> params = Collections.singletonMap("id", id);
        return jdbc.update(CategorySqls.DELETE_BY_ID, params);
    }
    
    public  List<Category> selectLimit(Integer start){
		try {    		
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        return jdbc.query(ProductSqls.SELECT_LIMIT,params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
    }
}