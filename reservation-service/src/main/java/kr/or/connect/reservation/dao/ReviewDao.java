package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dao.sqls.DetailSqls;
import kr.or.connect.reservation.dao.sqls.ReviewSqls;
import kr.or.connect.reservation.domain.Review;
import kr.or.connect.reservation.domain.ReviewImage;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.ReviewInfoDto;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert simpleJdbcInsert;
    private SimpleJdbcInsert simpleJdbcInsertImageTable;
    private RowMapper<Review> rowMapper = BeanPropertyRowMapper.newInstance(Review.class);

    public ReviewDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment")
                .usingGeneratedKeyColumns("id");
        this.simpleJdbcInsertImageTable = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment_image")
                .usingGeneratedKeyColumns("id");

    }

    public Integer insert(Review review) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(review);
        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    public List<UserCommentDto> selectComment(Integer id, Integer start) {
        try {
            RowMapper<UserCommentDto> rowMapper = BeanPropertyRowMapper.newInstance(UserCommentDto.class);
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("start", start);
            return jdbc.query(ReviewSqls.SELECT_REVIEW_COMMENT_INFO, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<CommentImage> selectCommentImg(Integer id) {
        try {
            RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.query(ReviewSqls.SELECT_REVIEW_COMMENT_IMAGE, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public ReviewInfoDto selectCountInfo(Integer id) {
        try {
            RowMapper<ReviewInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(ReviewInfoDto.class);
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.queryForObject(ReviewSqls.SELECT_REVIEW_COUNT_INFO, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer insertWithFiles(Review review, List<Integer> fileIdList) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(review);
        Integer reviewId = simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
        for(Integer fileId : fileIdList) {
            ReviewImage reviewImage = new ReviewImage();
            reviewImage.setReservationUserCommentId(reviewId);
            reviewImage.setFileId(fileId);
            SqlParameterSource reviewImageParam = new BeanPropertySqlParameterSource(reviewImage);
            simpleJdbcInsertImageTable.execute(reviewImageParam);
        }
        return reviewId;
    }
}
