package com.example.core.order;

import com.example.core.member.Grade;
import com.example.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountPrice = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountPrice;
        } else {
            return 0;
        }
    }
}
