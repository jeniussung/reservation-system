package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String saveFiles(MultipartFile[] files) {
        return null;
    }
}
