package ru.ddc.duckDBTest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {

    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(RunAfterStartup.class);

    @Value("${parquet.filename}")
    private String filename;

    public RunAfterStartup(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        log.info("Начата загрузка файла {}", filename);
        jdbcTemplate.execute("CREATE TABLE test AS SELECT * FROM " + filename + ";");
        log.info("Файл {} успешно загружен", filename);
    }
}
