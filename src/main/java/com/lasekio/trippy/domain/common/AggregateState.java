package com.lasekio.trippy.domain.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lasekio.trippy.domain.model.trippy_host.TripHostState;
import org.immutables.value.Value;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type")
public abstract class AggregateState<A> {

    @Value.Default
    public String id() {
        return UUID.randomUUID().toString();
    }

    @Value.Default
    public int version() {
        return 0;
    }
    public A newVersion() {
        return this.withVersion(version() + 1);
    }
    public abstract A withVersion(int version);
}
