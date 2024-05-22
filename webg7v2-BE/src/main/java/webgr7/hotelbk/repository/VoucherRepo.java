package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Client;
import webgr7.hotelbk.model.Voucher;

import java.util.List;
@Repository
public interface VoucherRepo extends JpaRepository<Voucher, Long> {
    List<Voucher> findAllByClients(Client client);
}
