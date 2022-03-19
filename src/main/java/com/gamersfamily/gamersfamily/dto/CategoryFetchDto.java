package com.gamersfamily.gamersfamily.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFetchDto {
    @JsonProperty("name")
    private String name;

    public String toString(){
        return this.getName();
    }
}
