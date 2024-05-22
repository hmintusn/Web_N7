package webgr7.hotelbk.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webgr7.hotelbk.dto.BillDTO;
import webgr7.hotelbk.model.Bill;
import webgr7.hotelbk.repository.BillRepo;
import webgr7.hotelbk.repository.ClientRepo;
import webgr7.hotelbk.repository.SlipRepo;
import webgr7.hotelbk.service.BillService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImp implements BillService {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private SlipRepo slipRepo;
    @Override
    public List<Bill> getBillByClient(Long clientId) {
        List<Bill> bills = new ArrayList<>();
        bills = billRepo.findByClientId(clientId);
        return bills;
    }

    @Override
    public Bill addBill(BillDTO billDTO) {

        Bill bill = new Bill();
        bill.setType(billDTO.getType());
        bill.setTime(billDTO.getTime());
        bill.setAmount(billDTO.getAmount());
        if(clientRepo.findById(billDTO.getClientID()).isPresent() && slipRepo.findById(billDTO.getSlipID()).isPresent()){
            bill.setClient(clientRepo.findById(billDTO.getClientID()).get());
            bill.setSlip(slipRepo.findById(billDTO.getSlipID()).get());
        }

        return billRepo.save(bill);
    }
}
