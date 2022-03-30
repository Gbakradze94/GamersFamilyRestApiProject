package com.gamersfamily.gamersfamily.serviceTests;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.dto.RatingOutputDto;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import com.gamersfamily.gamersfamily.service.RatingService;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application-test.properties")
public class RatingServiceTest {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingRepository ratingRepo;

//    @Test
//    @Transactional
//    @WithMockUser(username = "email@gmail.ru")
//    public void ba_saveRatingTestWhenUserHasAlreadyRatedTheNews() {
//        RatingDto dto = RatingDto.builder().rate(Rate.FIVE)
//                .newsId(200)
//                .userId(100).build();
//
//        RatingOutputDto rateOutput = ratingService.saveRating(dto);
//        Assert.assertEquals(Rate.FOUR, rateOutput.getRate());
//
//    }
//
//    @Test
//    @Transactional
//    @WithMockUser(username = "mishamisha@gmail.ru")
//    public void bb_saveRatingTest() {
//        RatingDto dto = RatingDto.builder().rate(Rate.THREE)
//                .newsId(200)
//                .userId(101).build();
//
//        RatingOutputDto rateOutput = ratingService.saveRating(dto);
//        Assert.assertEquals(Rate.THREE, rateOutput.getRate());
//
//    }
//
//    @Test
//    @WithMockUser(username = "email@gmail.ru")
//    public void a_getRatingTest() {
//        List<RatingOutputDto> output = ratingService.getRatingsForNews(200);
//        System.out.println(output);
//        Assert.assertEquals(1, output.size());
//
//    }
//
//    @Test
//    @WithMockUser(username = "email@gmail.ru")
//    public void c_updateRatingTest() {
//        RatingOutputDto dto = new RatingOutputDto(500, "anna", Rate.TWO, 100, 200, 300);
//        RatingOutputDto dtoOutput = ratingService.updateRating(dto);
//        Assert.assertEquals(Rate.TWO, dtoOutput.getRate());
//    }
//
//
//    @Test(expected = IllegalArgumentException.class)
//    @WithMockUser(username = "email@gmail.ru")
//    public void d_deleteRatingTest() {
//        RatingOutputDto output = ratingService.deleteRating(500, 100);
//        ratingRepo.findById(output.getId()).orElseThrow(() -> {
//            throw new IllegalArgumentException();
//        });
//
//    }

}
