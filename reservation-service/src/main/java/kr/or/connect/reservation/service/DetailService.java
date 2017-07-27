package kr.or.connect.reservation.service;

import java.util.Collection;

import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.DetailBottom;
import kr.or.connect.reservation.domain.dto.DetailTop;
import kr.or.connect.reservation.domain.dto.ImgFile;
import kr.or.connect.reservation.domain.dto.UserCommentDto;

public interface DetailService {
	
	public Collection<DetailTop> getDetailtop(Integer id);
	public ImgFile getFileAddr(Integer id);
	public Collection<UserCommentDto> getUserComment(Integer id);
	public Collection<CommentImage> getUserCommentImage(Integer id);
	public DetailBottom getDetailContent(Integer id);
}
