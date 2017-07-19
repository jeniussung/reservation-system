package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DetailBottom;
import kr.or.connect.reservation.dto.DetailTop;
import kr.or.connect.reservation.dto.ImgFile;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.UserComment;

@Repository
public class DetailDao {
	
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private RowMapper<DetailTop> rowMapper = BeanPropertyRowMapper.newInstance(DetailTop.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.

    // Spring은 생성자를 통하여 주입을 하게 된다. 빈을 따로 등록하지 않고 선언해서 사용했음.
    
    public DetailDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
    }
    
    public List<DetailTop> selectDetailTop(Integer id){
		try {    		
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return jdbc.query(DetailSqls.SELECT_DETAIL_TOP,params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
    }
    
    public ImgFile selectFileAddr(Integer id) {
    	try {    		
    		    RowMapper<ImgFile> rowMapper = BeanPropertyRowMapper.newInstance(ImgFile.class);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            return jdbc.queryForObject(DetailSqls.SELECT_FILE_ADDR, params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
    		}catch(EmptyResultDataAccessException e)
    		{
    			return null;
    		}
    }
    
    public List<UserComment> selectComment(Integer id){
    	try {    		
    		    RowMapper<UserComment> rowMapper = BeanPropertyRowMapper.newInstance(UserComment.class);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            return jdbc.query(DetailSqls.SELECT_COMMENT_INFO,params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
    		}catch(EmptyResultDataAccessException e)
    		{
    			return null;
    		}
    }
    
    public List<CommentImage> selectCommentImg(Integer id){
    	try {    		
    		    RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            return jdbc.query(DetailSqls.SELECT_COMMENT_IMAGE,params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
    		}catch(EmptyResultDataAccessException e)
    		{
    			return null;
    		}
    }
    
    public DetailBottom selectDetailContent(Integer id) {
    	try {    		
    		    RowMapper<DetailBottom> rowMapper = BeanPropertyRowMapper.newInstance(DetailBottom.class);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            return jdbc.queryForObject(DetailSqls.SELEECT_DETAIL_BOTTOM_CONTENT, params,rowMapper); //rowMapper는 컬름을 담을 때만 필요하다.
    		}catch(EmptyResultDataAccessException e)
    		{
    			return null;
    		}
    }
}
