package work365.work.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Categories;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Integer> {

    List<Categories> findBynomcategorieContaining(String nomcategorie);
  // List<Category> findById(int categoryId);
  @Query(value = "SELECT count(*)  FROM Categories")
  public int nbreCategory ();
}
