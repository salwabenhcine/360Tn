package work365.work.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work365.work.Repository.WishListRepository;
import work365.work.model.Produitts;
import work365.work.model.Clienttt;
import work365.work.model.WishList;

import java.util.List;
@Service
public class WishListServiceImpl implements WishListService{
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    UserService userService;

    @Autowired
    ProductService proServices;
    private static final Logger logger = LoggerFactory.getLogger(WishListServiceImpl.class);

    @Override
    public List<WishList> addtWishbyUserIdAndProductId(int productId, String userId, int qty, double prix_de_vente, String productName, String imageURL) throws Exception {
        try {
            if(wishListRepository.getWishByProduittsCODEAnduserId(userId,  productId).isPresent()){
                throw new Exception("Product is already exist.");
            }
            WishList obj = new WishList();
            Clienttt user = userService.getUser(userId);
            obj.setUser(user);
            obj.setQty(qty);
            obj.setUser_id(user.getEmail());
            obj.setProductName(productName);
            obj.setImageURL(imageURL);
            Produitts pro = proServices.getProductsById(productId);

            obj.setProduct(pro);
            obj.setPrix_de_vente(prix_de_vente);
            wishListRepository.save(obj);
            return this.getWishByUserId(userId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            throw new Exception(e.getMessage());
        }

    }
    @Override
    public List<WishList> getWishByUserId(String userId) {
        return wishListRepository.getWishByuserId(userId);
    }

    @Override
    public List<WishList> removeWishByUserId(int cartId, String userId) {
        wishListRepository.deleteCartByIdAndUserId(userId, cartId);
        return this.getWishByUserId(userId);
    }
}
