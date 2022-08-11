package seoham.seohamspring.post.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postIdx;

    private String sender; //보낸이
    private int date; //날짜
    private int tagIdx; //태그
    private String content; //편지 내용
    private int letterIdx; //편지지 번호



}
