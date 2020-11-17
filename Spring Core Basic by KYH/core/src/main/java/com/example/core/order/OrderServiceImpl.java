package com.example.core.order;

import com.example.core.member.Member;
import com.example.core.member.MemberService;

public class OrderServiceImpl implements OrderService {

    MemberService memberService;
    DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberService memberService, DiscountPolicy discountPolicy) {
        this.memberService = memberService;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int price) {
        Member member = memberService.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, price);
        Order order = new Order(memberId, itemName, price, discountPrice);
        return order;
    }
}
