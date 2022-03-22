package com.gamersfamily.gamersfamily.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagsDto {
    @JsonProperty("name")
    private String name;

    public String toString(){
        return this.getName();
    }
}
