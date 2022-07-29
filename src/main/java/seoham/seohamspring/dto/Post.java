package seoham.seohamspring.dto;

public class Post {

    private long postIdx;
    private String userIdx; //유저아이디
    private String sender; //보낸이
    private int date; //날짜
    private String tagIdx; //태그
    private String content; //편지 내용
    private int letterIdx; //편지지 번호

    public long getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(long postIdx) {
        this.postIdx = postIdx;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTagIdx() {
        return tagIdx;
    }

    public void setTagIdx(String tagIdx) {
        this.tagIdx = tagIdx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLetterIdx() {
        return letterIdx;
    }

    public void setLetterIdx(int letterIdx) {
        this.letterIdx = letterIdx;
    }


    public Post(int postIdx, String sender, int date, String tagIdx, String content, int letterIdx) {
        super();
        this.postIdx = postIdx;
        this.sender = sender;
        this.date = date;
        this.tagIdx = tagIdx;
        this.content = content;
        this.letterIdx = letterIdx;
    }
}
