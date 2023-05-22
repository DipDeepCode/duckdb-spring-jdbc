package ru.ddc.duckDBTest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.duckDBTest.service.QueryService;

@RestController
@RequestMapping("abc")
public class ApiController {

    private final QueryService queryService;

    public ApiController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/init")
    public Object init(){
        queryService.init();
        return "ok";
    }

    @GetMapping("/query")
    public Object query() {
        return queryService.query();
    }
}
