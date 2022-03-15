package com.gamersfamily.gamersfamily.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatformsDto {
    PlatformDto platform;

    public String toString(){
        return platform.getName();
    }
}
