package ParkingLot.controller;

import ParkingLot.dtos.IssueTicketRequestDto;
import ParkingLot.dtos.IssueTicketResponseDto;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.models.Ticket;
import ParkingLot.services.TicketService;

public class TicketController {
    public TicketController (TicketService ticketService){
        this.ticketService=ticketService;
    }
    TicketService ticketService;
    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto request)  {
        IssueTicketResponseDto issueTicketResponseDto=new IssueTicketResponseDto();
        Ticket ticket;
        try {
            ticket=ticketService.issueTicket(request.getVehicleType(), request.getVehicleNumber(), request.getVehicleOwnerName(), request.getGateId());
        }catch (Exception e){
            issueTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            issueTicketResponseDto.setErrorMessage(e.getMessage());
            return  issueTicketResponseDto;
        }
        issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        issueTicketResponseDto.setTicket(ticket);
        return issueTicketResponseDto;
    }
}
