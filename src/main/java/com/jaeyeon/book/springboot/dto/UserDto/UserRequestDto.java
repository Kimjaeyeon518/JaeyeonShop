package com.jaeyeon.book.springboot.dto.UserDto;

import com.jaeyeon.book.springboot.domain.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String name;
    private String email;
    private String picture;
    private Role role;
    private String addr;
    private String detailAddr;
}
