package com.assignment.happilyever.repository;

import com.assignment.happilyever.model.Profile;
import com.assignment.happilyever.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

    @Transactional
    Profile findProfileByFirstName(String firstName);

    List<Profile> findAllByStatus(Status status);

    @Transactional
    void deleteProfileById(Long id);


}
