package webgr7.hotelbk.service;

import webgr7.hotelbk.dto.BookingDTO;
import webgr7.hotelbk.model.Slip;

import java.util.List;

public interface SlipService {
    public Slip addBookingSlip(BookingDTO bookingDTO);

    public List<Slip> getSlipByClient(Long clientId);

    public boolean deleteSlip(Long slipId);
}
