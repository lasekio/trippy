package com.lasekio.trippy.domain.model.trippy_host;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import com.lasekio.trippy.domain.common.AggregateState;
import com.lasekio.trippy.domain.model.trippy_host.entity.Reservation;
import com.lasekio.trippy.domain.model.trippy_host.entity.TripHostRoute;
import org.immutables.value.Value;

import java.util.List;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableTripHostState.class)
@JsonDeserialize(as = ImmutableTripHostState.class)
abstract public class TripHostState extends AggregateState<TripHostState> {
    public abstract String driverName();
    public abstract Number pricePerKilometer();
    public abstract Number seatsCount();
    public abstract List<TripHostRoute> routes();
    @Value.Default public ImmutableList<Reservation> reservations() { return ImmutableList.of(); }
    @Value.Default public boolean isValid() {
        return false;
    }


    public abstract TripHostState withDriverName(String driverName);
    public abstract TripHostState withPricePerKilometer(Number pricePerKilometer);
    public abstract TripHostState withRoutes(TripHostRoute... routes);

    public TripHostState withReservations(ImmutableList<Reservation> reservations) {

        // ImmutableList is required, Iterable is not good enough
        ImmutableTripHostState _this = (ImmutableTripHostState) this;

        return _this.withReservations((Iterable<Reservation>) reservations);
    };

    public abstract TripHostState withIsValid(boolean isValid);

    public static ImmutableTripHostState.Builder builder() {
        return ImmutableTripHostState.builder();
    }
}
