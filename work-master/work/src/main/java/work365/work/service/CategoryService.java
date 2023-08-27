package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.CategoryRepo;
import work365.work.model.Categories;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


  /* public void save(MultipartFile file , Category category) throws IOException {
        category.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        categoryRepo.save(category).getCategoryId();
    }*/
  public int nbreCategory() {
      return categoryRepo.nbreCategory();
  }

    public Categories store(MultipartFile file, String nomcategorie, String description) throws IOException {
        Categories c = new Categories();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        //    c.setImage(Base64.getEncoder().encode(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.setNomcategorie(nomcategorie);
        c.setDescription(description);

        return  categoryRepo.save(c);

    }
    /*


    public Category store(MultipartFile file ) throws IOException {
        Category c = new Category();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

            //    c.setImage(Base64.getEncoder().encode(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.setCategoryName(c.getCategoryName());
       c.setDescription(c.getDescription());
    //    c.setCategoryName(categoryName);
      //  c.setDescription(description);

        return  categoryRepo.save(c);

    }
 */

    public List<Categories> listCategory() {
        return categoryRepo.findAll();
    }

    public Optional<Categories> findByIdcategorie(int idcategorie) {
        return categoryRepo.findById(idcategorie);
    }
    public void editCategory(MultipartFile file ,int idcategorie, String nomcategorie,
                             String description) throws IOException{
        Categories categories = categoryRepo.getById(idcategorie);

    //   Category  c = new Category();
        categories.setNomcategorie(nomcategorie);
        categories.setDescription(description);
        categories.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        categoryRepo.save(categories);
    }


    public boolean  findById(int idcategorie) {
        return categoryRepo.findById(idcategorie).isPresent();
    }

    public void deleteCategory(int idcategorie) {

        Optional<Categories> optionalCategories = categoryRepo.findById(idcategorie);

        optionalCategories.ifPresent(categoryRepo::delete);

    }


    public Categories getFile(int idcategorie) {
        return categoryRepo.findById(idcategorie).get();
    }


  public void createCategory(Categories categories)  {

      categoryRepo.save(categories);
    }

    /*    public void editCategory(int categoryId, Category updateCategory) {
        Category category = categoryRepo.getById(categoryId);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImage(updateCategory.getImage());
        categoryRepo.save(category);
    }*/
}
