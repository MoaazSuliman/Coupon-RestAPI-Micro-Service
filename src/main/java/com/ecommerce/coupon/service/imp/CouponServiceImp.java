package com.ecommerce.coupon.service.imp;

import com.ecommerce.coupon.dto.CouponRequest;
import com.ecommerce.coupon.exception.RecordNotFoundException;
import com.ecommerce.coupon.model.Coupon;
import com.ecommerce.coupon.model.CouponLog;
import com.ecommerce.coupon.repository.CouponRepository;
import com.ecommerce.coupon.service.CouponLogService;
import com.ecommerce.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CouponServiceImp implements CouponService {


    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponLogService couponLogService;

    @Override
    public ResponseEntity<?> addCoupon(CouponRequest couponRequest) {
        // convert request to entity
        // save the entity and return it
        return new ResponseEntity<>(couponRepository.save(Coupon.convertRequestToEntity(couponRequest)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(couponRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCoupon(long id, CouponRequest couponRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCouponDiscountIfThisCouponAreValid(String code, long orderId) {
        Coupon coupon = couponRepository.findByCode(code).
                orElseThrow(
                        () -> new RecordNotFoundException("This Coupon Not Exist In Our Coupons")
                );
        // now code are exist and we will see if numbers is ok or not.
        // then we will check if he is in the valid date or not.
        if (coupon.getNumbers() > 0) {
            if ((LocalDate.now().isAfter(coupon.getFromDate()) && LocalDate.now().isBefore(coupon.getToDate()))) {
                decreaseCouponNumber(coupon);// decrease number of using for this coupon.
                couponLogService.insert(CouponLog.getDoneLog(orderId, coupon.getId()));
                return new ResponseEntity<>(coupon.getDiscount(), HttpStatus.OK);
            } else {
                couponLogService.insert(CouponLog.getFailLog(orderId, coupon.getId(), "This Date Are Not In Coupon Validation Dates"));
                return new ResponseEntity<>("This Coupon Are Not Valid Today", HttpStatus.BAD_REQUEST);
            }
        } else {
            couponLogService.insert(CouponLog.getFailLog(orderId, coupon.getId(), "This Coupon Is Finished"));
            return new ResponseEntity<>("This Coupon is Finished ", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> delete(long couponId) {
        couponRepository.deleteById(couponId);
        return new ResponseEntity<>("This Coupon Is Deleted Successfully", HttpStatus.ACCEPTED);
    }


    private Coupon getById(long couponId) {
        return couponRepository.findById(couponId).
                orElseThrow(
                        () -> new RecordNotFoundException("This Id Not Exist In Our Database")
                );
    }

    private void decreaseCouponNumber(Coupon coupon) {
        coupon.setNumbers(coupon.getNumbers() - 1);
        couponRepository.save(coupon);
    }
}
