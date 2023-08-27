package work365.work.model;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.StringType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Entity
public class Produitts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private @NotNull String name;
    private @NotNull String codebarre;

    private @NotNull double prix_de_vente;
    private @NotNull String description;
    private @NotNull String details;

    private @NotNull String unite;
    private @NotNull int qte;
    private @NotNull Boolean available;

    @ColumnDefault("0")
    private Integer productStatus;
    @NotNull
    @Min(0)
    private Integer productStock;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_categories", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categories categories;
    // Many to one relationship
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_souscategorie", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Souscategories souscategories;

    // Many to one relationship
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nommarque_marque", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Marque marques;
    // one to one relationship
    @OneToOne(cascade = CascadeType.ALL)
     private CheckoutCart checkoutCart;
    private Integer quantity;

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String added_on;

    public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String added_on) {
        this.added_on = added_on;
    }

    @Lob
    private @NotNull String imageURL;// Many to one relationship
  /*  @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "promotion_id")
    Promotion promotion;
*/

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public void setSubcategory(Souscategories souscategories) {
        this.souscategories = souscategories;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrix_de_vente() {
        return prix_de_vente;
    }

    public void setPrix_de_vente(double prix_de_vente) {
        this.prix_de_vente = prix_de_vente;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public Souscategories getSouscategories() {
        return souscategories;
    }

    public void setSouscategories(Souscategories souscategories) {
        this.souscategories = souscategories;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Marque getMarques() {return marques; }

    public void setMarques(Marque marques) {this.marques = marques;}

 /*   public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
*/


    public String getUnite() {
        return unite;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public Produitts(){
        this.quantity=1;
    }

}
