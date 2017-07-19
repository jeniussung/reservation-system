package kr.or.connect.reservation.service;

import java.util.Collection;

import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DetailBottom;
import kr.or.connect.reservation.dto.DetailTop;
import kr.or.connect.reservation.dto.ImgFile;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.UserComment;

public interface DetailService {
	
	public Collection<DetailTop> getDetailtop(Integer id);
	public ImgFile getFileAddr(Integer id);
	public Collection<UserComment> getUserComment(Integer id);
	public Collection<CommentImage> getUserCommentImage(Integer id);
	public DetailBottom getDetailContent(Integer id);
}
