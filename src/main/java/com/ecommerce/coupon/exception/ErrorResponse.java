package com.ecommerce.coupon.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class ErrorResponse {


    private String message;

    private LocalDate date;

    public ErrorResponse(String message) {
        this.message = message;
        this.date = LocalDate.now();
    }
}
