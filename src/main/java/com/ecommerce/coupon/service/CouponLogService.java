package com.ecommerce.coupon.service;

import com.ecommerce.coupon.model.Coupon;
import com.ecommerce.coupon.model.CouponLog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CouponLogService {


    public void insert(CouponLog log);

    public ResponseEntity<?> getAll();
}
