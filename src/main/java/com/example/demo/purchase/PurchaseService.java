package com.example.demo.purchase;

import com.cedarsoftware.util.io.JsonWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getPurchases()
    {
        return purchaseRepository.findAll();
    }

    public void addNewPurchase(Purchase purchase) {
        if(purchase.getDop()==null)
            purchase.setDop(LocalDate.now());
        purchaseRepository.save(purchase);
    }

    public void deletePurchase(Long purchaseId) {
        boolean exists = purchaseRepository.existsById(purchaseId);
        if(!exists){
            throw new IllegalStateException("purchase with id " + purchaseId + " does not exists");
        }
        purchaseRepository.deleteById(purchaseId);
    }

    @Transactional
    public void updatePurchase(Long purchaseId, String type, String subType, Long value, LocalDate dop){
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new IllegalStateException("purchase with id " + purchaseId + " does not exists"));
        if(type !=null && type.length()>0){
            purchase.setType(type);
        }

        if(subType !=null && subType.length()>0){
            purchase.setSubType(subType);
        }

        if(value != null){
            purchase.setValue(value);
        }

        if(dop != null){
            purchase.setDop(dop);
        }
    }
}
