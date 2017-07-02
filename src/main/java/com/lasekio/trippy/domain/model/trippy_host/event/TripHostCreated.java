package com.lasekio.trippy.domain.model.trippy_host.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasekio.trippy.domain.common.Event;
import com.lasekio.trippy.domain.model.trippy_host.entity.TripHostRoute;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableTripHostCreated.class)
@JsonSerialize(as = ImmutableTripHostCreated.class)
public abstract class TripHostCreated extends Event {
    public enum STATE { ENABLED, DISABLED }

    public abstract List<TripHostRoute> routes();
    public abstract TripHostCreated withRoutes(TripHostRoute... routes);

    public abstract String driverName();
    public abstract TripHostCreated withDriverName(String driverName);

    public abstract Number pricePerKilometer();
    public abstract TripHostCreated withPricePerKilometer(Number pricePerKilometer);

    @Value.Default
    public STATE state() {
        return STATE.ENABLED;
    }
    public abstract TripHostCreated withState(STATE state);

    public static ImmutableTripHostCreated.Builder builder() {
        return ImmutableTripHostCreated.builder();
    }
}

