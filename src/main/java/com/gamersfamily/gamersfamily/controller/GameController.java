package com.gamersfamily.gamersfamily.controller;


import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Game> saveGame(@RequestBody @Valid GameDto gameDto){
        return new ResponseEntity<>(gameService.saveGame(gameDto),HttpStatus.OK);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody GameDto gameDto){
        gameDto.setId(id);
        return new ResponseEntity<>(gameService.updateGame(gameDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/games")
    public ResponseEntity<HttpStatus> deleteGames(@RequestParam("id") Long id){
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
