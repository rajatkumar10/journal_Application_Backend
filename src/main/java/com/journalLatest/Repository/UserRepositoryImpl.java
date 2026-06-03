package com.journalLatest.Repository;

import com.journalLatest.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User>getUserForSA(){
        Query q=new Query();
        q.addCriteria(
                Criteria.where("email")
                        .regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        );
//        q.addCriteria(Criteria.where("email").ne(null).ne(""));
        q.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        //     q.addCriteria(Criteria.where("roles").in("USER","ADMIN"));
        return mongoTemplate.find(q, User.class);
    }
}
