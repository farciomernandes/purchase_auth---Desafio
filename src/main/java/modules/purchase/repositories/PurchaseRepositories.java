package modules.purchase.repositories;

import java.util.ArrayList;
import java.util.List;

import modules.purchase.entity.Purchase;

public class PurchaseRepositories {
    List<Purchase> purchases = new ArrayList<>();

    public void Create(Purchase new_purchase){
        this.purchases.add(new_purchase);
    }

    public Purchase getPurchase(){
        return this.purchases.get(0);
    }

    public List<Purchase> getAll(){
        return this.purchases;
    }
}
