package com.manning.gia.todo.config.nitrite;

import com.manning.gia.todo.DatabaseProfiles;
import org.dizitart.no2.Nitrite;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(DatabaseProfiles.NITRITE)
public class NitriteConfig {

    @Bean
    Nitrite nitriteDatabase() {
        Nitrite db = Nitrite.builder()
                .filePath("/tmp/test.db")
                .compressed()
                .openOrCreate();
        Runtime.getRuntime().addShutdownHook(new Thread(db::close));
        return db;
    }
}
