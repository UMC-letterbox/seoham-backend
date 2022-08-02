package seoham.seohamspring.service;

import seoham.seohamspring.domain.Post;

import java.util.Optional;

public interface PostService {

    //게시물 작성
    void post(Post post);
    //게시물 삭제
    void delete(int postIdx);
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findBySender(String sender);
    Optional<Post> findByDate(int date);
    Optional<Post> findByPostIdx(int postIdx);



}
