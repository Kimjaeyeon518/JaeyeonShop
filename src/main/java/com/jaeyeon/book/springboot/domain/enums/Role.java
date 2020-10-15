package com.jaeyeon.book.springboot.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_MANAGER", "관리자");

    private final String key;
    private final String title;

}