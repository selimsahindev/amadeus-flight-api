package com.selimsahin.amadeus.jobs;

import com.selimsahin.amadeus.dtos.FlightCreateDto;
import com.selimsahin.amadeus.dtos.FlightResponseDto;
import com.selimsahin.amadeus.models.Flight;
import com.selimsahin.amadeus.services.FlightDataService;
import com.selimsahin.amadeus.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
public class FetchFlightDataJob implements Job {
    private final FlightService flightService;
    private final FlightDataService flightDataService;
    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(FetchFlightDataJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Flight data job started at {}", context.getFireTime());

        try {
            List<Flight> mockData = flightDataService.fetchMockFlightData();
            flightService.storeFlightData(mockData);
        } catch (Exception e) {
            logger.error("An error occurred while fetching flight data.", e);
        }
    }
}
