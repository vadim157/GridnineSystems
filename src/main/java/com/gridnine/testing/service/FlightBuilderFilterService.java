package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightBuilderFilterService {

    List<Flight> getFlight();
    FlightBuilderFilterService filterDepartureBeforeNow();
    FlightBuilderFilterService filterArrivalBeforeDeparture();
    FlightBuilderFilterService filterOverTwoHoursOnEarth();
}
