package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.services.FlightDataService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class JobTriggerController {
    private final FlightDataService flightDataService;
    private final Scheduler scheduler;
    private final ModelMapper modelMapper;

    @GetMapping("/get-data")
    public List<FlightResponseDto> getFlights() {
        return flightDataService.fetchMockFlightData()
                .stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @GetMapping("/trigger-job")
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
