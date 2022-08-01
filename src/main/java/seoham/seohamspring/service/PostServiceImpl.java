package seoham.seohamspring.service;


import org.springframework.beans.factory.annotation.Autowired;
import seoham.seohamspring.domain.PostRequest;
import seoham.seohamspring.repository.PostRepository;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    private PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //게시물 작성
    @Override
    public void post(PostRequest postRequest) {
        postRepository.save(postRequest);
    }

    @Override
    public PostRequest findByTag(int tagIdx) {
        return postRepository.findByTag(tagIdx);
    }

    @Override
    public PostRequest findBySender(String sender) {
        return postRepository.findBySender(sender);
    }

    @Override
    public PostRequest findByDate(int date) {
        return postRepository.findByDate(date);
    }
}
