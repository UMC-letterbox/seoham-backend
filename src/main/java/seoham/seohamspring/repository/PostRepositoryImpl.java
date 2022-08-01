package seoham.seohamspring.repository;

import seoham.seohamspring.domain.PostRequest;

import java.util.HashMap;
import java.util.Map;

public class PostRepositoryImpl implements PostRepository {

    private static Map<Long, PostRequest> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(PostRequest postRequest) {
        store.put(postRequest.getPostIdx(), postRequest);
    }

    @Override
    public PostRequest findByTag(int tagIdx) {
        return store.get(tagIdx);
    }

    @Override
    public PostRequest findByDate(int date) {
        return store.get(date);
    }

    @Override
    public PostRequest findBySender(String sender) {
        return store.get(sender);
    }

    @Override
    public PostRequest findByPostId(int postIdx) {
        return store.get(postIdx);
    }
}
