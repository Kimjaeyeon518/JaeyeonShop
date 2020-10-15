package com.jaeyeon.book.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@Entity
public class FileModel extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    private String path;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    public FileModel(String name, String path, String mimetype, byte[] pic) {
        this.name = name;
        this.path = path;
        this.mimetype = mimetype;
        this.pic = pic;
    }
}
