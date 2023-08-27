package work365.work.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Souscategories;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<Souscategories, Integer> {

    List<Souscategories> findByNomsouscatContaining(String nomsouscat);
    List<Souscategories> findByCategoriesNomcategorie(String nomcategorie);
    List<Souscategories> findByNomsouscat(String nomsouscat);
    @Query(value = "SELECT count(*)  FROM Souscategories")
    public int nbreSubCategory ();

}
