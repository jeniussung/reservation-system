package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.FileDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<Integer> saveFiles(Integer userId, MultipartFile[] files);
    FileDomain getFileLocationById(Integer id);
}
