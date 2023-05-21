package hr.fer.is.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.is.app.domain.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer> {
}
