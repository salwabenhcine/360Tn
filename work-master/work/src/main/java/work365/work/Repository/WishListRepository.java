package work365.work.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work365.work.model.WishList;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    //remove cart by userid and cartId,
    //getCartByuserId
WishList findById(int id);
    @Query("Select wishlist  FROM WishList wishlist WHERE wishlist.user_id=:user_id")
    List<WishList> getWishByuserId(@Param("user_id")String user_id);
    @Query("Select wishlist  FROM WishList wishlist ")
    Optional<WishList> getWishByuserIdtest();
    @Query("Select wishlist  FROM WishList wishlist WHERE wishlist.produitts.code= :produitts_code and wishlist.user_id=:user_id")
    Optional<WishList> getWishByProduittsCODEAnduserId(@Param("user_id")String user_id,@Param("produitts_code") int produitts_code);
    @Modifying
    @Transactional
    @Query("DELETE  FROM WishList wishlist WHERE wishlist.id =:cart_id   and wishlist.user_id=:user_id")
    void deleteCartByIdAndUserId(@Param("user_id")String user_id,@Param("cart_id") int cart_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM WishList wishlist WHERE   wishlist.user_id=:user_id")
    void deleteAllCartByUserId(@Param("user_id")String user_id);

    @Modifying
    @Transactional
    @Query("DELETE  FROM WishList wishlist WHERE wishlist.user_id=:user_id")
    void deleteAllCartUserId(@Param("user_id")String user_id);


}
