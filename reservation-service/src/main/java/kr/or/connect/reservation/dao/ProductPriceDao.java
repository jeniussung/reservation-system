package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.domain.ProductPrice;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.ProductPriceSqls.SELECT_BY_PRODUCT_ID;

@Repository
public class ProductPriceDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

    public ProductPriceDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProductPrice> getProductPriceList(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.query(SELECT_BY_PRODUCT_ID, params, rowMapper);
    }
}
