package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.domain.MyReservationInfo;
import kr.or.connect.reservation.domain.ProductPrice;
import kr.or.connect.reservation.domain.dto.MyReservationInfoDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sqls.MyReservationInfoSqls.*;

@Repository
public class MyReservationInfoDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<MyReservationInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(MyReservationInfoDto.class);

    public MyReservationInfoDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<MyReservationInfoDto> getMyReservartionInfoList(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.query(SELECT_BY_USER_ID, params, rowMapper);
    }

    public Integer updateReservationType(MyReservationInfo myReservationInfo) {
        SqlParameterSource paramsSource = new BeanPropertySqlParameterSource(myReservationInfo);
        return jdbcTemplate.update(UPDATE_RESERVATION_TYPE_BY_ID, paramsSource);
    }

}
