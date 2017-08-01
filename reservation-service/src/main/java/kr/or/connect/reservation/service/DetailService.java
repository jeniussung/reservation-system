package kr.or.connect.reservation.service;

import java.io.File;
import java.util.Collection;
import java.util.List;

import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.DetailBottom;
import kr.or.connect.reservation.domain.dto.DetailTop;
import kr.or.connect.reservation.domain.dto.UserCommentDto;

public interface DetailService {
	
	List<DetailTop> getDetailtop(Integer id);
	List<UserCommentDto> getUserComment(Integer id);
	List<CommentImage> getUserCommentImage(Integer id);
	DetailBottom getDetailContent(Integer id);
}
