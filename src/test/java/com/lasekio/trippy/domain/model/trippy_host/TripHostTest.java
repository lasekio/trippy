package com.lasekio.trippy.domain.model.trippy_host;

import com.lasekio.trippy.domain.common.Event;
import com.lasekio.trippy.domain.model.trippy_host.command.CreateTrip;
import com.lasekio.trippy.domain.model.trippy_host.command.ReserveSeat;
import com.lasekio.trippy.domain.model.trippy_host.entity.TripHostRoute;
import com.lasekio.trippy.domain.model.trippy_host.event.SeatReserved;
import com.lasekio.trippy.domain.model.trippy_host.event.TripHostCreated;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TripHostTest {

    private TripHostRoute torunWarszawaRoute = TripHostRoute.builder()
            .fromCity("Toruń")
            .toCity("Warszawa")
            .length(256)
            .build();

    @Test public void testCreate() {

        ArrayList<TripHostRoute> routes = new ArrayList<TripHostRoute>(1);
        routes.add(torunWarszawaRoute);

        CreateTrip createTrip = CreateTrip.builder()
                .routes(routes)
                .pricePerKilometer(1)
                .driverName("Joe")
                .build();

        List<Event> events = TripHost.handle(createTrip);

        assertTrue(events.get(0) instanceof TripHostCreated);

        TripHostCreated event = (TripHostCreated) events.get(0);

        assertEquals(torunWarszawaRoute, event.routes().get(0));

        assertEquals("Joe", event.driverName());
    }

    @Test public void testCreatedEventHandle() {
        ArrayList<TripHostRoute> routes = new ArrayList<TripHostRoute>();

        routes.add(torunWarszawaRoute);

        TripHostCreated event = TripHostCreated.builder()
                .driverName("Dave")
                .routes(routes)
                .pricePerKilometer(1)
                .build();

        TripHostState state = TripHost.handle(event);

        assertEquals("Dave", state.driverName());
        assertEquals(routes, state.routes());
        assertEquals(1, state.pricePerKilometer());
    }

    @Test public void testRoutesValidation() {}


    @Test public void testReserveSeat() {
        ArrayList<TripHostRoute> routes = new ArrayList<TripHostRoute>(1);
        routes.add(torunWarszawaRoute);

        ReserveSeat command = ReserveSeat.builder()
                .fromRouteId(torunWarszawaRoute.id())
                .toRouteId(torunWarszawaRoute.id())
                .build();

        TripHostState state = TripHostState.builder()
                .driverName("Dave")
                .pricePerKilometer(2)
                .routes(routes)
                .seatsCount(4)
                .build();

        List<Event> events = TripHost.handle(state, command);

        assertNotEquals("Handle must return some event", 0, events.size());
        assertTrue(events.get(0) instanceof SeatReserved);

        SeatReserved event = (SeatReserved) events.get(0);

        assertEquals(event.fromRouteIndex(), 0);
        assertEquals(event.toRouteIndex(), 0);
        assertEquals(event.seatIndex(), 0);
    }


//    @Test public void testReserveSeatOnReserved() {
//        ArrayList<TripHostRoute> routes = new ArrayList<TripHostRoute>(1);
//        routes.add(torunWarszawaRoute);
//
//        ReserveSeat command = ReserveSeat.builder()
//                .fromRouteId(torunWarszawaRoute.id())
//                .toRouteId(torunWarszawaRoute.id())
//                .build();
//
//        TripHostState state = TripHostState.builder()
//                .driverName("Dave")
//                .pricePerKilometer(2)
//                .routes(routes)
//                .seatsCount(4)
//                .build();
//
//        List<Event> events = TripHost.handle(state, command);
//
//        assertNotEquals("Handle must return some event", 0, events.size());
//    }
}
curl -i -d  ~/Downloads/simpleevent.json "http://docker.local:2113/streams/newstream444"