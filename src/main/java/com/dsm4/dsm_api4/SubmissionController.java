package com.dsm4.dsm_api4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubmissionController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<?> submitForm(
            @RequestParam("requestUser") String requestUser,
            @RequestParam("summary") String summary,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "dueDate", required = false) String dueDate,
            @RequestParam(value = "externalRequestType", required = false) String externalRequestType,
            @RequestParam(value = "otherPersonName", required = false) String otherPersonName,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        try {
            String sql;
            Object[] params;

            if (file != null && !file.isEmpty()) {
                sql = "INSERT INTO submissions (request_user, summary, description, due_date, external_request_type, other_person_name, file_name, file_content) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                params = new Object[]{
                        requestUser,
                        summary,
                        description,
                        dueDate != null && !dueDate.isEmpty() ? java.sql.Date.valueOf(dueDate) : null,
                        externalRequestType,
                        otherPersonName,
                        file.getOriginalFilename(),
                        file.getBytes()
                };
            } else {
                sql = "INSERT INTO submissions (request_user, summary, description, due_date, external_request_type, other_person_name) VALUES (?, ?, ?, ?, ?, ?)";
                params = new Object[]{
                        requestUser,
                        summary,
                        description,
                        dueDate != null && !dueDate.isEmpty() ? java.sql.Date.valueOf(dueDate) : null,
                        externalRequestType,
                        otherPersonName
                };
            }

            jdbcTemplate.update(sql, params);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Submission successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Submission failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}