package rocketseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passin.domain.checkin.CkeckIn;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<CkeckIn, Integer> {
    Optional<CkeckIn> findByAttendeeId(String attendeeId);


}
