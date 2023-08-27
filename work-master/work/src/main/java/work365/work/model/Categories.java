package work365.work.model;


import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_generator")
    private int idcategorie;
    // private long categoryId;

    @Column(name = "nomcategorie")
    private String nomcategorie;
    @Column(name = "categories_description")
    private String description;
    @Lob
    @Column(name = "categories_image")
    private String image;
    //  private byte[] image;


    /*
        @OneToMany(cascade = CascadeType.REMOVE)
      // @OneToMany(cascade = CascadeType.MERGE)
        private Set<SubCategory> subCategory;
    */
    public Categories() {
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
