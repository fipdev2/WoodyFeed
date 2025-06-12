package com.woodyfeed.quiz.question.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
public class QuestionRequest {
    private String description;
    private long mapsToProfileId;
    private long quizId;
}
