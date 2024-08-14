package com.dsm4.dsm_api4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DsmController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DsmController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test-connection")
    public ResponseEntity<Map<String, Object>> testConnection() {
        Map<String, Object> response = new HashMap<>();
        try {
            // Test basic connectivity
            jdbcTemplate.execute("SELECT 1");
            response.put("status", "Connection successful");

            // Get database version
            String version = jdbcTemplate.queryForObject("SELECT @@VERSION", String.class);
            response.put("version", version);

            // Get current database name
            String dbName = jdbcTemplate.queryForObject("SELECT DB_NAME()", String.class);
            response.put("database", dbName);

            // List all tables in the database
            List<String> tables = jdbcTemplate.queryForList(
                    "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'",
                    String.class
            );
            response.put("tables", tables);

            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put("status", "Connection failed");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}