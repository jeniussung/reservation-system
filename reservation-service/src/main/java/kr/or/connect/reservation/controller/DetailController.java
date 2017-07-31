package kr.or.connect.reservation.controller;

import java.util.Collection;
import java.util.List;

import kr.or.connect.reservation.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.domain.dto.CommentImage;
import kr.or.connect.reservation.domain.dto.DetailBottom;
import kr.or.connect.reservation.domain.dto.DetailTop;
import kr.or.connect.reservation.domain.dto.UserCommentDto;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.impl.DetailServiceImpl;

@RestController
@RequestMapping("/details")
public class DetailController {

    @Autowired
    DetailService detailServiceImpl;

    @GetMapping("/top/{id}")
    public List<DetailTop> getDetailTop(@PathVariable Integer id) {
        return detailServiceImpl.getDetailtop(id);
    }

    @GetMapping("/comment/{id}")
    public List<UserCommentDto> getUserComment(@PathVariable Integer id) {
        return detailServiceImpl.getUserComment(id);
    }

    @GetMapping("/image/{id}")
    public List<CommentImage> getCommentImage(@PathVariable Integer id) {
        return detailServiceImpl.getUserCommentImage(id);
    }

    @GetMapping("/content/{id}")
    public DetailBottom getDetailBottomContent(@PathVariable Integer id) {
        return detailServiceImpl.getDetailContent(id);
    }

}
