
package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.*;
import work365.work.message.ResponseMessage;
import work365.work.model.Categories;
import work365.work.model.Marque;
import work365.work.model.Produitts;
import work365.work.model.Souscategories;
//import work365.work.payload.Repository.*;
import work365.work.payload.response.ApiResponse;
import work365.work.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    SubCategoryRepo subcategoryRepo;

    @Autowired
    MarqueRepository marqueRepository;

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    ProductRepository productRepository;
    @GetMapping("/produiits")
    public ResponseEntity<List<Produitts>> getAllProducts(@RequestParam(required = false) String name) {
        try {
            List<Produitts> produitts = new ArrayList<Produitts>();

            if (name == null)
                productRepository.findAll().forEach(produitts::add);
            else
                productRepository.findBynameContaining(name).forEach(produitts::add);

            if (produitts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(produitts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/upload" )
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("codebarre") String codebarre,
                                                 @RequestParam("prix_de_vente") double prix_de_vente,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("unite") String unite,
                                                 @RequestParam("qte") int qte,
                                                 @RequestParam("available") Boolean available,
                                                 @RequestParam("idcategorie")  int idcategorie,
                                                 @RequestParam("idsouscategorie") int idsouscategorie,
                                                 @RequestParam("nommarque") String nommarque) {
        String message = "";
        try {

            productService.store(file,name ,codebarre, prix_de_vente, description, unite, qte,available,
                    idcategorie, idsouscategorie, nommarque);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/{code}")
    public ResponseEntity<Produitts> post(@PathVariable int code) {
        Optional<Produitts> prod = productService.findByproductId(code);
        return prod.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }




  /*  @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        Optional<SubCategory> optionalSubCategory = subcategoryRepo.findById(productDto.getSubCategoryId());
        if (!optionalSubCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Marque> optionalMarque = marqueRepository.findById(productDto.getMarqueId());
        if (!optionalMarque.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Brand does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Promotion> optionalPromotion = promotionRepository.findById(productDto.getPromotionId());
        if (!optionalPromotion.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Promotion does not exists"),
                    HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDto, optionalSubCategory.get(), optionalMarque.get(), optionalPromotion.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }
    */


    @GetMapping("/productCategory/{nomcategorie}")
    public List<Produitts> listProductByCategory(@PathVariable String nomcategorie) {
        return productService.listProductByCategories(nomcategorie);
    }
    @GetMapping("/productSubcategory/{nomsouscat}")
    public List<Produitts> listProductBySubcategory(@PathVariable String nomsouscat) {
        return productService.listProductBySouscategorie(nomsouscat);
    }
    @GetMapping("/")
    public List<Produitts> listProduct() {
        return productService.listProduct();
    }
   /* public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }*/
   @GetMapping("/nbrProduit")
   public int nbreProd(){
       return productService.nbreProd();
   }
    // create an api to edit the product
    @PutMapping("/update/{code}")
    public ResponseEntity<ApiResponse> updatePro(@PathVariable("code") int code,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("article") String article,
                                                 @RequestParam("codebarre") String codebarre,
                                                 @RequestParam("prix_de_vente") double prix_de_vente,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("unite") String unite,
                                                 @RequestParam("qte") int qte,
                                                 @RequestParam("available") Boolean available,
                                                 @RequestParam("idcategorie") Categories idcategorie,
                                                 @RequestParam("idsouscategorie") Souscategories idsouscategorie,
                                                 @RequestParam("nommarque") Marque nommarque) throws IOException {
        System.out.println("id " + code);
        if (!productService.findById(code)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "product does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        productService.editProduct(file, code, article, codebarre, prix_de_vente, description, unite, qte, available,
                idcategorie, idsouscategorie, nommarque );
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }
/*
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,
            @RequestBody ProductDto productDto) throws Exception {
        Optional<SubCategory> optionalSubCategory = subcategoryRepo.findById(productDto.getSubCategoryId());
        if (!optionalSubCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "sub-category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Marque> optionalMarque = marqueRepository.findById(productDto.getMarqueId());
        if (!optionalMarque.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Brand does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Category> optionalPromotion = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalPromotion.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }*/

    /* @DeleteMapping("/delete/{id}")
     public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
         productRepository.deleteById(id);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }*/
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("CODE") Integer CODE) {

        //  productRepository.deleteById(id);
        productService.deleteProduct(CODE);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }

}
