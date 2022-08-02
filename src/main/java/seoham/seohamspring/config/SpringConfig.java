package seoham.seohamspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import seoham.seohamspring.repository.PostRepository;
import seoham.seohamspring.repository.PostRepositoryImpl;
import seoham.seohamspring.service.PostService;
import seoham.seohamspring.service.PostServiceImpl;

import javax.sql.DataSource;

public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PostService postService(){
        return new PostServiceImpl(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepositoryImpl(dataSource);
    }
}
