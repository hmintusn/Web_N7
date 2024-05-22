package webgr7.hotelbk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webgr7.hotelbk.model.Slip;

import java.util.List;
@Repository
public interface SlipRepo extends JpaRepository<Slip, Long> {
    List<Slip> findByClientId(Long clientId);
}
