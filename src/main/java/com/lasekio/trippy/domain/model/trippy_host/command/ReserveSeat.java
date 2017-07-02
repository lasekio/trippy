package com.lasekio.trippy.domain.model.trippy_host.command;

import com.lasekio.trippy.domain.common.Command;
import com.lasekio.trippy.domain.common.Event;
import com.lasekio.trippy.domain.model.trippy_host.entity.TripHostRoute;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class ReserveSeat extends Command {
    public abstract String fromRouteId();
    public abstract String toRouteId();

    public static ImmutableReserveSeat.Builder builder() {
        return ImmutableReserveSeat.builder();
    }
}
