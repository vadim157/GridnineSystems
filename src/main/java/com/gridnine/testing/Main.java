package com.gridnine.testing;

import com.gridnine.testing.dao.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightBuilderFilterServiceImpl;

import java.util.List;


/**
 * Gridnine Systems
 */
public class Main {
    public static void main(String[] args) {

        List<Flight> allFlights = new FlightBuilderFilterServiceImpl(FlightBuilder.createFlights()).getFlight();
        List<Flight> departureBeforeNow = new FlightBuilderFilterServiceImpl(FlightBuilder.createFlights())
                .filterDepartureBeforeNow()
                .getFlight();
        List<Flight> arrivalBeforeDeparture = new FlightBuilderFilterServiceImpl(FlightBuilder.createFlights())
                .filterArrivalBeforeDeparture().getFlight();
        List<Flight> overTwoHoursOnEarth = new FlightBuilderFilterServiceImpl(FlightBuilder.createFlights())
                .filterOverTwoHoursOnEarth().getFlight();

        System.out.println("All Flights: \n" + allFlights);
        System.out.println("No departures up to the current point in time: \n" + departureBeforeNow);
        System.out.println("There are no segments with an arrival date earlier than the departure date: \n"
                + arrivalBeforeDeparture);
        System.out.println("No segments whose total time spent on the ground exceeds two hours: \n"
                + overTwoHoursOnEarth);

    }
}
