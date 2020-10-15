package com.jaeyeon.book.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private String filename;
    private String url;
}
