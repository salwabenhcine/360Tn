package work365.work.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recette")
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nomRecette;
    @Column(length=10000)
    private String description;
    @Lob
    private String imageRecette;
    /*@OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<RecetteProduct> recetteProducts;*/
    @ManyToMany(
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "recette_produitts",
            joinColumns = { @JoinColumn(name = "recette_id") },
            inverseJoinColumns = { @JoinColumn(name = "produitts_code") })
    private List<Produitts> produitts;



    public Recette() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public void setNomRecette(String nomRecette) {
        this.nomRecette = nomRecette;
    }

    public String getImageRecette() {
        return imageRecette;
    }

    public void setImageRecette(String imageRecette) {
        this.imageRecette = imageRecette;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* public List<RecetteProduct> getRecetteProducts() {
        return recetteProducts;
    }
    public void setRecetteProducts(List<RecetteProduct> recetteProducts) {
        this.recetteProducts = recetteProducts;
    }*/

    public List<Produitts> getProducts() {
        return produitts;
    }

    public void setProducts(List<Produitts> produitts) {
        this.produitts = produitts;
    }
}