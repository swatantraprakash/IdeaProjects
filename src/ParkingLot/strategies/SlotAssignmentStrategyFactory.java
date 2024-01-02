package ParkingLot.strategies;

import ParkingLot.models.SlotAllotmentStrategyType;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignmentStrategy getSlotForType(SlotAllotmentStrategyType slotAllotmentStrategyType){
        return new RandomSlotAssignmentStrategy();
    }
}
