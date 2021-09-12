package com.iryna.db.jdbc;

import com.iryna.db.GenreDao;
import com.iryna.db.mapper.GenreRowMapper;
import com.iryna.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class JdbcGenreDao implements GenreDao {
    private static final String GET_ALL_GENRES_QUERY = "SELECT id, name FROM genre;";

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private JdbcTemplate jdbcTemplate;

    public List<Genre> findAll() {
        log.info("Get genres from db");
        return jdbcTemplate.query(GET_ALL_GENRES_QUERY, GENRE_ROW_MAPPER);
    }
}
