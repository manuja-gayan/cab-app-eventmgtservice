package com.ceyloncab.eventmgtservice.external.repository;

import com.ceyloncab.eventmgtservice.domain.entity.UserTokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends MongoRepository<UserTokenEntity,String> {

    Optional<UserTokenEntity> findByUserId(String userId);
}
