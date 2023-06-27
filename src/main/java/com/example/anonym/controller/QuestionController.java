package com.example.anonym.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.anonym.dto.GetQuestionsResponse;
import com.example.anonym.dto.QuestionRequest;
import com.example.anonym.dto.QuestionResponse;
import com.example.anonym.service.QuestionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;


    @PostMapping("/api/v1/ask/{guid}")
    public ResponseEntity<String> askQuestion(
        @PathVariable("guid") String guid,
        @RequestBody QuestionRequest questionReq,
        @RequestHeader("Authorization") String token ) {

        QuestionResponse response = questionService.askQuestion(questionReq, guid, token);
        
        
       return ResponseEntity.ok("done");
    }

    @GetMapping("/api/v1/getQuestions")
    public ResponseEntity<GetQuestionsResponse> getUserQuestions(@RequestHeader("Authorization") String token){

        GetQuestionsResponse response = questionService.getUserQuestions(token);
        
        return ResponseEntity.ok(response);
    }
    
    
}
