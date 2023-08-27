package work365.work.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work365.work.configuration.ShoppingConfiguration;
import work365.work.controller.Pojo.ApiResponse;
import work365.work.model.Panier;
import work365.work.service.CartService;

//Panier
@RestController
@RequestMapping("api/addtocart")
public class AddtoCartController {

    @Autowired
    CartService cartService;
    @RequestMapping("addProduct")
    public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"productId","userId","qty","prix_de_vente","productName","imageURL" };
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            String productName= addCartRequest.get("productName");
            String imageURL  = addCartRequest.get("imageURL");
            int productId = Integer.parseInt(addCartRequest.get("productId"));
            String userId =  addCartRequest.get("userId");
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double prix_de_vente = Double.parseDouble(addCartRequest.get("prix_de_vente"));
            List<Panier> obj = cartService.addCartbyUserIdAndProductId(productId,userId,qty,prix_de_vente,productName,imageURL);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }

    @RequestMapping("updateQtyForCart")
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"cartId","userId","qty","prix_de_vente"};
            if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

            }
            int cartId =Integer.parseInt(addCartRequest.get("cartId"));
            String userId =  addCartRequest.get("userId");
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double prix_de_vente = Double.parseDouble(addCartRequest.get("prix_de_vente"));
            cartService.updateQtyByCartId( cartId, qty, prix_de_vente);
            List<Panier> obj = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }



    @RequestMapping("removeProductFromCart")
    public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
        try {
            String keys[] = {"userId","cartId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

            }
            List<Panier> obj = cartService.removeCartByUserId(Integer.parseInt(removeCartRequest.get("cartId")),
                    removeCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    @RequestMapping("removeProductsFromCart")
    public ResponseEntity<?> removeProductsFromCart(@RequestBody HashMap<String,String> removeCartRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

            }
            List<Panier> obj = cartService.removeAllCartByUserId(removeCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @RequestMapping("getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
            }
            List<Panier> obj = cartService.getCartByUserId(getCartRequest.get("userId"));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    
}