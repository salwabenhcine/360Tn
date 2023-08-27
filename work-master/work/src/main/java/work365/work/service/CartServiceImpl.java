package work365.work.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;
import work365.work.Repository.AddToCartRepo;
import work365.work.Repository.CheckoutRepo;
import work365.work.model.*;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    AddToCartRepo addCartRepo;
    @Autowired
    CheckoutRepo checkOutRepo;
    @Autowired
    ProductService proServices;
@Autowired
UserService userService;
@Autowired
WishListServiceImpl wishListService;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
@Override
public Optional<CheckoutCart> getById(int id){
    return checkOutRepo.findById(id);
}

    @Override
    public CheckoutCart getCheckout(String orderId) {
        return checkOutRepo.getCheckout(orderId);
    }


    @Override
    public List<Panier> addCartbyUserIdAndProductId(int productId, String userId, int qty, double prix_de_vente, String productName, String imageURL) throws Exception {
        try {
            if(addCartRepo.getCartByProduittsIdAnduserId(userId, productId).isPresent()){
                throw new Exception("Product is already exist.");
            }
            Panier obj = new Panier();
            Clienttt user = userService.getUser(userId);
            obj.setUser(user);
            obj.setQty(qty);
            obj.setUser_id(user.getEmail());
            obj.setProductName(productName);
            obj.setImageURL(imageURL);
            Produitts pro = proServices.getProductsById(productId);
            obj.setProduct(pro);

            obj.setPrix_de_vente(prix_de_vente);
            addCartRepo.save(obj);
            return this.getCartByUserId(userId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<Panier> getCartByUserId(String userId) {
        return addCartRepo.getCartByuserId(userId);
    }
    @Override
    public List<CheckoutCart> getCheckoutCartByOrderId(String orderId) {
        return checkOutRepo.getAllCheckoutByorderId(orderId);
    }
    @Override
    public List<CheckoutCart> getCheckoutCartByUserId(String userId) {
        return checkOutRepo.getAllCheckoutByuserId(userId);
    }

    @Override
    public List<Panier> removeCartByUserId(int cartId, String userId) {
        addCartRepo.deleteCartByIdAndUserId(userId,  cartId);
        return this.getCartByUserId(userId);
    }

    @Override
    public void updateQtyByCartId(int cartId, int qty, double prix_de_vente) throws Exception {
        addCartRepo.updateQtyByCartId(cartId,prix_de_vente,qty);
    }

    @Override
    public Boolean checkTotalAmountAgainstCart(double totalAmount,String userId) {
        double total_amount =addCartRepo.getTotalAmountByUserId(userId);
        if(total_amount == totalAmount) {
            return true;
        }
        System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
        return false;
    }




    @Override
    public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
        try {
            if(tmp.size() > 0) {
                String user_id = tmp.get(0).getUser_id();
                checkOutRepo.saveAll(tmp);
                this.removeAllCartByUserId(user_id);
                return this.getCheckoutCartByUserId(user_id);
            } else {
                throw new Exception("List should not be empty");
            }
        } catch(Exception e) {
            throw new Exception("Error while checkout " + e.getMessage());
        }
    }




    @Override
    public List<Panier> removeAllCartByUserId(String userId) {
        addCartRepo.deleteAllCartByUserId(userId);
        return null;
    }
    @Override
    public List<CheckoutCart> getAll() {
        return checkOutRepo.findAll();
    }
}