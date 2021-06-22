package modules.customer.services;

import modules.customer.repositories.CustomerRepositories;
import modules.customer.entity.Customer;

public class CreateCustomerService {

    private CustomerRepositories repository;

    public CreateCustomerService(CustomerRepositories repository){
        this.repository = repository;
    }

    public void execute(boolean active, int avaliable_limit){

        boolean checkExist = this.repository.isExist();

        if(checkExist == true){
            Customer customer = repository.getCustomer();
            customer.setFoul("customer-already-created");
            System.out.printf(customer.toString());
        }else{
            Customer customer = new Customer(active, avaliable_limit);
            repository.Create(customer);
            System.out.printf(customer.toString());
        }
    }
}
