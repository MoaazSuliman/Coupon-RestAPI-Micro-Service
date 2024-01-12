package com.ecommerce.coupon.controller;

import com.ecommerce.coupon.dto.CouponRequest;
import com.ecommerce.coupon.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-commerce/api/v1/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid CouponRequest couponRequest) {
        return couponService.addCoupon(couponRequest);
    }

    @GetMapping("/use/{code}/for/{orderId}")
    public ResponseEntity<?> useCode(@PathVariable String code  , @PathVariable long orderId) {
        return couponService.getCouponDiscountIfThisCouponAreValid(code , orderId);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return couponService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return couponService.delete(id);
    }

}
