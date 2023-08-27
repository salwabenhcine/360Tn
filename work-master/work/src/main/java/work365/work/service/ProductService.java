package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.ProductRepository;
import work365.work.dto.ProductDto;
import work365.work.model.Categories;
import work365.work.model.Marque;
import work365.work.model.Produitts;
import work365.work.model.Souscategories;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public Produitts store(MultipartFile file, String name, String codebarre, double prix_de_vente,
                         String description, String unite, int qte, Boolean available
            , int idcategorie, int idsouscategorie, String nommarque) throws IOException {
        Produitts p = new Produitts();
        //  ProductDto productDto = new ProductDto();
        Marque m = new Marque();
        m.setNommarque(nommarque);
        Souscategories s = new Souscategories();
        s.setIdsouscategorie(idsouscategorie);
        Categories c = new Categories();
        c.setIdcategorie(idcategorie);
        //  Promotion pr = new Promotion();
        // pr.setPromotionId(promotionId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            p.setImageURL(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setName(name);
        p.setDescription(description);
        p.setCodebarre(codebarre);
        p.setAvailable(available);
        p.setUnite(unite);
        p.setQte(qte);
        p.setPrix_de_vente(prix_de_vente);
        p.setMarques(m);
        //  p.setPromotion(pr);
        p.setSouscategories(s);
        p.setCategories(c);

        return productRepository.save(p);

    }

    /*   public void createProduct(ProductDto productDto, SubCategory subcategory, Marque marque,
                                 Promotion promotion) {
           Product product = new Product();
           product.setDescription(productDto.getDescription());
           product.setImageURL(productDto.getImageURL());
           product.setName(productDto.getName());
           product.setSubCategory(subcategory);
           product.setMarque(marque);
           product.setPromotion(promotion);
           product.setPrice(productDto.getPrice());
           product.setAvailable(productDto.getAvailable());
           product.setCodeBarre(productDto.getCodeBarre());
           product.setUnite(productDto.getUnite());
           product.setQte(productDto.getQte());
           productRepository.save(product);
       }
   */
    public ProductDto getProductDto(Produitts product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCode(product.getSouscategories().getIdsouscategorie());
        productDto.setCode(product.getCategories().getIdcategorie());
        productDto.setCode(product.getMarques().getIdmarque());
        //   product.setId(product.getPromotion().getPromotionId());
        productDto.setPrix_de_vente(product.getPrix_de_vente());
        productDto.setAvailable(product.getAvailable());
        productDto.setQte(product.getQte());
        productDto.setUnite(product.getUnite());
        productDto.setCodebarre(product.getCodebarre());

        productDto.setCode(product.getCode());
        return productDto;
    }

    public List<Produitts> listProduct() {
        return productRepository.findAll();
    }

    public List<Produitts> listProductByCategories(String nomcategorie) {
        return productRepository.findByCategoriesNomcategorie(nomcategorie);
    }
    public List<Produitts> listProductByCheckoutCart(int id) {
        return productRepository.findByCheckoutCartId(id);
    }

    public List<Produitts> listProductBySouscategorie(String nomsouscat) {

        return productRepository.findBySouscategoriesNomsouscat(nomsouscat);
    }

    public List<ProductDto> getAllProducts() {
        List<Produitts> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for (Produitts product : allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public Optional<Produitts> findByproductId(int CODE) {
        return productRepository.findById(CODE);
    }


    public void updateProduct(ProductDto productDto, int productId) throws Exception {
        Optional<Produitts> optionalProduct = productRepository.findById(productId);
        // throw an exception if product does not exists
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        Produitts product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrix_de_vente(productDto.getPrix_de_vente());
        product.setQte(productDto.getQte());
        product.setUnite(productDto.getUnite());
        product.setAvailable(productDto.getAvailable());
        product.setCodebarre(productDto.getCodebarre());

        productRepository.save(product);
    }

   /* public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }*/

    public int nbreProd() {
        return productRepository.nbreProd();
    }

    public boolean findById(int code) {
        return productRepository.findById(code).isPresent();
    }

    public void deleteProduct(int code) {

        Optional<Produitts> optionalProduct = productRepository.findById(code);

        optionalProduct.ifPresent(productRepository::delete);

    }
    public Produitts getProductsById(int productId) throws Exception {
        return productRepository.findById(productId).orElseThrow(() ->new Exception("Product is not found"));
    }

    public void editProduct(MultipartFile file, int code, String name, String codebarre, double prix_de_vente,
                            String description, String unite, int qte, Boolean available,
                            Categories nomcategorie, Souscategories subCategoryId, Marque nommarque) throws IOException {
        Produitts product = productRepository.getById(code);
        product.setName(name);
        product.setCodebarre(codebarre);
        product.setPrix_de_vente(prix_de_vente);
        product.setDescription(description);
        product.setUnite(unite);
        product.setQte(qte);
        product.setAvailable(available);
        product.setImageURL(Base64.getEncoder().encodeToString(file.getBytes()));
        product.setCategories(nomcategorie);
        product.setSouscategories(subCategoryId);
        product.setMarques(nommarque);

        productRepository.save(product);
    }



   /* public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }*/

}