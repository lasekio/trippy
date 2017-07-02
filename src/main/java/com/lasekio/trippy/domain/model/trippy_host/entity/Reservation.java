package com.lasekio.trippy.domain.model.trippy_host.entity;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public abstract class Reservation {

    @Value.Default
    public String id() {
        return UUID.randomUUID().toString();
    }

    public abstract Number fromRouteIndex();
    public abstract Number toRouteIndex();
    public abstract Number seatIndex();

    public static ImmutableReservation.Builder builder() {
        return ImmutableReservation.builder();
    }
}
