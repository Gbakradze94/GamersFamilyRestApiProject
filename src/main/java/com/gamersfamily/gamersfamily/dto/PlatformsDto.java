package com.gamersfamily.gamersfamily.dto;

import lombok.*;

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
