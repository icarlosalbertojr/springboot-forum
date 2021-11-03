package com.icarlosalbertojr.forum.repositories;

import com.icarlosalbertojr.forum.models.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumUserRepository  extends JpaRepository<ForumUser, Long> {

    ForumUser findByEmail(String email);

}
