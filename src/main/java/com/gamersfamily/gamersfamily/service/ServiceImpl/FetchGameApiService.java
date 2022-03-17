package com.gamersfamily.gamersfamily.service.ServiceImpl;

import com.gamersfamily.gamersfamily.dto.GameFetchDto;
import com.gamersfamily.gamersfamily.dto.PlatformsDto;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;

@Profile("fetchGame")
@Service
@PropertySource("classpath:application-fetchGame.properties")
public class FetchGameApiService {

    private static final String GAME_URL = "https://api.rawg.io/api/games";
    private static final String API_KEY = "125a2de5e7734f638760ae2bad8bd29d";
    private static Integer GAME_ID = 1;
    private static final String URL = GAME_URL + "/" + GAME_ID + "?key=" + API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void getGameById(){
        GameFetchDto gameDto = restTemplate.getForObject(URL, GameFetchDto.class);
        String name = gameDto.getName();
        String description = html2text(gameDto.getDescription());
        String background_image = gameDto.getBackground_image();
        LocalDate released = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(gameDto.getReleased()));
        Set<PlatformsDto> platform = gameDto.getPlatforms();
        String answer = "Name: " + name + "\n" + "Desc: " + description
        + "\n" + "Image: " + background_image + "\n" + "released: " + released
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
