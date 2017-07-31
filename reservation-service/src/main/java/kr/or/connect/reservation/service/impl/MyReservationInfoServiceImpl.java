package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.MyReservationInfoDao;
import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.domain.MyReservationInfo;
import kr.or.connect.reservation.domain.ProductPrice;
import kr.or.connect.reservation.domain.dto.MyReservationInfoDto;
import kr.or.connect.reservation.service.MyReservationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyReservationInfoServiceImpl implements MyReservationInfoService {
    @Autowired
    private ProductPriceDao productPriceDao;
    @Autowired
    private MyReservationInfoDao myReservationInfoDao;

    @Override
    public List<MyReservationInfoDto> getReservationInfoList(Integer id) {
        List<MyReservationInfoDto> myReservationInfoDtoList = myReservationInfoDao.getMyReservartionInfoList(id);
        setTotalPrice(myReservationInfoDtoList);
        return myReservationInfoDtoList;
    }

    @Override
    public Integer updateResrvationType(MyReservationInfo myReservationInfo) {
        return myReservationInfoDao.updateReservationType(myReservationInfo);
    }

    private void setTotalPrice(List<MyReservationInfoDto> myReservationInfoDtoList) {
        Map<Integer, List<ProductPrice>> productPriceMap = new HashMap<>();

        for (MyReservationInfoDto myReservationInfoDto : myReservationInfoDtoList) {
            Double totalPrice = new Double(0);
            Integer productId = myReservationInfoDto.getProductId();

            if (productPriceMap.get(productId) == null) {
                productPriceMap.put(productId, productPriceDao.getProductPriceList(productId));
            }
            List<ProductPrice> productPriceList = productPriceMap.get(productId);
            for (ProductPrice productPrice : productPriceList) {
                Integer count;
                if (productPrice.getPriceType().equals(1) && myReservationInfoDto.getGeneralTicketCount() != null) {
                    count = myReservationInfoDto.getGeneralTicketCount();
                } else if (productPrice.getPriceType().equals(2) && myReservationInfoDto.getYouthTicketCount() != null) {
                    count = myReservationInfoDto.getYouthTicketCount();
                } else if (productPrice.getPriceType().equals(3) && myReservationInfoDto.getChildTicketCount() != null) {
                    count = myReservationInfoDto.getChildTicketCount();
                } else {
                    throw new IllegalArgumentException();
                }
                Double discountRate = productPrice.getDiscountRate().doubleValue();
                totalPrice += (productPrice.getPrice() * (1 - discountRate)) * count;
            }

            myReservationInfoDto.setTotalPrice(totalPrice.longValue());
        }
    }
}

