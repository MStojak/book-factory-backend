package hr.fer.is.app.repository;

import hr.fer.is.app.domain.CoverType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverTypeRepository extends JpaRepository<CoverType, Integer> {
}
