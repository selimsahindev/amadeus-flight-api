package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.services.FlightDataService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Job Testing", description = "This is for testing purposes. You can trigger the job to fetch flight data.")
public class JobTriggerController {
    private final FlightDataService flightDataService;
    private final Scheduler scheduler;
    private final ModelMapper modelMapper;

    @GetMapping("/mock-data")
    public List<FlightResponseDto> getFlights() {
        return flightDataService.fetchMockFlightData()
                .stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @PostMapping("/trigger")
    public String triggerJob() {
        JobKey jobKey = new JobKey("flightDataJob", "group1");
        try {
            scheduler.triggerJob(jobKey);
            return "Flight data job triggered successfully.";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Error triggering flight data job: " + e.getMessage();
        }
    }
}
