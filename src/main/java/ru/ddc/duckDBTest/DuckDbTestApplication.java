package ru.ddc.duckDBTest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class DuckDbTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuckDbTestApplication.class, args);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate duckdb = new JdbcTemplate();
        duckdb.setDataSource(dataSource);
        return duckdb;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.duckdb.DuckDBDriver");
        config.setMaximumPoolSize(10);
        config.setMaxLifetime(3);
        config.setJdbcUrl("jdbc:duckdb:");
        return new HikariDataSource(config);
    }
}
