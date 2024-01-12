package com.ecommerce.coupon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "coupon_logs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CouponLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponLogId;

    private long orderId;
    private long couponId;
    private LocalDate date;
    private String message;
    private CouponStatus status;

    public static CouponLog getDoneLog(long orderId, long couponId) {
        return CouponLog
                .builder()
                .orderId(orderId)
                .couponId(couponId)
                .message("DONE Ya Za3eeeeeeeeeeeeeeeeeeeeeeeeeeeeem")
                .date(LocalDate.now())
                .status(CouponStatus.DONE)
                .build();
    }

    public static CouponLog getFailLog(long orderId, long couponId, String message) {
        return CouponLog
                .builder()
                .orderId(orderId)
                .couponId(couponId)
                .message(message)
                .date(LocalDate.now())
                .status(CouponStatus.FAIL)
                .build();
    }


}
