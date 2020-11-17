package com.example.core.order;

import com.example.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
