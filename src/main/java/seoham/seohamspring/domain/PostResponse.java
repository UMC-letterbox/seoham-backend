package seoham.seohamspring.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
public class PostResponse {

    private long postIdx;
    private int userIdx;
    private String sender; //보낸이
    private int date; //날짜
    private int tagIdx; //태그
    private String content; //편지 내용
    private int letterIdx; //편지지 번호
}
