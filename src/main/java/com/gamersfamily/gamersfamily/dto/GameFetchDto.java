package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameFetchDto {
    private String name;
    private String description;
    private String background_image;
    private String released;
    private Double rating;
    private Set<PlatformsDto> platforms;
}
