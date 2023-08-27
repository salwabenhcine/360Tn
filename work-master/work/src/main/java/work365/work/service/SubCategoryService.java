package work365.work.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.SubCategoryRepo;
import work365.work.model.Categories;
import work365.work.model.Souscategories;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepo subcategoryRepo;


 public Souscategories store(MultipartFile file, String nomsouscat,
                          String description,
                          int idcategorie) throws IOException {
     Souscategories s = new Souscategories();
     Categories c = new Categories();
     c.setIdcategorie(idcategorie);
     //categoryId = c.setCategoryId(categoryId);
     String fileName = StringUtils.cleanPath(file.getOriginalFilename());
     if(fileName.contains(".."))
     {
         System.out.println("not a a valid file");
     }
     try {
         s.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
     } catch (IOException e) {
         e.printStackTrace();
     }
     s.setNomsouscat(nomsouscat);
     s.setDescription(description);
     s.setCategories(c);
     return  subcategoryRepo.save(s);

 }

    public void store(MultipartFile file){}
    public List<Souscategories> listSubCategory() {
        return subcategoryRepo.findAll();
    }

    public Optional<Souscategories> findByIdsouscategorie(int idsouscategorie) {
        return subcategoryRepo.findById(idsouscategorie);
    }

    public List<Souscategories> listSubCategoryByCategory(String nomcategorie) {
        return subcategoryRepo.findByCategoriesNomcategorie(nomcategorie);
    }
    public List<Souscategories> listSubCategoryBySubcategory(String nomsouscat) {
        return subcategoryRepo.findByNomsouscat(nomsouscat);
    }
    public void editSubCategory(MultipartFile file , int idsouscategorie, String nomsouscat,
                                String description, Categories  idcategorie) throws IOException {
        Souscategories souscategories = subcategoryRepo.getById(idsouscategorie);
        souscategories.setNomsouscat(nomsouscat);
        souscategories.setDescription(description);
        souscategories.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
        souscategories.setCategories(idcategorie);
        subcategoryRepo.save(souscategories);
    }

    public boolean findById(int idsouscategorie) {
        return subcategoryRepo.findById(idsouscategorie).isPresent();
    }
    
    public void deleteSouscategorie(Integer idsouscategorie) {

        Optional<Souscategories> optionalSouscategories = subcategoryRepo.findById(idsouscategorie);

        optionalSouscategories.ifPresent(subcategoryRepo::delete);

    }
    public int nbreSubCategory() {
        return subcategoryRepo.nbreSubCategory();
    }


     /*  public void createSubCategory(SubCategory subcategory) {
        subcategoryRepo.save(subcategory);
    }
*/

      /*  public void editSubCategory(int subcategoryId, SubCategory updateSubCategory) {
        SubCategory subcategory = subcategoryRepo.getById(subcategoryId);
        subcategory.setSubcategoryName(updateSubCategory.getSubcategoryName());
        subcategory.setDescription(updateSubCategory.getDescription());
        subcategory.setImageUrl(updateSubCategory.getImageUrl());
        subcategory.setCategory(updateSubCategory.getCategory());
        subcategoryRepo.save(subcategory);
    }*/



}
