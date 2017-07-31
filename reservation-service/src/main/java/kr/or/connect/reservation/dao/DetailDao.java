package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kr.or.connect.reservation.dao.sqls.DetailSqls;
import kr.or.connect.reservation.domain.FileDomain;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.DetailBottom;
import kr.or.connect.reservation.domain.dto.DetailTop;
import kr.or.connect.reservation.domain.dto.UserCommentDto;

@Repository
public class DetailDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<DetailTop> rowMapper = BeanPropertyRowMapper.newInstance(DetailTop.class);


    public DetailDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<DetailTop> selectDetailTop(Integer id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.query(DetailSqls.SELECT_DETAIL_TOP, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<UserCommentDto> selectComment(Integer id) {
        try {
            RowMapper<UserCommentDto> rowMapper = BeanPropertyRowMapper.newInstance(UserCommentDto.class);
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.query(DetailSqls.SELECT_COMMENT_INFO, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<CommentImage> selectCommentImg(Integer id) {
        try {
            RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.query(DetailSqls.SELECT_COMMENT_IMAGE, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public DetailBottom selectDetailContent(Integer id) {
        try {
            RowMapper<DetailBottom> rowMapper = BeanPropertyRowMapper.newInstance(DetailBottom.class);
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.queryForObject(DetailSqls.SELEECT_DETAIL_BOTTOM_CONTENT, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
