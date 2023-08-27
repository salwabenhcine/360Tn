package work365.work.model;


import javax.persistence.*;
import java.util.Date;


@Entity
public class CheckoutCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String orderId;
    String user_id;

   @OneToOne
   @JoinColumn(name = "produitts_code")
   Produitts produitts;
     String statut;
    String email;
     String nom_et_prenom;
     String lastName;
     Date date;
     String ville;
    String gouvernorat;
    String adress;
    String tel;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNom_et_prenom() {
        return nom_et_prenom;
    }

    public void setNom_et_prenom(String nom_et_prenom) {
        this.nom_et_prenom = nom_et_prenom;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }




    public Produitts getProduct() {
        return produitts;
    }

    public void setProduct(Produitts produitts) {
        this.produitts = produitts;
    }

    int qty;
    double prix_de_vente;

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private  Date order_date;

    @PrePersist
    private void onCreate(){
        order_date=new Date();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getOrder_id() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    }
/**
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
**/

