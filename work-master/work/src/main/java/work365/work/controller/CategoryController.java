package work365.work.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.CategoryRepo;
import work365.work.message.ResponseMessage;
import work365.work.model.Categories;
import work365.work.payload.response.ApiResponse;
import work365.work.service.CategoryService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/category")
@CrossOrigin( maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {


    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ServletContext context;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/nbrCategory")
    public int nbreCategory(){
        return categoryService.nbreCategory();
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getAllCategories(@RequestParam(required = false) String nomcategorie) {

            List<Categories> categories = new ArrayList<Categories>();

            if (nomcategorie == null)
                categoryRepo.findAll().forEach(categories::add);
            else
                categoryRepo.findBynomcategorieContaining(nomcategorie).forEach(categories::add);

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        }







  @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("nomcategorie") String nomcategorie,
                                                 @RequestParam("description") String description

  ) {  String message = "";

        try {


        //    categoryService.store(file);
            categoryService.store(file, nomcategorie, description);


            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Categories categories) {

        categoryService.createCategory(categories);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }


    @GetMapping("/")
    public List<Categories> listCategory() {
        return categoryService.listCategory();
    }

    @GetMapping("/{idcategorie}")
    public ResponseEntity<Categories> post(@PathVariable int idcategorie) {
        Optional<Categories> cat = categoryService.findByIdcategorie(idcategorie);
        return cat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }



    @PutMapping("/update/{idcategorie}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable("idcategorie") int idcategorie,
                                                    @RequestParam("file") MultipartFile file,
                                                    @RequestParam("nomcategorie") String nomcategorie,
                                                    @RequestParam("description") String description) throws IOException {
        System.out.println("category id " + idcategorie);
        if (!categoryService.findById(idcategorie)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(file, idcategorie, nomcategorie, description);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);

    }



    @DeleteMapping("/delete/{idcategorie}")
    public ResponseEntity<ApiResponse> deleteCategorie(@PathVariable("idcategorie") Integer idcategorie) {


        categoryService.deleteCategory(idcategorie);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }
}
   /* @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable("categoryId") int categoryId,
            @RequestBody Category category) {
        System.out.println("category id " + categoryId);
        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }*/
/*

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryRepo.deleteById(categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



  @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
       categoryService.delete(categoryId);
    }
*/


  /*  @GetMapping("/images/{categoryId}")
    public byte[] getPhoto(@PathVariable("categoryId") int categoryId) throws Exception{
        Category Category = categoryRepo.findById(categoryId).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/images/")));
    }


   @GetMapping("/images/{categoryId}")
    public ResponseEntity<byte[]> getFile(@PathVariable int categoryId) {
        Category category = categoryService.getFile(categoryId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; ")
                .body(category.getImage());

    }

    @GetMapping(path="/images/{categoryId}")
    public ResponseEntity<byte[]> getFile(@PathVariable int categoryId) throws Exception{
    //    Category Category = categoryRepo.findById(categoryId).get();
      //  return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Category.getImage()));
        Category category = categoryService.getFile(categoryId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement;", context.getRealPath("/images/"))
                .body(category.getImage());

    }

   */





