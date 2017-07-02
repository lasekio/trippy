package com.lasekio.trippy.domain.model.trippy_host;

import com.google.common.collect.ImmutableList;
import com.lasekio.trippy.domain.common.Aggregate;
import com.lasekio.trippy.domain.common.Event;
import com.lasekio.trippy.domain.meta.CommandHandler;
import com.lasekio.trippy.domain.meta.EventHandler;
import com.lasekio.trippy.domain.model.trippy_host.command.CreateTrip;
import com.lasekio.trippy.domain.model.trippy_host.command.ReserveSeat;
import com.lasekio.trippy.domain.model.trippy_host.entity.Reservation;
import com.lasekio.trippy.domain.model.trippy_host.entity.TripHostRoute;
import com.lasekio.trippy.domain.model.trippy_host.event.SeatReserved;
import com.lasekio.trippy.domain.model.trippy_host.event.TripHostCreated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TripHost extends Aggregate<TripHost> {

    /////// Command handlers
    @CommandHandler
    public static List<Event> handle(CreateTrip createTrip) {
        ArrayList<Event> events = new ArrayList<Event>();

        TripHostCreated event = TripHostCreated.builder()
                .routes(createTrip.routes())
                .driverName(createTrip.driverName())
                .pricePerKilometer(createTrip.pricePerKilometer())
                .build();

        events.add(event);

        return events;
    }

    @CommandHandler
    public static List<Event> handle(TripHostState state, ReserveSeat reserveSeat) {
        ArrayList<Event> events = new ArrayList<Event>();

        Number fromRouteIndex = getRouteIndex(state, reserveSeat.fromRouteId());
        Number toRouteIndex = getRouteIndex(state, reserveSeat.toRouteId());

        events.add(SeatReserved.builder()
            .fromRouteIndex(fromRouteIndex)
            .toRouteIndex(toRouteIndex)
            // @TODO Seat distribution logic
            .seatIndex(0)
            .build());

        return events;
    }

    /////// Events handlers
    @EventHandler()
    public static TripHostState handle(TripHostCreated event) {
        return TripHostState.builder()
                .driverName(event.driverName())
                .routes(event.routes())
                .pricePerKilometer(event.pricePerKilometer())
                // @TODO it must come from command!
                .seatsCount(4)
                .build();
    }

    @EventHandler
    public static TripHostState handle(TripHostState state, SeatReserved event) {

        Reservation reservation = Reservation.builder()
                .fromRouteIndex(event.fromRouteIndex())
                .toRouteIndex(event.toRouteIndex())
                .seatIndex(event.seatIndex())
                .build();

        ImmutableList<Reservation> reservations = new ImmutableList.Builder<Reservation>()
                .addAll(state.reservations())
                .add(reservation)
                .build();

        return state.withReservations(reservations);
    }

    //// Utils
    private static Number getRouteIndex(TripHostState state, String routeID) {
        TripHostRoute foundRoute = state.routes().stream().filter(
                route -> route.id().equals(routeID)
        ).collect(Collectors.toList()).get(0);

        return state.routes().indexOf(foundRoute);
    }
}
