package com.ecommerce.coupon.service.imp;

import com.ecommerce.coupon.model.CouponLog;
import com.ecommerce.coupon.repository.CouponLogRepository;
import com.ecommerce.coupon.service.CouponLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CouponLogServiceImp implements CouponLogService {
    @Autowired
    private CouponLogRepository couponLogRepository;


    @Override
    public void insert(CouponLog log) {
        couponLogRepository.save(log);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(couponLogRepository.findAll(), HttpStatus.OK);
    }
}
