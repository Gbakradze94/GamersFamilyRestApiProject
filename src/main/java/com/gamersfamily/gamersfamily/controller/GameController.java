package com.gamersfamily.gamersfamily.controller;


import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class GameController {


    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity<List<GameDto>> getGames(){
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/gamesByPage")
    public ResponseEntity<List<GameDto>> getGamesByPage(@RequestParam Integer pageNumber,
                                                        @RequestParam Integer pageSize){
        return new ResponseEntity<>(gameService.getGamesByPage(pageNumber,pageSize),HttpStatus.OK);
    }


    @PostMapping("/games")
    public ResponseEntity<Game> saveGame(@RequestBody GameDto gameDto){
        return new ResponseEntity<>(gameService.saveGame(gameDto),HttpStatus.OK);
    }


}
