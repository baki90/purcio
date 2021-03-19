package purcio.purcio.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import purcio.purcio.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
