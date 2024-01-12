package com.ecommerce.coupon.service;

import com.ecommerce.coupon.dto.CouponRequest;
import org.springframework.http.ResponseEntity;

public interface CouponService {


    public ResponseEntity<?> addCoupon(CouponRequest couponRequest);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> updateCoupon(long id, CouponRequest couponRequest);

    public ResponseEntity<?> getCouponDiscountIfThisCouponAreValid(String code , long orderId);

    public ResponseEntity<?> delete(long couponId);

}
