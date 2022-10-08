package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightBuilderFilterServiceImpl implements FlightBuilderFilterService {
    private List<Flight> flights;

    public FlightBuilderFilterServiceImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> getFlight() {
        return flights;
    }

    @Override
    public FlightBuilderFilterService filterDepartureBeforeNow() {
        flights.removeIf(flight -> flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())));
        return this;
    }


    @Override
    public FlightBuilderFilterService filterArrivalBeforeDeparture() {
        flights.removeIf(flight -> flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        return this;
    }

    @Override
    public FlightBuilderFilterService filterOverTwoHoursOnEarth() {
        flights.removeIf(flight -> {
            List<Segment> segments = flight.getSegments();
            LocalDateTime departure, lastArrival;
            Duration duration = Duration.ZERO;
            for (int i = 1; i < segments.size(); i++) {
                departure = segments.get(i).getDepartureDate();
                lastArrival = segments.get(i - 1).getArrivalDate();
                duration = duration.plus(Duration.between(departure, lastArrival).abs());
            }
            return duration.toHours() >= 2;
        });
        return this;
    }


}
