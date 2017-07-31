package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.config.RootApplicationContextConfig;
import kr.or.connect.reservation.domain.FileDomain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class FileDaoTest {

    @Autowired
    private FileDao fileDao;

    @Test
    public void shouldFileInsert() {
        FileDomain fileDomain = new FileDomain();
        fileDomain.setUserId(1);
        fileDomain.setFileName("36090be1-1b25-45a3-ac51-bd6008e1f18d.jpg");
        fileDomain.setSaveFileName("2017/07/31/36090be1-1b25-45a3-ac51-bd6008e1f18d.jpg");
        Long fileLength = new Long(116767);
        fileDomain.setFileLength(fileLength);
        fileDomain.setContentType("image/jpeg");
        fileDomain.setDeleteFlag(1);
        fileDomain.setCreateDate(new Date());
        fileDomain.setModifyDate(new Date());
        Assert.assertNotNull(fileDao.addFile(fileDomain));
    }
}
