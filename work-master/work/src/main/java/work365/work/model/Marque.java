package work365.work.model;


import javax.persistence.*;

@Entity
    public class Marque {

       @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private int idmarque;
        @Id
        private String nommarque;
        @Lob
        private String Logo;
        private String teleMarque;
        private String emailMarque;
        @Lob
        private String contrat;

        //   @OneToMany(cascade = CascadeType.REMOVE)
        //  private Set<Product> product;

        public int getIdmarque() {
            return idmarque;
        }

        public void setIdmarque(int idmarque) {
            this.idmarque = idmarque;
        }

        public String getNommarque() {
            return nommarque;
        }

        public void setNommarque(String nomMarque) {
            this.nommarque = nommarque;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String logo) {
            Logo = logo;
        }

        public String getTeleMarque() {
            return teleMarque;
        }

        public void setTeleMarque(String teleMarque) {
            this.teleMarque = teleMarque;
        }

        public String getEmailMarque() {
            return emailMarque;
        }

        public void setEmailMarque(String emailMarque) {
            this.emailMarque = emailMarque;
        }

        public String getContrat() {
            return contrat;
        }

        public void setContrat(String contrat) {
            this.contrat = contrat;
        }

}
