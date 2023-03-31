package com.example.demo;

import io.r2dbc.spi.ConnectionFactory;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.r2dbc.R2dbcLockProvider;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {

    @Bean
    public LockProvider lockProvider(ConnectionFactory connectionFactory) {
        return new R2dbcLockProvider(connectionFactory);
    }

    @Scheduled(fixedDelayString = "15000")
    @SchedulerLock(name = "MySchedule_deleteExpiredComplexCheckResult", lockAtLeastFor = "5000", lockAtMostFor = "5000")
    public void deleteExpiredComplexCheckResults() {
        System.out.println("Start deleting expired complex check results.");
        System.out.println("Finished deleting expired complex check results.");
    }

}
