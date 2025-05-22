package com.usercrud.repository;

import com.usercrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "mobile TEXT, " +
                "gender TEXT, " +
                "address TEXT" +
                ")";
        jdbcTemplate.execute(sql);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void save(User user) {
        String sql = "INSERT INTO users (name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getMobile(), user.getGender(), user.getAddress());
    }

    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, mobile = ?, gender = ?, address = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getMobile(), user.getGender(), user.getAddress(), user.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
} 