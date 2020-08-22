package com.jaeyeon.book.springboot.dto;

import com.jaeyeon.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);     // dto의 name에 이곳에서 설정한 name값이 잘 들어갔는지 검증
        assertThat(dto.getAmount()).isEqualTo(amount);  // dto의 amount에 이곳에서 설정한 amount값이 잘 들어갔는지 검증
    }
}
