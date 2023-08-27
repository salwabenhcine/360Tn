package work365.work.model;


import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Souscategories implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,  generator = "souscategories_generator")
    @Column(name = "idsouscategorie")
    // private Long subCategoryId;
    private int idsouscategorie;
    @Column(name = "nomsouscat")
    private @NotBlank String nomsouscat;

    @Column(name = "description")
    private @NotBlank String description;

    @Column(name = "image")
    @Lob
    private String imageUrl;

    //  @Lob
    //  private String data;

    // @ManyToOne( cascade = CascadeType.REMOVE)
    @ManyToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_categories", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Categories categories;

    //  @OneToMany(cascade = CascadeType.REMOVE)
    //  private Set<Product> product;

    public Souscategories() {

    }


    public int getIdsouscategorie() {
        return idsouscategorie;
    }

    public void setIdsouscategorie(int idsouscategorie) {
        this.idsouscategorie = idsouscategorie;
    }

    public String getNomsouscat() {
        return nomsouscat;
    }

    public void setNomsouscat(String nomsouscat) {
        this.nomsouscat = nomsouscat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categories getCategories() {
        return categories;
    }


    public void setCategories(Categories categories) {
        this.categories = categories;
    }



}
