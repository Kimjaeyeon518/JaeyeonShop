//package com.jaeyeon.book.springboot.config.auth.dto;
//
//import com.jaeyeon.book.springboot.domain.User;
//import com.jaeyeon.book.springboot.domain.enums.Role;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes;
//    private Long id;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String picture;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, Long id, String nameAttributeKey, String name, String email, String picture) {
//        this.attributes = attributes;
//        this.id = id;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//    }
//
//    // of() : OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 함
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .id((Long) attributes.get("id"))
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .picture((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity() {    // 처음 가입할 때, User 엔티티를 생성
//        return User.builder()
//                .id(id)
//                .name(name)
//                .email(email)
//                .picture(picture)
//                .role(Role.USER)   // 가입할 때의 기본 권한은 USER
//                .build();
//    }
//}