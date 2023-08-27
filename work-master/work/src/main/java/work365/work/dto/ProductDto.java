package work365.work.dto;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class ProductDto {
    // for create it can be optional
    // for update we need the id
    // private Long id;
    private int code;
    private @NotNull String name;
    private @NotNull String codebarre;
    @Lob
    private @NotNull String imageURL;
    private @NotNull double prix_de_vente;
    private @NotNull String description;
    private @NotNull String unite;
    private @NotNull int qte;
    private @NotNull Boolean available;
    //   private @NotNull Integer subcategoryId;
 /* private @NotNull int categoryId;
    private @NotNull int subcategoryId;
    private @NotNull int marqueId;*/
    //   private @NotNull int promotionId;


    //   private @NotNull Integer subcategoryId;
 /* private @NotNull int categoryId;
    private @NotNull int subcategoryId;
    private @NotNull int marqueId;*/
    //   private @NotNull int promotionId;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    /*  public int getCategoryId() {
         return categoryId;
     }

     public void setCategoryId(int categoryId) {
         this.categoryId = categoryId;
     }

    public Integer getSubCategoryId() {
         return subcategoryId;
     }

     public void setSubCategoryId(Integer subcategoryId) {
         this.subcategoryId = subcategoryId;
     }*/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

 /*   public int getMarqueId() {return marqueId;}

    public void setMarqueId(int marqueId) {this.marqueId = marqueId;}
*/


    // public int getPromotionId() {return promotionId;}
    /*public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }
*/
    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public String getUnite() {
        return unite;
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
}
