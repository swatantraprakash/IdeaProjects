package ParkingLot.services;

import ParkingLot.exceptions.GateNotFoundException;
import ParkingLot.exceptions.VehicleNotFoundException;
import ParkingLot.models.*;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.strategies.SlotAssignmentStrategy;
import ParkingLot.strategies.SlotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    /*
     Here Service should be generic (Like Authenticate Service) because other controller also can use this service
     Here we should not pass DTO's because other controller might not have that dto
     so pass separate parameter and if any parameter gets increase /decrease we change
      Because services are not exposed to client else controller are exposed to client (can change parameter in Controller also)
     */
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;
    public Ticket issueTicket(VehicleType vehicleType,String vehicleNumber,String vehicleOwnerName,Long gateId) throws GateNotFoundException, VehicleNotFoundException {

        //create Ticket Object
        //Assign slot to it
        //Assign time to it
        //Store it to db
        //return the created object
        Ticket ticket=new Ticket();
        ticket.setEntryTime(new Date());

        //Get Gate model from gate id
        Optional<Gate> gateOp=gateRepository.findGateById(gateId);
        if(gateOp.isEmpty()) {
           throw new GateNotFoundException();
        }
        Gate gate=gateOp.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());
        Vehicle saveVehicle;
        Optional<Vehicle> vehicleOp = vehicleRepository.getVehicleByNumber(vehicleNumber);
        if(vehicleOp.isEmpty()){
            Vehicle vehicle=new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleNumber(vehicleOwnerName);
            vehicle.setVehicleType(vehicleType);
            saveVehicle=vehicleRepository.saveVehicle(vehicle);
        }else {
            saveVehicle=vehicleOp.get();
        }
        ticket.setVehicle(saveVehicle);

        SlotAllotmentStrategyType slotAllotmentStrategyType=parkingLotRepository.getParkingLotForGate(gate).getSlotAllotmentStrategy();
        SlotAssignmentStrategy slotAssignmentStrategy= SlotAssignmentStrategyFactory.getSlotForType(slotAllotmentStrategyType);

        ticket.setParkingSlot(slotAssignmentStrategy.getParkingSlot(gate,vehicleType));

        ticket.setNumber("Ticket--");
        return ticketRepository.saveTicket(ticket);
    }
}
