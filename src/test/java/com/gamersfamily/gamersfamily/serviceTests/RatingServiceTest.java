package com.gamersfamily.gamersfamily.serviceTests;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.dto.RatingOutputDto;
import com.gamersfamily.gamersfamily.service.RatingService;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RatingServiceTest {
    @Autowired
    private RatingService ratingService;

    @Test
    public void saveRatingTest() {
        RatingDto dto = RatingDto.builder().rate(Rate.FIVE)
                .newsId(200)
                .userId(100).build();

        RatingOutputDto rateOutput = ratingService.saveRating(dto);
        Assert.assertEquals(4, rateOutput.getRate());

    }

    @Test
    public void getRatingTest() {

    }

    @Test
    public void updateRatingTest() {

    }

    @Test
    public void deleteRatingTest() {

    }
}
