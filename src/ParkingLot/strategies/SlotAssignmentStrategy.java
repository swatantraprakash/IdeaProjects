package ParkingLot.strategies;

import ParkingLot.models.*;

public interface SlotAssignmentStrategy {
    public ParkingSlot getParkingSlot(Gate gate, VehicleType vehicleType);
}
