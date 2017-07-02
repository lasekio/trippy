package com.lasekio.trippy.domain.model.trippy_host.event;

import com.lasekio.trippy.domain.common.Event;
import org.immutables.value.Value;

@Value.Immutable
public abstract class SeatReserved extends Event{
    public abstract Number fromRouteIndex();
    public abstract Number toRouteIndex();
    public abstract Number seatIndex();

    public static ImmutableSeatReserved.Builder builder() {
        return ImmutableSeatReserved.builder();
    }
}

