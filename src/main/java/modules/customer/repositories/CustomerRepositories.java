package modules.customer.repositories;

import java.util.ArrayList;
import java.util.List;
import modules.customer.entity.Customer;



public class CustomerRepositories {
    List<Customer> customers = new ArrayList<>();

    public void Create(Customer new_customer){
        customers.add(new_customer);
    }

    public boolean isExist(){
        try{
            this.customers.get(0);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public Customer getCustomer(){
        return this.customers.get(0);
    }

    public void HandlePurchase(int amount){
        Customer customer = this.customers.get(0);
            int avaliable_limit = customer.getAvaliable_limit() - amount;
            this.customers.get(0).setAvaliable_limit(avaliable_limit);
    }
}
