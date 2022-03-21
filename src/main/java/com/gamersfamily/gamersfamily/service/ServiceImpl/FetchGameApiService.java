package com.gamersfamily.gamersfamily.service.ServiceImpl;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;
import java.util.Optional;

@Profile("fetchGame")
@Service
@PropertySource("classpath:application-fetchGame.properties")
@Slf4j
public class FetchGameApiService {

    private static final String GAME_URL = "https://api.rawg.io/api/games";
    private static final String API_KEY = "125a2de5e7734f638760ae2bad8bd29d";
    private final Integer START_GAME_LOOP_ID = 1;
    private final Integer END_GAME_LOOP_ID = 2;

    private final GameService gameService;
    private final RestTemplate restTemplate;

    public FetchGameApiService(GameService gameService, RestTemplate restTemplate) {
        this.gameService = gameService;
        this.restTemplate = restTemplate;
    }


    @PostConstruct
    public void copyGamesToDatabase(){
        for (int gameId = START_GAME_LOOP_ID; gameId <= END_GAME_LOOP_ID; gameId++) {
            StringBuilder url = new StringBuilder(GAME_URL + "/" + gameId + "?key=" + API_KEY);
            try{
                GameDto gameDto = restTemplate.getForObject(url.toString(), GameDto.class);
                if(!checkIfGameValidity(gameDto.getName())){
                    gameDto.setDescription(html2text(gameDto.getDescription()));
                    gameService.saveGame(gameDto);
                }
            }catch(Exception e){
                log.error(e.getMessage() + " ID: " + gameId);
            }
        }
//        Set<PlatformsDto> platform = gameDto.getPlatforms();

    }

    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    private Boolean checkIfGameValidity(String name){
        if (name != null && !name.isEmpty()) {
            Optional<Game> game = gameService.findByName(name);
            return game.isPresent();
        }
        return false;
    }
}
