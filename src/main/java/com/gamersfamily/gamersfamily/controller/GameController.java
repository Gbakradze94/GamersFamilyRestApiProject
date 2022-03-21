package com.gamersfamily.gamersfamily.controller;


import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.dto.GameOriginalDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(GameController.BASE_URL)
public class GameController {

    public static final String BASE_URL = "/api/v1/games";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ApiOperation(value = "Gets all games from database.")
    @GetMapping
    public ResponseEntity<List<GameOriginalDto>> getGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets games by page number and quantity of games on each page.")
    @GetMapping("/gamesByPage")
    public ResponseEntity<List<GameOriginalDto>> getGamesByPage(@RequestParam Integer pageNumber,
                                                        @RequestParam Integer pageSize) {
        return new ResponseEntity<>(gameService.getGamesByPage(pageNumber, pageSize), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates new game.")
    @PostMapping
    public ResponseEntity<Game> saveGame(@RequestBody @Valid GameDto gameDto) {
        return new ResponseEntity<>(gameService.saveGame(gameDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Updates game.")
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody GameOriginalDto gameDto) {
        gameDto.setId(id);
        return new ResponseEntity<>(gameService.updateGame(gameDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes game.")
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteGames(@RequestParam("id") Long id) {
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
