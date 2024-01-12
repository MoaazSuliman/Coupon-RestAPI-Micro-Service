package com.ecommerce.coupon.repository;

import com.ecommerce.coupon.model.CouponLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponLogRepository  extends JpaRepository<CouponLog , Long> {
}
