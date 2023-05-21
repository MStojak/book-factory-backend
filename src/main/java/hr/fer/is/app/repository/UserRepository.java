package hr.fer.is.app.repository;

import hr.fer.is.app.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByEmail(String login);

    List<User> findAllByEmail(String login);

}
