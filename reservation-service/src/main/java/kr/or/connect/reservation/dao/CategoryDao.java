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
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);


    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("category")
                .usingGeneratedKeyColumns("id");
    }

    public Long insert(Category member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        return insertAction.executeAndReturnKey(params).longValue();
    }


    public Category selectById(long id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.queryForObject(CategorySqls.SELECT_BY_ID, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Category> selectAll() {
        return jdbc.query(CategorySqls.SELECT, rowMapper);
    }

    public Integer update(Category member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        return jdbc.update(CategorySqls.UPDATE_BY_ID, params);
    }

    public Integer deleteById(Integer id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.update(CategorySqls.DELETE_BY_ID, params);
    }

    public List<Category> selectLimit(Integer start) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("start", start);
            return jdbc.query(ProductSqls.SELECT_LIMIT, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}