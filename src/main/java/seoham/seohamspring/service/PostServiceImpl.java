package seoham.seohamspring.service;


import org.springframework.beans.factory.annotation.Autowired;
import seoham.seohamspring.dao.PostDAO;

public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    @Autowired
    private PostServiceImpl(PostDAO postDAO){
        this.postDAO = postDAO;
    }
}
