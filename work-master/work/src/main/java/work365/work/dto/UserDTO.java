package work365.work.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Clienttt;

@Repository
public interface UserDTO  extends JpaRepository<Clienttt,String> {

    @Query("SELECT u FROM Clienttt u WHERE u.verificationCode = ?1")
    public Clienttt findByVerificationCode(String code);

    Clienttt findByEmail(String email);




    @Query(value = "SELECT count(*)  FROM Clienttt")
    int nbreClient();

}
