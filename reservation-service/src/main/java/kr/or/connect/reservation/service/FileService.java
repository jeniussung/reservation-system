package kr.or.connect.reservation.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFiles(MultipartFile[] files);
}
