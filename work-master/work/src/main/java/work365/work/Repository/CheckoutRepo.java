package work365.work.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work365.work.model.Categories;
import work365.work.model.Panier;
import work365.work.model.CheckoutCart;

import javax.transaction.Transactional;


@Repository
public interface CheckoutRepo  extends JpaRepository<CheckoutCart, Integer> {
    //@Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.user_id=:user_id group By orderId")
    //List<CheckoutCart> getAllCheckoutByuserId(@Param("user_id")String user_id);
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.orderId=:orderId ")
    CheckoutCart getCheckout(@Param("orderId")String orderId);
    @Modifying
    @Transactional
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.orderId=:orderId group by orderId ")
    List<CheckoutCart> getAllCheckoutByorderId(@Param("orderId")String orderId);
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart ")
    Optional<Panier> getAllCheckoutByuserIdtest();
    @Query("Select checkoutCart  FROM CheckoutCart checkoutCart WHERE checkoutCart.user_id=:user_id group by user_id  ")
    List<CheckoutCart> getAllCheckoutByuserId(@Param("user_id")String user_id);
    @Query(value="Select checkoutCart FROM CheckoutCart checkoutCart WHERE checkoutCart.orderId=:orderId group by clienttt_code", nativeQuery=true)
    List<CheckoutCart> findAll();

    List<Categories>findByTelContaining(String tel);



}