package com.lorenzomiscoli.spring_boot_sse.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.lorenzomiscoli.spring_boot_sse.services.SseEmitterService;

@RestController
public class CategoryController {

    private final SseEmitterService sseEmitterService;

    private final List<String> categories = new ArrayList<>();
    
    public CategoryController(SseEmitterService sseEmitterService) {
        this.sseEmitterService = sseEmitterService;
    }

    @PostMapping("/categories/{category}")
    public void addCategory(@PathVariable String category) {
        categories.add(category);
        sseEmitterService.send(SseEmitter.event()
                .id(String.valueOf(categories.size()))
                .name("add-category")
                .data(categories));
    }
    
}
