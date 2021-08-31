package com.iryna.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private long id;
    private String name;
    private int yearOfRelease;
    private double rating;
    private String picturePath;
    private double price;
}
