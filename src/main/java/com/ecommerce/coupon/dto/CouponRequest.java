package com.ecommerce.coupon.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CouponRequest {


    @NotNull(message = "discount Must Not Be Null")
    @JsonProperty("coupon_discount_percentage")
    @Min(value = 1, message = "numbers must be greater than or equal to 1")
    @Max(value = 100, message = "numbers must be less than or equal to " + 100)
    private double discount;


    @NotNull(message = "from_date Must Not Be Null")
    @JsonProperty("from_date")
    private LocalDate fromDate;


    @NotNull(message = "to_date Must Not Be Null")
    @JsonProperty("to_date")
    private LocalDate toDate;


    @Min(value = 1, message = "numbers must be greater than or equal to 1")
    @Max(value = Long.MAX_VALUE, message = "numbers must be less than or equal to " + Long.MAX_VALUE)
    @JsonProperty("coupon_numbers")
    @NotNull(message = "numbers Must Not Be Null")
    private long numbers;


    @AssertTrue(message = "fromDate must be after toDate")
    private boolean isFromDateAfterToDate() {
        // here i can check for any custom validation i need it.
        // if that come to false that will throw MethodArgumentNotValidExceptionClass
        // MethodArgumentNotValidExceptionClass ==> In the RestControllerAdvice.

        return fromDate == null || toDate == null || fromDate.isBefore(toDate);
    }
}
