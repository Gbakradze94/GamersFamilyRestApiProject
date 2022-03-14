package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.dto.RatingOutputDto;
import com.gamersfamily.gamersfamily.mapper.RatingMapper;
import com.gamersfamily.gamersfamily.model.Rating;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import com.gamersfamily.gamersfamily.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    private RatingMapper ratingMapper;
    private RatingRepository ratingRepository;
    private UserRepository userRepository;

    public RatingServiceImpl(RatingMapper ratingMapper, RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingMapper = ratingMapper;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RatingOutputDto saveRating(RatingDto rating) {
        User user = userRepository.findById(rating.getUserId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("no user with given id found");
                });
        Rating ratingResult = user.getRatings().stream()
                .filter(src -> src.getNews().getId().equals(rating.getNewsId()))
                .findFirst()
                .orElseGet(() -> ratingRepository.save(ratingMapper.dtoToEntity(rating)));
        return ratingMapper.entityToDto(ratingResult);
    }

    @Override
    public List<RatingOutputDto> getRatingsForNews(long newsId) {
        return ratingRepository.findByNewsId(newsId).stream()
                .map(obj -> ratingMapper.entityToDto(obj)).toList();
    }

    @Override
    public RatingOutputDto deleteRating(long ratingId, long authorId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> {
            throw new IllegalArgumentException("rating with this id can not be found to delete");
        });
        if (rating.getAuthor().getId() == authorId) {
            RatingOutputDto output = ratingMapper.entityToDto(rating);
            System.out.println("deleting the rating");
            ratingRepository.deleteById(ratingId);
            return output;
        } else {
            throw new IllegalArgumentException("authorId does not belong to the id of the rating author");
        }
    }

    @Override
    public RatingOutputDto updateRating(RatingOutputDto rating) {
        Rating ratingFound = ratingRepository.findById(rating.getId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("rating with this id can not be found to change");
                });
        ratingFound.setRate(rating.getRate());
        if (ratingFound.getAuthor().getId() == rating.getUserId() && ratingFound.getNews().getId() == rating.getNewsId())
            return ratingMapper.entityToDto(ratingRepository.save(ratingFound));
        else throw new IllegalArgumentException("newsId or UserId is not correct");
    }
}
