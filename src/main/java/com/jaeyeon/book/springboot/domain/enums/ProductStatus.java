package com.jaeyeon.book.springboot.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

    SOLDOUT("품절"),
    SALE("판매중");

    private final String status;

}
