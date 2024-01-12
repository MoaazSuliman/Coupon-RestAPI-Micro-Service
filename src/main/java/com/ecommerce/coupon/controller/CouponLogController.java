package com.ecommerce.coupon.controller;

import com.ecommerce.coupon.service.CouponLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-commerce/api/v1/coupons/logs")
public class CouponLogController {

    @Autowired
    private CouponLogService couponLogService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return couponLogService.getAll();
    }
}
