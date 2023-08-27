package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work365.work.Repository.MarqueRepository;
import work365.work.message.ResponseMessage;
import work365.work.model.Marque;
import work365.work.payload.response.ApiResponse;
import work365.work.service.MarqueService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/marque")
public class MarqueController {

    @Autowired
    MarqueRepository marqueRepository;

    @Autowired
    MarqueService marqueService;

    @GetMapping("/marques")
    public ResponseEntity<List<Marque>> getAllMarques(@RequestParam(required = false) String nommarque) {
        try {
            List<Marque> marques = new ArrayList<Marque>();

            if (nommarque == null)
                marqueRepository.findAll().forEach(marques::add);
            else
                marqueRepository.findBynommarqueContaining(nommarque).forEach(marques::add);

            if (marques.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(marques, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload" )
    public ResponseEntity<ResponseMessage> store(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("file1") MultipartFile file1,
                                                 @RequestParam("nommarque") String nommarque,
                                                 @RequestParam("teleMarque") String teleMarque,
                                                 @RequestParam("emailMarque") String emailMarque) {
        String message = "";
        try {

            marqueService.store(file, file1, nommarque, teleMarque, emailMarque);

            message = "Uploaded the file successfully: " + file.getOriginalFilename()
                                                         + file1.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename()
                                                    + file1.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/nbrMarque")
    public int nbreCategory(){
        return marqueService.nbreMarque();
    }


    @GetMapping("/")
    public List<Marque> listMarque() {
        return marqueService.listMarque();
    }

    @GetMapping("/{idmarque}")
    public ResponseEntity<Marque> post(@PathVariable String  nommarque) {
        Optional<Marque> mrq = marqueService.findBymrqId(nommarque);
        return mrq.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

@PutMapping("/update/{idmarque}")
    public ResponseEntity<ApiResponse> editMarque(@PathVariable("idmarque") int idmarque,
                                                  @RequestParam("file") MultipartFile file,
                                                  @RequestParam("file1") MultipartFile file1,
                                                  @RequestParam("nommarque") String nommarque,
                                                  @RequestParam("teleMarque") String teleMarque,
                                                  @RequestParam("emailMarque") String emailMarque) throws IOException {

        System.out.println("marque id " + idmarque);
        if (!marqueService.findById(nommarque)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Brand does not exists"),
                    HttpStatus.NOT_FOUND);
        }
        marqueService.editMarque(idmarque, file, file1, nommarque, teleMarque, emailMarque);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Brand has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{nommarque}")
    public ResponseEntity<ApiResponse> deleteMarque(@PathVariable("nommarque") String nommarque) {

        marqueService.deleteMarque(nommarque);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }


    /*
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMarque(@RequestBody Marque marque) {
        marqueService.createMarque(marque);
        return new ResponseEntity<>(new ApiResponse(true, "a new Brand created"), HttpStatus.CREATED);
    }
    */
}
