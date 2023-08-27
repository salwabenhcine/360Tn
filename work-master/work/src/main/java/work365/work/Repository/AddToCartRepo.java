package work365.work.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work365.work.model.Panier;

@Repository
public interface AddToCartRepo extends JpaRepository<Panier,Integer> {


    //remove cart by userid and cartId,
    //getCartByuserId

    @Query("Select sum(addCart.prix_de_vente) FROM Panier addCart WHERE addCart.user_id=:user_id")
    double getTotalAmountByUserId(@Param("user_id")String user_id);
    @Query("Select addCart  FROM Panier addCart WHERE addCart.user_id=:user_id")
    List<Panier> getCartByuserId(@Param("user_id")String user_id);
    @Query("Select addCart  FROM Panier addCart ")
    Optional<Panier> getCartByuserIdtest();
    @Query("Select addCart  FROM Panier addCart WHERE addCart.produitts.code= :produitts_code and addCart.user_id=:user_id")
    Optional<Panier> getCartByProduittsIdAnduserId(@Param("user_id")String user_id, @Param("produitts_code")int produitts_code);

    @Modifying
    @Transactional
    @Query("DELETE  FROM Panier addCart WHERE addCart.id =:cart_id and addCart.user_id=:user_id")
    void deleteCartByIdAndUserId(@Param("user_id")String user_id,@Param("cart_id")int cart_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM Panier addCart WHERE   addCart.user_id=:user_id")
    void deleteAllCartByUserId(@Param("user_id")String user_id);

    @Modifying
    @Transactional
    @Query("DELETE  FROM Panier addCart WHERE addCart.user_id=:user_id")
    void deleteAllCartUserId(@Param("user_id")String user_id);
    @Modifying
    @Transactional
    @Query("update Panier addCart set addCart.qty=:qty,addCart.prix_de_vente=:prix_de_vente  WHERE addCart.id=:cart_id")
    void updateQtyByCartId(@Param("cart_id")int cart_id,@Param("prix_de_vente")double prix_de_vente,@Param("qty")Integer qty);

}