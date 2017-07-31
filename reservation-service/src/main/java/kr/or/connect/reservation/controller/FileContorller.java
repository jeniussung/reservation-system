package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.domain.dto.ImgFile;
import kr.or.connect.reservation.service.impl.DetailServiceImpl;
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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@PropertySource("classpath:/application.properties")
public class FileContorller {

    @Autowired
    DetailServiceImpl detailServiceImpl;
    private String baseDir = "/Users/odol/Documents/Boost/gavas/files/";
    private String baseDir2 = "/Users/seongjihyeon/desktop/serverfile/s";
    
    @Value("${spring.resources.file-location}")
    private String DownLoadBaseDir;

    @GetMapping
    public ModelAndView fileForm() {
        return new ModelAndView("files");
    }

    @PostMapping
    public ModelAndView create(@RequestParam("title") String title, @RequestParam("file") MultipartFile[] files) {

        if (files != null && files.length > 0) {

            String formattedDate = baseDir2 + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
            File f = new File(formattedDate);
            if (!f.exists()) {
                f.mkdirs();
            }

            for (MultipartFile file : files) {
                String contentType = file.getContentType();
                String name = file.getName();
                String originalFilename = file.getOriginalFilename();
                long size = file.getSize();

                String uuid = UUID.randomUUID().toString();
                String saveFileName = formattedDate + File.separator + uuid;

                // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
                // pk 값은 자동으로 생성되도록 한다.
                System.out.println("title :" + title);
                System.out.println("contentType :" + contentType);
                System.out.println("name :" + name);
                System.out.println("originalFilename : " + originalFilename);
                System.out.println("size : " + size);
                System.out.println("saveFileName : " + saveFileName);

                try (InputStream in = file.getInputStream();
                     FileOutputStream fos = new FileOutputStream(saveFileName)) {

                    int readCount = 0;
                    byte[] buffer = new byte[512];
                    while ((readCount = in.read(buffer)) != -1) {
                        fos.write(buffer, 0, readCount);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return new ModelAndView("redirect:/files");
    }

    @GetMapping("{id}")
    public void downloadReservationUserCommentImage(@PathVariable(name = "id") Integer id, HttpServletResponse response) {
        // id를 이용하여 파일의 정보를 읽어온다.
        // 이 부분은 원래 db에서 읽어오는 것인데 db부분은 생략했다.
        ImgFile addr = detailServiceImpl.getFileAddr(id);

        String originalFilename = "원본파일명";
        String contentType = "image/jpeg";
        // 해당 파일이 이미 존재해야한다.
        String saveFileName = addr.getSave_file_name();

        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        //response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(DownLoadBaseDir+saveFileName);
        if (!readFile.exists()) { // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }

    }
}
