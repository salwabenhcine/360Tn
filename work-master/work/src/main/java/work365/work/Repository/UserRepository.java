package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Clienttt;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Clienttt, String> {
    List<Clienttt> findAll();
    @Query(value = "SELECT count(*)  FROM Clienttt")
    public int nbreClient ();
    ///User findByFirstName(String firstName);

    Clienttt findByEmail(String email);
}