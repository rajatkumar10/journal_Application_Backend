package com.journalLatest.Repository;

import com.journalLatest.Entity.ConfigJournalAppEntity;
import com.journalLatest.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
