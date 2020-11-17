package com.example.core.order;

import com.example.core.AppConfig;
import com.example.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    AppConfig appConfig = new AppConfig();

    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    public void createOrder() {
        Member member1 = new Member(1L, "userA", Grade.VIP);
        memberService.join(member1);
        Order order1 = orderService.createOrder(1L, "itemA", 20000);

        Assertions.assertThat(order1.getDiscountPrice()).isEqualTo(1000);

        Member member2 = new Member(2L, "userB", Grade.BASIC);
        memberService.join(member2);
        Order order2 = orderService.createOrder(2L, "itemB", 20000);

        Assertions.assertThat(order2.getDiscountPrice()).isEqualTo(0);
    }
}
