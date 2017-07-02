package com.lasekio.trippy.domain.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.immutables.value.Value;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type")
abstract public class Event implements java.io.Serializable {
    @Value.Default
    public String id() {
        return UUID.randomUUID().toString();
    }
}
