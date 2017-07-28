package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kr.or.connect.reservation.dao.sqls.ReserveSqls;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.ReserveInfo;

@Repository
public class ReserveDao {
	
		private NamedParameterJdbcTemplate jdbc;
	    private RowMapper<ReserveInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReserveInfo.class);
	    
	    public ReserveDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	    }
	    
	    public List<ReserveInfo> selectReserveInfo(Integer id){
	    	 	Map<String, Object> params = new HashMap<>();
	        params.put("id", id);
	        return jdbc.query(ReserveSqls.SELECT_RESERVE_INFO,params,rowMapper);
	    }
}
