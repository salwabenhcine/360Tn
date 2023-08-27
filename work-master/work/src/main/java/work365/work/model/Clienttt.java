package work365.work.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Clienttt {
    Random random = new Random();
    private int id_client = random.nextInt(1000000);
    @Id
    private String email;
    private String password;
    private String nom_et_prenom;
    private String lastName;
    private Date date;
    private String profession;
    private String ville;
    private String gouvernorat;
    private String adress;
    private String Civilite;
    private String nom_enfant_1;
    private String nom_enfant_2;
    private String nom_enfant_3;
    private String nom_enfant_4;
    private Date Date_de_naissance1;
    private Date Date_de_naissance2;
    private Date Date_de_naissance3;
    private Date Date_de_naissance4;
    private  String tel;
    private String situation_familiale;
    private Date date1;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CLIENTTT_ROLE",
            joinColumns = {
                    @JoinColumn(name = "CLIENTTT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
    private  boolean enabled;
    private  String verificationCode;
    @Lob
    @Column(name = "Clienttt_image")
    private String image;


}



