package webgr7.hotelbk.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.BillDTO;
import webgr7.hotelbk.model.Bill;

import java.util.List;
@Service
public interface BillService {
    public List<Bill> getBillByClient(Long clientId);

    public Bill addBill(BillDTO billDTO);
}
