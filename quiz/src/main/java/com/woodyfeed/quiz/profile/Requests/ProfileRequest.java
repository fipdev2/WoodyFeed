package com.woodyfeed.quiz.profile.Requests;

import lombok.Data;

@Data
public class ProfileRequest {
    private String universe;
    private String name;
    private String imageFileName;
    private long questionId;
}
