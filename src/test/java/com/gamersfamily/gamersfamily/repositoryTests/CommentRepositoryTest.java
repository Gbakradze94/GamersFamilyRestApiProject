package com.gamersfamily.gamersfamily.repositoryTests;

import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.CommentRepository;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void saveTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = User.builder()
                .id(34)
                .email("email@mail.ru")
                .username("robert23")
                .password("password")
                .build();
        userRepository.save(user);
        News news = News.builder()
                .id(4)
                .body("bodyy")
                .name("newsName")
                .build();
        newsRepository.save(news);
        Comment comment = Comment.builder()
                .id(33)
                .body("mycomment")
                .author(user)
                .news(news)
                .createdAt(localDateTime).build();
        Comment commentSaved = commentRepository.save(comment);
        Assert.assertEquals(Long.valueOf(33).toString(), commentSaved.getId().toString());
        Assert.assertEquals(user, commentSaved.getAuthor());
        Assert.assertEquals(news, commentSaved.getNews());
        System.out.println(commentSaved);
        Assert.assertEquals("mycomment", commentSaved.getBody());


    }


    @Test
    public void findAllTest() {
        List<Comment> commentsList = commentRepository.findByNewsId(200);
        Comment comment = commentsList.get(0);
        Assert.assertEquals(Long.valueOf(200).toString(), comment.getNews().getId().toString());
        Assert.assertEquals(Long.valueOf(100).toString(), comment.getAuthor().getId().toString());
        Assert.assertEquals("first comment to this news", comment.getBody());

    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCommentTest() {
        commentRepository.deleteById(300L);
        Comment comment = commentRepository.findById(300L).
                orElseThrow(() -> {
                    throw new IllegalArgumentException();
                });

    }


}
