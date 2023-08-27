package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.SubCategoryRepo;
import work365.work.message.ResponseMessage;
import work365.work.model.Categories;
import work365.work.model.Souscategories;
import work365.work.payload.response.ApiResponse;
import work365.work.service.SubCategoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {

    @Autowired
    SubCategoryRepo subcategoryRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;

    @Autowired
    SubCategoryService subcategoryService;

    @GetMapping("/SubCat/{nomcategorie}")
    public List<Souscategories> listSubCategoryByCategory(@PathVariable String nomcategorie) {
        return subcategoryService.listSubCategoryByCategory(nomcategorie);
    }
    @GetMapping("/productSubcategory/{nomsouscat}")
    public List<Souscategories> listSubCategoryBySubcategory(@PathVariable String nomsouscat) {
        return subcategoryService.listSubCategoryBySubcategory(nomsouscat);
    }


    @GetMapping("/subcategories")
    public ResponseEntity<List<Souscategories>> getAllSubCategories(@RequestParam(required = false) String nomsouscat) {
        try {
            List<Souscategories> subCategories = new ArrayList<Souscategories>();

            if (nomsouscat == null)
                subCategoryRepo.findAll().forEach(subCategories::add);
            else
                subCategoryRepo.findByNomsouscatContaining(nomsouscat).forEach(subCategories::add);

            if (subCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nbrSubCategory")
    public int nbreSubCategory(){
        return subcategoryService.nbreSubCategory();
    }
    @PostMapping("/upload" )
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("nomsouscat") String nomsouscat,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("idcategorie") int idcategorie){
        String message = "";
        try {

            subcategoryService.store(file, nomsouscat, description, idcategorie);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @GetMapping("/")
    public List<Souscategories> listSubCategory() {
        return subcategoryService.listSubCategory();
    }



    @GetMapping("/{idsouscategorie}")
    public ResponseEntity<Souscategories> post(@PathVariable int idsouscategorie) {
        Optional<Souscategories> sub = subcategoryService.findByIdsouscategorie(idsouscategorie);
        return sub.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PutMapping("/update/{idsouscategorie}")
    public ResponseEntity<ApiResponse> updateSubCategory(@PathVariable("idsouscategorie") int idsouscategorie,
                                                         @RequestParam("file") MultipartFile file,
                                                         @RequestParam("nomsouscat") String nomsouscat,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("idcategorie") Categories idcategorie ) throws IOException {
        System.out.println("subcategory id " + idsouscategorie);
        if (!subcategoryService.findById(idsouscategorie)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        subcategoryService.editSubCategory(file, idsouscategorie, nomsouscat, description, idcategorie);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "sub-category has been updated"), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{idsouscategorie}")
    public ResponseEntity<ApiResponse> deleteSubCategory(@PathVariable("idsouscategorie") Integer idsouscategorie) {

    	subcategoryService.deleteSouscategorie(idsouscategorie);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }
     /*   @PostMapping("/create")
    public ResponseEntity<ApiResponse> createSubCategory(@RequestBody SubCategory subcategory) {
        subcategoryService.createSubCategory(subcategory);
        return new ResponseEntity<>(new ApiResponse(true, "a new sub-category created"), HttpStatus.CREATED);
    }
*/

   /*

    @PostMapping("/update/{subcategoryId}")
    public ResponseEntity<ApiResponse> updateSubCategory(@PathVariable("subcategoryId") int subcategoryId,
            @RequestBody SubCategory subcategory) {
        System.out.println("subcategory id " + subcategoryId);
        if (!subcategoryService.findById(subcategoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        subcategoryService.editSubCategory(subcategoryId, subcategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "sub-category has been updated"), HttpStatus.OK);
    }
    */
}
