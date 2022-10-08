package com.gridnine.testing;


import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightBuilderFilterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {


    private List<Flight> allFlights;
    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;
    private Flight flight5;
    private Flight flight6;

    static LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    @Before
    public void initDataTest() {
        flight1 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        flight2 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        flight3 = new Flight(Arrays.asList(new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow)));
        flight4 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6))));
        flight5 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6))));
        flight6 = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)),
                new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))));
        allFlights = new ArrayList<>();
        allFlights.addAll(Arrays.asList(flight1, flight2, flight3, flight4, flight5, flight6));
    }

    @Test
    public void shouldFilterDeparturesUntilNow() {
        List<Flight> expected = new ArrayList<>();
        expected.addAll(allFlights);
        expected.remove(flight3);
        List<Flight> actual = new FlightBuilderFilterServiceImpl(allFlights).filterDepartureBeforeNow().getFlight();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void shouldFilterArrivalBeforeDeparture() {
        List<Flight> expected = new ArrayList<>();
        expected.addAll(allFlights);
        expected.remove(flight4);
        List<Flight> actual = new FlightBuilderFilterServiceImpl(allFlights).filterArrivalBeforeDeparture().getFlight();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterOverTwoHoursOnEarth() {
        List<Flight> expected = new ArrayList<>();
        expected.addAll(allFlights);
        expected.remove(flight5);
        expected.remove(flight6);
        List<Flight> actual = new FlightBuilderFilterServiceImpl(allFlights).filterOverTwoHoursOnEarth().getFlight();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldUseAllFilters() {
        List<Flight> expected = new ArrayList<>();
        expected.addAll(allFlights);
        expected.remove(flight3);
        expected.remove(flight4);
        expected.remove(flight5);
        expected.remove(flight6);
        List<Flight> actual = new FlightBuilderFilterServiceImpl(allFlights)
                .filterDepartureBeforeNow()
                .filterArrivalBeforeDeparture()
                .filterOverTwoHoursOnEarth().getFlight();
        Assert.assertEquals(expected, actual);
    }


}
