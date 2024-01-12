package com.ecommerce.coupon.model;


import com.ecommerce.coupon.dto.CouponRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double discount;

    private LocalDate fromDate;

    private LocalDate toDate;

    private long numbers;

    @Column(unique = true)
    private String code;


    public static Coupon convertRequestToEntity(CouponRequest couponRequest) {
        return Coupon.
                builder()
                .discount(couponRequest.getDiscount())
                .fromDate(couponRequest.getFromDate())
                .toDate(couponRequest.getToDate())
                .numbers(couponRequest.getNumbers())
                .code(Coupon.generateUniqueCode())
                .build();
    }

    private static String generateUniqueCode() {
        // generate unique code with 10 chars
        return UUID.randomUUID().toString().substring(0, 10);
    }

}
