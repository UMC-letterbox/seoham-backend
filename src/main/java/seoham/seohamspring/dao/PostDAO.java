package seoham.seohamspring.dao;

import seoham.seohamspring.dto.Post;

import java.util.Optional;

public interface PostDAO {

    Post save(Post post);
    Optional<Post> findByTag(int tagIdx);
    Optional<Post> findByDate(int date);
    Optional<Post> findBySender(String sender);
    Optional<Post> findByPostId(int postIdx);
}
