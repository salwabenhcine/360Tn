package work365.work.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.RecetteRepo;
import work365.work.model.Produitts;
import work365.work.model.Recette;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {

    @Autowired
    RecetteRepo recetteRepo;

    public Recette store(MultipartFile file, String nomRecette, String description, int[] products) throws IOException {
        Recette r = new Recette();
        //Produitts p = new Produitts();
        System.out.println(products);
        List <Produitts> x = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            //System.out.println(products[i]);
            Produitts p = new Produitts();
            p.setCode(products[i]);
            x.add(p);
        }
        //p.setId(productId);
        //RecetteProduct rc = new RecetteProduct();
        //rc.setProduct(p);
        //List <RecetteProduct> x = new ArrayList<>();
        //List <Produitts> x = new ArrayList<>();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            r.setImageRecette(Base64.getEncoder().encodeToString(file.getBytes()));
            //    c.setImage(Base64.getEncoder().encode(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(x);
        r.setNomRecette(nomRecette);
        r.setDescription(description);
        //x.add(p);
        r.setProducts(x);
        return  recetteRepo.save(r);

    }


    public List<Recette> listRecette() {
        return recetteRepo.findAll();
    }

    public Optional<Recette> findByIdrecette(int id) {
        return recetteRepo.findById(id);
    }
    public void editRecette(MultipartFile file , int id, String nomRecette,
                            String description, int[] products) throws IOException {
        Recette recette = recetteRepo.getById(id);
        List <Produitts> x = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            //System.out.println(products[i]);
            Produitts p = new Produitts();
            p.setCode(products[i]);
            x.add(p);
        }
        recette.setNomRecette(nomRecette);
        recette.setDescription(description);
        recette.setImageRecette(Base64.getEncoder().encodeToString(file.getBytes()));
        recette.setProducts(x);
        recetteRepo.save(recette);
    }



    public boolean  findById(int id) {
        return recetteRepo.findById(id).isPresent();
    }

    public void deleteRecette(int id) {

        Optional<Recette> optionalRecette = recetteRepo.findById(id);

        optionalRecette.ifPresent(recetteRepo::delete);

    }

    public Recette getFile(int id) {
        return recetteRepo.findById(id).get();
    }



    public int nbreRecette() {
        return recetteRepo.nbreRecette();
    }


}