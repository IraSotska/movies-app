package com.iryna.db;

import com.iryna.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> findAll();
}
