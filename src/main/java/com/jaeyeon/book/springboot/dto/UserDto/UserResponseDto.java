package com.jaeyeon.book.springboot.dto.UserDto;

import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
    }

    public UserResponseDto() {

    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .picture(picture)
                .role(role)
                .build();
    }
}

