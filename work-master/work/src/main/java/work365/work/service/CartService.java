package work365.work.service;



import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import work365.work.model.Panier;
import work365.work.model.CheckoutCart;

@Configuration
@EnableJpaAuditing
public interface CartService {
    List<Panier> addCartbyUserIdAndProductId(int productId, String userId, int qty, double prix_de_vente, String productName, String imageURL) throws Exception;
    void updateQtyByCartId(int cartId,int qty,double price) throws Exception;
    List<Panier> getCartByUserId(String userId);
    List<Panier> removeCartByUserId(int cartId, String userId);
    List<Panier> removeAllCartByUserId(String userId);
    Boolean checkTotalAmountAgainstCart(double totalAmount,String userId);
    List<CheckoutCart> getCheckoutCartByUserId(String userId);
    List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp)  throws Exception;
    List<CheckoutCart> getCheckoutCartByOrderId(String orderId);
    CheckoutCart getCheckout(String orderId);
    Optional<CheckoutCart> getById(int id);
    List<CheckoutCart> getAll();
    //CheckOutCart
}