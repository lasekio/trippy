package com.lasekio.trippy.domain.model.trippy_host.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableTripHostRoute.class)
@JsonDeserialize(as = ImmutableTripHostRoute.class)
abstract public class TripHostRoute {
    @Value.Default
    public String id() {
        return UUID.randomUUID().toString();
    }

    public abstract String fromCity();
    public abstract TripHostRoute withFromCity(String fromCity);

    public abstract String toCity();
    public abstract TripHostRoute withToCity(String toCity);

    public abstract Number length();
    public abstract TripHostRoute withLength(Number length);

    public static ImmutableTripHostRoute.Builder builder() {
        return ImmutableTripHostRoute.builder();
    }
}
