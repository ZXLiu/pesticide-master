package pers.geolo.pesticide.server.config;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

@Configuration
public class MongoDBConfig {

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Bean
    public GridFSBucket getGridFSBucket() {
        MongoDatabase mongoDatabase = mongoDbFactory.getDb();
        return GridFSBuckets.create(mongoDatabase);
    }

}
