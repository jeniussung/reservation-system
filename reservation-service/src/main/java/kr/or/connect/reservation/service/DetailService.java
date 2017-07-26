package kr.or.connect.reservation.service;

import java.util.Collection;

import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.DetailBottom;
import kr.or.connect.reservation.domain.DetailTop;
import kr.or.connect.reservation.domain.ImgFile;
import kr.or.connect.reservation.domain.UserComment;

public interface DetailService {
	
	public Collection<DetailTop> getDetailtop(Integer id);
	public ImgFile getFileAddr(Integer id);
	public Collection<UserComment> getUserComment(Integer id);
	public Collection<CommentImage> getUserCommentImage(Integer id);
	public DetailBottom getDetailContent(Integer id);
}
