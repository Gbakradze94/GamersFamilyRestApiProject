package com.gamersfamily.gamersfamily.service.ServiceImpl;

import com.gamersfamily.gamersfamily.dto.GameFetchDto;
import com.gamersfamily.gamersfamily.dto.PlatformsDto;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class FetchGameApiService {

    private static final String URL = "https://api.rawg.io/api/games/3498?key=125a2de5e7734f638760ae2bad8bd29d";

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void getGameById(){
        GameFetchDto gameDto = restTemplate.getForObject(URL, GameFetchDto.class);
        String name = gameDto.getName();
        String description = html2text(gameDto.getDescription());
        String background_image = gameDto.getBackground_image();
        String released = gameDto.getReleased();
        Double rating = gameDto.getRating();
        Set<PlatformsDto> platform = gameDto.getPlatforms();
        String answer = "Name: " + name + "\n" + "Desc: " + description
        + "\n" + "Image: " + background_image + "\n" + "released: " + released +
                "\n" + "rating: " + rating.toString()
                + "\n" + "platforms: " + platform;
        System.out.println(answer);
    }

    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
