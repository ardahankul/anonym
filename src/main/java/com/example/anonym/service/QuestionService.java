package com.example.anonym.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.anonym.config.JwtService;
import com.example.anonym.dto.GetQuestionsResponse;
import com.example.anonym.dto.QuestionRequest;
import com.example.anonym.dto.QuestionResponse;
import com.example.anonym.entity.AppUser;
import com.example.anonym.entity.Question;
import com.example.anonym.repository.AppUserRepository;
import com.example.anonym.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private final AppUserRepository userRepository;
    @Autowired
    private final QuestionRepository questionRepository;
    private final JwtService jwtService;

    public QuestionResponse askQuestion(QuestionRequest body, String guid, String token){

        String questionString = body.getQuestion();
        String jwtToken = token.substring(7);
        String askingUsername = jwtService.extractUsername(jwtToken);

        AppUser answeringUser = userRepository.findByGuid(guid)
                                            .orElseThrow();
        AppUser askingUser = userRepository.findByUsername(askingUsername)
                                            .orElseThrow();

        var question = Question.builder()
                        .question(questionString)
                        .answeringId(answeringUser.getId())
                        .askingId(askingUser.getId())
                        .build();

        questionRepository.save(question);

        QuestionResponse response = new QuestionResponse();
        response.setIsSuccess(true);
        response.setMessage("Question asked succesfully");

        return response;
       
    }

    public GetQuestionsResponse getUserQuestions(String token) {
        String jwtToken = token.substring(7);
        String username = jwtService.extractUsername(jwtToken);
        AppUser user = userRepository.findByUsername(username).orElseThrow();

        List<Question> questionList = questionRepository.findAllByAnsweringId(user.getId());

        List<String> questionStringList = new ArrayList<String>();

        for (Question question : questionList) {
            questionStringList.add(question.getQuestion());
        }

        var response = GetQuestionsResponse.builder().questions(questionStringList).build();

        return response;
    }


}
