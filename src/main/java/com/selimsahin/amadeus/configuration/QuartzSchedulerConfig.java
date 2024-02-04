package com.selimsahin.amadeus.configuration;

import com.selimsahin.amadeus.jobs.FetchFlightDataJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzSchedulerConfig {
    @Bean
    public CommandLineRunner scheduleJob(Scheduler scheduler) {
        return args -> {
            JobDetail job = JobBuilder.newJob(FetchFlightDataJob.class)
                .withIdentity("flightDataJob", "group1")
                .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?")) // Runs every day at midnight
                .build();

            scheduler.scheduleJob(job, trigger);
        };
    }
}