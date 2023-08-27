package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Marque;

import java.util.List;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, String> {
    @Query(value = "SELECT count(*)  FROM Marque")
    public int nbreMarque ();
    List<Marque> findBynommarqueContaining(String nommarque);
}
