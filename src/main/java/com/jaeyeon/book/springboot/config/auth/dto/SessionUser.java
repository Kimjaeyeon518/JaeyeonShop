package com.jaeyeon.book.springboot.config.auth.dto;

import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.domain.enums.Role;
import lombok.Getter;

import java.io.Serializable;

// 인증된 사용자 정보만 필요하므로, 그 외에 필요한 정보들은 없으니 name, email, picture만 필드로 선언
@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }
}