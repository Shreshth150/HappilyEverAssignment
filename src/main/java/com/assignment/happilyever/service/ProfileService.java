package com.assignment.happilyever.service;


import com.assignment.happilyever.exchanges.ProfileRequest;
import com.assignment.happilyever.exchanges.ProfileResponse;
import com.assignment.happilyever.model.Profile;
import com.assignment.happilyever.model.Status;
import com.assignment.happilyever.repository.ProfileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileResponse> getAllProfiles() {
        List<ProfileResponse> profileResponseList = new ArrayList<>();
        List<Profile> storedProfiles = profileRepository.findAll();

        storedProfiles.forEach(profile ->{
            ProfileResponse profileResponse = new ProfileResponse();
            BeanUtils.copyProperties(profile , profileResponse);
            profileResponseList.add(profileResponse);
        });
        return profileResponseList;
    }

    public List<ProfileResponse> getPausedProfiles(){
        List<ProfileResponse> PausedProfileResponseList = new ArrayList<>();
        List<Profile> storedPausedProfiles = profileRepository.findAllByStatus(Status.PAUSED);

        storedPausedProfiles.forEach(profile ->{
            ProfileResponse profileResponse = new ProfileResponse();
            BeanUtils.copyProperties(profile , profileResponse);
            PausedProfileResponseList.add(profileResponse);
        });
        return PausedProfileResponseList;
    }


    public ProfileResponse pauseProfile(String firstName){
        Profile profileFromDb = profileRepository.findProfileByFirstName(firstName);
        profileFromDb.setStatus(Status.PAUSED);
        ProfileResponse response = new ProfileResponse();
        BeanUtils.copyProperties(profileFromDb , response);
        profileRepository.save(profileFromDb);
        return response;

    }


    public ProfileResponse unPauseProfile(String firstName){
        Profile profileFromDb = profileRepository.findProfileByFirstName(firstName);
        profileFromDb.setStatus(Status.ACTIVE);
        ProfileResponse response = new ProfileResponse();
        BeanUtils.copyProperties(profileFromDb , response);
        profileRepository.save(profileFromDb);
        return response;

    }

    public ProfileResponse deleteProfile(String firstName){
        ProfileResponse profileResponse = new ProfileResponse();
        Profile profile = profileRepository.findProfileByFirstName(firstName);
        Long id = profile.getId();
        profileRepository.deleteProfileById(id);
        BeanUtils.copyProperties(profile , profileResponse);
        return profileResponse;
    }

    public ProfileResponse addProfile(ProfileRequest profileRequest){
        Profile profile = new Profile();
        BeanUtils.copyProperties(profileRequest , profile);
        profile.setStatus(Status.ACTIVE);
        profileRepository.save(profile);
        ProfileResponse response = new ProfileResponse();
        BeanUtils.copyProperties(profile , response);
        return response;
    }
}
