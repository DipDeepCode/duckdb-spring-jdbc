package ru.ddc.duckDBTest.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    private final JdbcTemplate jdbcTemplate;

    public QueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
        jdbcTemplate.execute("CREATE TABLE test AS SELECT * FROM \"D:\\Данные для хакатона ЛЦТ\\X_test.parquet\";");
    }

    public List<Map<String, Object>> query() {
        return jdbcTemplate.queryForList("SELECT * FROM test LIMIT 2;");
    }
}
