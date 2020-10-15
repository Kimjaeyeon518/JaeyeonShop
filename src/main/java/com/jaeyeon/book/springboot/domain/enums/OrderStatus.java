package com.jaeyeon.book.springboot.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    COMPLETE("결제완료"), CANCEL("결제취소"), REFUND("환불"), WAIT("배송중");

    private final String status;

}
