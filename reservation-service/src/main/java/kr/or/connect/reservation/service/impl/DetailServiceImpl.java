package kr.or.connect.reservation.service.impl;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DetailDao;
import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.DetailBottom;
import kr.or.connect.reservation.domain.dto.DetailTop;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import kr.or.connect.reservation.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	private	DetailDao detailDao;

	@Override
	public List<DetailTop> getDetailtop(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectDetailTop(id);
	}

	@Override
	public List<UserCommentDto> getUserComment(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectComment(id);
	}

	@Override
	public List<CommentImage> getUserCommentImage(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectCommentImg(id);
	}

	@Override
	public DetailBottom getDetailContent(Integer id) {
		// TODO Auto-generated method stub
		return detailDao.selectDetailContent(id);
	}
}
