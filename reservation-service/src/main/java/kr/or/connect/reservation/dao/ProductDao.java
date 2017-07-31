package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kr.or.connect.reservation.dao.sqls.ProductSqls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Product;

import static kr.or.connect.reservation.dao.sqls.ProductSqls.*;
@PropertySource("classpath:/application.properties")
@Repository
public class ProductDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
    
    @Value("${spring.resources.product-limit}")
	private Long limit;

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("category")
                .usingGeneratedKeyColumns("id");
    }

    public List<Product> selectLimit(Integer start, Integer id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("start", start);
            params.put("category_id", id);
            params.put("limit", limit);
            return jdbc.query(SELECT_CATEGORY_ID, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Product> selectAll(Integer start) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("limit", limit);
        return jdbc.query(ProductSqls.SELECT_LIMIT, params, rowMapper);
    }

    public int selectCount() {
        Map<String, Object> params = Collections.emptyMap();
        return jdbc.queryForObject(ProductSqls.SELECT_COUNT, params, Integer.class);
    }

    public int selectCountId(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("category_id", id);
        return jdbc.queryForObject(ProductSqls.SELECT_COUNT_ID, params, Integer.class);
    }
}