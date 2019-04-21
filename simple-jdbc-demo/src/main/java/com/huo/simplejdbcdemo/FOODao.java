package com.huo.simplejdbcdemo;

import com.huo.simplejdbcdemo.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class FOODao implements CommandLineRunner {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;


    private void insert() {
        Arrays.asList("a", "b").forEach(bar -> jdbcTemplate.update("insert into foo values (?)", bar));
        log.info("插入了");

        log.info("开始使用simpleInsert");
        HashMap<String, String> row = new HashMap<>();
        row.put("BAR","张三");
        simpleJdbcInsert.execute(row);
    }

    @Override
    public void run(String... args) throws Exception {
        insert();
        showAll();
    }

    private void showAll() {
        log.info("Count:{}", jdbcTemplate.queryForObject("select count(1) from foo", Long.class));
        List<String> strings = jdbcTemplate.queryForList("select * from foo", String.class);
        strings.forEach(name -> log.info("name:{}", name));

        List<People> people = jdbcTemplate.query("select * from foo", new RowMapper<People>() {
            @Override
            public People mapRow(ResultSet resultSet, int i) throws SQLException {
                return People.builder().name(resultSet.getString(1)).build();
            }
        });

        System.out.println(people);
    }

}
