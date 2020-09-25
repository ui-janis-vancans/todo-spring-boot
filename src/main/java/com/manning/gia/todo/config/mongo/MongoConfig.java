package com.manning.gia.todo.config.mongo;

import com.manning.gia.todo.DatabaseProfiles;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.net.InetAddress;

@Configuration
@Profile(DatabaseProfiles.MONGO)
public class MongoConfig {

    @Bean
    MongoTemplate mongoTemplate() throws IOException {
        Storage replication = new Storage("/data/mongo", null, 0);

        IMongodConfig config = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .replication(replication)
                .net(new Net(27017, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = starter.prepare(config);
        mongodExecutable.start();
        Runtime.getRuntime().addShutdownHook(new Thread(mongodExecutable::stop));
        Net network = config.net();
        InetAddress serverAddress = network.getServerAddress();
        int port = network.getPort();
        return new MongoTemplate(MongoClients.create("mongodb://" + serverAddress + ":" + port), "test");
    }
}
