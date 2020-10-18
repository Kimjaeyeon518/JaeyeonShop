package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.dto.UserDto.UserRequestDto;
import com.jaeyeon.book.springboot.dto.UserDto.UserSaveRequestDto;
import com.jaeyeon.book.springboot.dto.UserDto.UserUpdateRequestDto;
import com.jaeyeon.book.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserRequestDto requestDto) {

        User user = new User();

        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPicture(requestDto.getPicture());

        return userRepository.save(user).getId();
    }

    @Transactional
    public Long update(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        user.setName(requestDto.getName());
        user.setPicture(requestDto.getPicture());

        return userRepository.save(user).getId();
    }

    @Transactional
    public void delete (Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return user;
    }

    @Transactional
    public Long saveAddr(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        user.setAddr(requestDto.getAddr());
        user.setDetailAddr(requestDto.getDetailAddr());

        return userRepository.save(user).getId();
    }

}