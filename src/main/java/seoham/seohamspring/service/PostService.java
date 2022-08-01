package seoham.seohamspring.service;

import seoham.seohamspring.domain.PostRequest;

public interface PostService {

    //게시물 작성
    void post(PostRequest postRequest);
    PostRequest findByTag(int tagIdx);
    PostRequest findBySender(String sender);
    PostRequest findByDate(int date);



}
