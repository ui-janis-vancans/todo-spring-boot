package com.manning.gia.todo.config.mapdb;

import com.manning.gia.todo.DatabaseProfiles;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(DatabaseProfiles.MAPDB)
class MapdbConfig {

    @Bean
    DB mapdbDatabase() {
        DB db = DBMaker.fileDB("mapdb.data")
                .fileMmapEnable()
                .make();
        Runtime.getRuntime().addShutdownHook(new Thread(db::close));
        return db;
    }
}
