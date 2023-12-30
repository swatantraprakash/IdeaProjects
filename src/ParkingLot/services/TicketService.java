package ParkingLot.services;

import ParkingLot.models.VehicleType;

public class TicketService {
    //TODO Here Service should be generic (Like Authenticate Service) because other controller also can use this service
    //TODO Here we should not pass DTO's because other controller might not have that dto
    //TODO so pass separate parameter and if any parameter gets increase /decrease we change
    // TODO Because services are not exposed to client else controller are exposed to client (can change parameter in Controller also)
    public void issueTicket(VehicleType vehicleType,String vehicleNumber,String vehicleOwnerName,Long gateId){

    }
}
