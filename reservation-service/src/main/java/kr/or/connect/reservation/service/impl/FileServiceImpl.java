package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.domain.FileDomain;
import kr.or.connect.reservation.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.io.File.separator;

@Service
@PropertySource("classpath:/application.properties")
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;


    @Value("${spring.resources.file-location}")
    private String baseDir;

    public FileDomain getFileLocationById(Integer id) {
        return fileDao.selectFileAddr(id);
    }

    @Override
    public List<Integer> saveFiles(Integer userId, MultipartFile[] files) {
        List<Integer> createdFileIdList = new ArrayList<>();
        if (files != null && files.length > 0) {
            String simpleDateFormat = new SimpleDateFormat("yyyy" + separator + "MM" + separator + "dd").format(new Date());
            String saveDir = baseDir + simpleDateFormat;
            File f = new File(saveDir);
            if (!f.exists()) {
                f.mkdirs();
            }

            for (MultipartFile file : files) {
                String contentType = file.getContentType();
                String originalFilename = file.getOriginalFilename();
                Long size = file.getSize();
                String uuid = UUID.randomUUID().toString();
                String saveFileName = saveDir + separator + uuid;

                try (InputStream in = file.getInputStream();
                     FileOutputStream fos = new FileOutputStream(saveFileName)) {

                    int readCount;
                    byte[] buffer = new byte[512];
                    while ((readCount = in.read(buffer)) != -1) {
                        fos.write(buffer, 0, readCount);
                    }
                    FileDomain fileDomain = new FileDomain();
                    fileDomain.setUserId(userId);
                    fileDomain.setFileName(originalFilename);
                    fileDomain.setSaveFileName(simpleDateFormat + separator + uuid);
                    fileDomain.setFileLength(size);
                    fileDomain.setContentType(contentType);
                    fileDomain.setDeleteFlag(1);
                    fileDomain.setCreateDate(new Date());
                    fileDomain.setModifyDate(new Date());
                    createdFileIdList.add(fileDao.addFile(fileDomain));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return createdFileIdList;
    }
}


