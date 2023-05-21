package hr.fer.is.app.repository;

import hr.fer.is.app.domain.PrintJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrintJobRepository extends JpaRepository<PrintJob, Long> {

    List<PrintJob> findByPublisherId(Long id);

    List<PrintJob> findAllByPublisherIdIsNull();

    List<PrintJob> findByCoverTypeId(Long id);

    List<PrintJob> findAllByCoverTypeIdIsNull();

    List<PrintJob> findAllByPublisherId(Long id);
}