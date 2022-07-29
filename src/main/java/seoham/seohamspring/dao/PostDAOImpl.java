package seoham.seohamspring.dao;

import seoham.seohamspring.dto.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PostDAOImpl implements PostDAO {

    private static Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setPostIdx(++sequence);
        store.put(post.getPostIdx(), post);
        return post;
    }

    @Override
    public Optional<Post> findByTag(int tagIdx) {
        return Optional.ofNullable(store.get(tagIdx));
    }

    @Override
    public Optional<Post> findByDate(int date) {
        return Optional.ofNullable(store.get(date));
    }

    @Override
    public Optional<Post> findBySender(String sender) {
        return Optional.ofNullable(store.get(sender));
    }

    @Override
    public Optional<Post> findByPostId(int postIdx) {
        return Optional.ofNullable(store.get(postIdx));
    }
}
