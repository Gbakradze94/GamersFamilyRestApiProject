package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.dto.RatingOutputDto;
import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import com.gamersfamily.gamersfamily.mapper.RatingMapper;
import com.gamersfamily.gamersfamily.model.Rating;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import com.gamersfamily.gamersfamily.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user = userRepository.findById(rating.getUser().getId())
                .orElseThrow(() -> {
                    throw new BlogAPIException("no user with given id found", HttpStatus.BAD_REQUEST);
                });
        if (!user.getEmail().equals(getAuthenticatedUserEmail())){
            throw new BlogAPIException("rating does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        }
        Rating ratingResult = user.getRatings().stream()
                .filter(src -> src.getNews().getId() == rating.getNews().getId())
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
            throw new BlogAPIException("rating with this id can not be found to delete", HttpStatus.BAD_REQUEST);
        });
        if (rating.getAuthor().getEmail().equals(getAuthenticatedUserEmail())) {
            RatingOutputDto output = ratingMapper.entityToDto(rating);
            System.out.println("deleting the rating");
            ratingRepository.deleteById(ratingId);
            return output;
        } else {
            throw new BlogAPIException("rating does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public RatingOutputDto updateRating(RatingOutputDto rating) {
        Rating ratingFound = ratingRepository.findById(rating.getId())
                .orElseThrow(() -> {
                    throw new BlogAPIException("rating with this id can not be found to change", HttpStatus.BAD_REQUEST);
                });
        ratingFound.setRate(rating.getRate());
        if (!ratingFound.getAuthor().getEmail().equals(getAuthenticatedUserEmail())) {
            throw new BlogAPIException("rating does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        } else if (ratingFound.getNews().getId() != rating.getNews().getId()) {
            throw new BlogAPIException("news id does not belong to this rating ", HttpStatus.BAD_REQUEST);
        } else {
            return ratingMapper.entityToDto(ratingRepository.save(ratingFound));
        }

    }

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
