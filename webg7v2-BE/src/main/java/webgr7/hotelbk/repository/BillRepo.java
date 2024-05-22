package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Bill;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
    List<Bill> findByClientId(Long clientId);
}
