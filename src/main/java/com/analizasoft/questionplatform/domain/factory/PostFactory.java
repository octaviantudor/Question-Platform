package com.analizasoft.questionplatform.domain.factory;

import com.analizasoft.questionplatform.domain.entity.Answer;
import com.analizasoft.questionplatform.domain.entity.Question;
import com.analizasoft.questionplatform.domain.entity.Suggestion;
import com.analizasoft.questionplatform.domain.entity.User;
import com.analizasoft.questionplatform.domain.enums.PostType;
import org.springframework.stereotype.Component;

@Component
public class PostFactory {

    public Postable createPostableEntity(Question question, String postText, User user, PostType postType) {

        return postType.equals(PostType.ANSWER) ?
                createAnswear(question, postText, user) :
                createSuggestion(question, postText, user);


    }

    private Answer createAnswear(Question question, String test, User user) {
        return Answer
                .builder()
                .question(question)
                .user(user)
                .isBestAnswer(Boolean.FALSE)
                .answer(test)
                .build();
    }


    private Suggestion createSuggestion(Question question, String text, User user) {
        return Suggestion.builder()
                .suggestion(text)
                .question(question)
                .user(user)
                .build();
    }

}
