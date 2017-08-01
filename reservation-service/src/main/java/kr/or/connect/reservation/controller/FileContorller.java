package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.domain.FileDomain;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/files")
@PropertySource("classpath:/application.properties")
public class FileContorller {

    @Autowired
    private FileService fileService;

    @Value("${spring.resources.file-location}")
    private String downLoadBaseDir;

    @GetMapping
    public ModelAndView fileForm() {
        return new ModelAndView("files");
    }

    @PostMapping
    public ModelAndView create(@RequestParam("title") String title, @RequestParam("file") MultipartFile[] files) {
        fileService.saveFiles(1, files);
        return new ModelAndView("redirect:/files");
    }

    @GetMapping("{id}")
    public void downloadReservationUserCommentImage(@PathVariable("id") Integer id, HttpServletResponse response) {
        FileDomain fileLocation = fileService.getFileLocationById(id);

        String originalFilename = "원본파일명";
        String contentType = "image/jpeg";
        String saveFileName = fileLocation.getSaveFileName();

        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        File readFile = new java.io.File(downLoadBaseDir + saveFileName);
        if (!readFile.exists()) { // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        try (FileInputStream fileInputStream = new FileInputStream(readFile)) {
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
