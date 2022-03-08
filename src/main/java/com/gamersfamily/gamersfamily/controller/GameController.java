package com.gamersfamily.gamersfamily.controller;


import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class GameController {

    @Autowired
    private GameServiceImpl gameServiceImpl;

    @GetMapping("/games")
    public ResponseEntity<List<GameDto>> getGames(){
        return new ResponseEntity<>(gameServiceImpl.getAllGames(), HttpStatus.OK);
    }
}
