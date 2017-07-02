package com.lasekio.trippy.domain.model.trippy_host.command;

import com.lasekio.trippy.domain.common.Event;
import com.lasekio.trippy.domain.model.trippy_host.entity.TripHostRoute;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class CreateTrip extends Event {
    public abstract List<TripHostRoute> routes();
    public abstract String driverName();
    public abstract Number pricePerKilometer();

    public static ImmutableCreateTrip.Builder builder() {
        return ImmutableCreateTrip.builder();
    }
}
