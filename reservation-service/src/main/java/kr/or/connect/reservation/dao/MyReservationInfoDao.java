package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.MyReservationInfoSqls.SELECT_BY_USER_ID;
import static kr.or.connect.reservation.dao.sqls.MyReservationInfoSqls.UPDATE_RESERVATION_TYPE_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.MyReservationInfo;
import kr.or.connect.reservation.domain.dto.MyReservationInfoDto;

@Repository
public class MyReservationInfoDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertAction;
    private RowMapper<MyReservationInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(MyReservationInfoDto.class);
    private RowMapper<MyReservationInfo> myReservationInfoRowMapper = BeanPropertyRowMapper.newInstance(MyReservationInfo.class);
    
    public MyReservationInfoDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
        		.withTableName("reservation_info")
        		.usingGeneratedKeyColumns("id");
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
    
    public Integer insert(MyReservationInfo myReservationInfo){
    	SqlParameterSource params = new BeanPropertySqlParameterSource(myReservationInfo);
    	return insertAction.executeAndReturnKey(params).intValue();
    }

}
