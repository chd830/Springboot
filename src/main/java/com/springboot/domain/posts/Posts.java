package com.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 어노테이션. 클래스 내 모든 필드의 Getter메소드 자동생성
@NoArgsConstructor // 롬복 어노테이션. 기본 생성자 자동추가
@Entity // JPA 어노테이션. 테이블과 링크될 클래스임을 나타냄. 카멜케이스를 언더스코어 네이밍으로 테이블을 매칭. 절대 Setter 메소드를 만들지 않음.
public class Posts {

    @Id //테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
