package seoham.seohamspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seoham.seohamspring.post.PostRepository;
import seoham.seohamspring.post.PostRepositoryImpl;
import seoham.seohamspring.post.PostService;
import seoham.seohamspring.post.PostServiceImpl;

import javax.sql.DataSource;

@Configuration
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
