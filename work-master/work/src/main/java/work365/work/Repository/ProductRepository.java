package work365.work.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work365.work.model.Produitts;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Produitts, Integer> {
    List<Produitts> findBynameContaining(String name);
    List<Produitts> findByCategoriesNomcategorie(String nomcategorie);
    List<Produitts> findBySouscategoriesNomsouscat(String nomsouscat );
    List<Produitts> findAll();
    List<Produitts> findByCheckoutCartId(int code);
    Produitts findByCode(int code);

    @Query(value = "SELECT count(*)  FROM Produitts")
    public int nbreProd ();
    Produitts findByName(String name);
Produitts findByCodebarre(String codebarre);
    // Get a Hibernate SessionFactory



}


