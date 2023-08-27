package work365.work.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    Clienttt user;
    @ManyToOne(fetch = FetchType.LAZY)
    Produitts produitts;



    //Long product_id;
    int qty;
    double prix_de_vente;
    String user_id;
    String productName;

    @Lob
    private @NotNull String imageURL;





    public Clienttt getUser() {
        return user;
    }

    public void setUser(Clienttt user) {
        this.user = user;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrix_de_vente() {
        return prix_de_vente;
    }

    public void setPrix_de_vente(double prix_de_vente) {
        this.prix_de_vente = prix_de_vente;
    }

    String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produitts getProduct() {
        return produitts;
    }

    public void setProduct(Produitts produitts) {
        this.produitts = produitts;
    }

    public String getProductName() {
        return produitts.getName();
    }
}
