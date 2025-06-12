package com.woodyfeed.quiz.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woodyfeed.quiz.profile.Requests.ProfileRequest;
import com.woodyfeed.quiz.question.QuestionRepository;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/:id")
    public ResponseEntity<Profile> getProfile(@RequestParam long profileId) {
        Profile profile = profileRepository.findById(profileId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/")
    public ResponseEntity<List<Profile>> getProfiles(@RequestParam long profileId) {
        List<Profile> profiles = profileRepository.findAll();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping("/")
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileRequest profileRequest) {
        Profile profile = Profile.builder()
                .universe(profileRequest.getUniverse())
                .name(profileRequest.getName())
                .imageFileName(profileRequest.getImageFileName())
                .build();

        profile = profileRepository.save(profile);
        if (profile == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(profile);
    }

}
