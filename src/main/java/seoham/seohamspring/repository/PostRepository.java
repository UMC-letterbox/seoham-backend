package seoham.seohamspring.repository;

import seoham.seohamspring.domain.PostRequest;

public interface PostRepository {

    void save(PostRequest postRequest);
    PostRequest findByTag(int tagIdx);
    PostRequest findByDate(int date);
    PostRequest findBySender(String sender);
    PostRequest findByPostId(int postIdx);
}
