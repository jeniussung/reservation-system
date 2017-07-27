package kr.or.connect.reservation.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DetailDao;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.DetailBottom;
import kr.or.connect.reservation.domain.dto.DetailTop;
import kr.or.connect.reservation.domain.dto.ImgFile;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import kr.or.connect.reservation.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	DetailDao detailDao;

	@Override
	public Collection<DetailTop> getDetailtop(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectDetailTop(id);
	}

	@Override
	public ImgFile getFileAddr(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectFileAddr(id);
	}

	@Override
	public Collection<UserCommentDto> getUserComment(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectComment(id);
	}

	@Override
	public Collection<CommentImage> getUserCommentImage(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectCommentImg(id);
	}

	@Override
	public DetailBottom getDetailContent(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectDetailContent(id);
	}
}
