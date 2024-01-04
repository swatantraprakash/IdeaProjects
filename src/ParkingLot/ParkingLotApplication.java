package ParkingLot;

import ParkingLot.controller.TicketController;
import ParkingLot.dtos.IssueTicketRequestDto;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.services.TicketService;

public class ParkingLotApplication {
    public static void main(String[] args) {

        GateRepository gateRepository=new GateRepository();
        ParkingLotRepository parkingLotRepository=new ParkingLotRepository();
        TicketRepository ticketRepository=new TicketRepository();
        VehicleRepository vehicleRepository=new VehicleRepository();

        TicketService ticketService=new TicketService(gateRepository,vehicleRepository,parkingLotRepository,ticketRepository);

        TicketController ticketController=new TicketController(ticketService);

        System.out.println("Server listening at port 8090----");
    }
}
