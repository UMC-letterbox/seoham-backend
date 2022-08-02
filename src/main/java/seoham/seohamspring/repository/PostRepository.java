package seoham.seohamspring.repository;

import seoham.seohamspring.domain.Post;

import java.util.Optional;

public interface PostRepository {

    void save(Post post);
    void delete(int postIdx);
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostId(int postIdx);
}
