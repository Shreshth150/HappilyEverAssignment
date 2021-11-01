package com.assignment.happilyever.controller;


import com.assignment.happilyever.exchanges.ProfileRequest;
import com.assignment.happilyever.exchanges.ProfileResponse;
import com.assignment.happilyever.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PostMapping()
    public ResponseEntity<ProfileResponse> createProfile(
           @RequestBody @Valid ProfileRequest profileRequest){
        ProfileResponse profileResponse = profileService.addProfile(profileRequest);
        log.info("Created a new Profile : {}" , profileResponse);
        return new ResponseEntity<>(profileResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        List<ProfileResponse> profiles = profileService.getAllProfiles();
        return new ResponseEntity<>(profiles , HttpStatus.OK);
    }

    @GetMapping("/paused")
    public ResponseEntity<List<ProfileResponse>> getPausedProfiles(){
        List<ProfileResponse> profiles = profileService.getPausedProfiles();
        return new ResponseEntity<>(profiles , HttpStatus.OK);
    }

    @GetMapping("/pause")
    public ResponseEntity<ProfileResponse> pauseProfile(@RequestParam String firstName){
      ProfileResponse profileResponse = profileService.pauseProfile(firstName);
      log.info("Paused Profile : {}" , profileResponse);
      return new ResponseEntity<>(profileResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/unpause")
    public ResponseEntity<ProfileResponse> UnPauseProfile(@RequestParam String firstName){
        ProfileResponse profileResponse = profileService.unPauseProfile(firstName);
        log.info("Unpaused Profile : {}" , profileResponse);
        return new ResponseEntity<>(profileResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ProfileResponse> deleteProfile(@RequestParam String firstName ){
        ProfileResponse profileResponse = profileService.deleteProfile(firstName);
        log.info("Deleted Profile of : {}" , firstName);
        return new ResponseEntity<>(profileResponse , HttpStatus.OK);
    }
}
