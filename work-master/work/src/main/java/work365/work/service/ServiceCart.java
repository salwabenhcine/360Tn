package work365.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work365.work.Repository.CheckoutRepo;
import work365.work.model.CheckoutCart;
import work365.work.model.Recette;

import java.io.IOException;

@Service
public class ServiceCart {

    @Autowired
    CheckoutRepo checkoutRepo;


    public boolean  findById(int id) {
        return checkoutRepo.findById(id).isPresent();
    }

    public void editStatut(int id, String statut )throws IOException {
        CheckoutCart checkoutCart = checkoutRepo.getById(id);
        checkoutCart.setStatut(statut);
        checkoutRepo.save(checkoutCart);
    }



}


