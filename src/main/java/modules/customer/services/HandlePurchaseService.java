package modules.customer.services;

import modules.customer.entity.Customer;
import modules.customer.repositories.CustomerRepositories;

import modules.purchase.repositories.PurchaseRepositories;
import modules.purchase.services.CreatePurchaseService;

import java.time.LocalDateTime;


public class HandlePurchaseService {

    private CustomerRepositories customerRepository;
    private PurchaseRepositories purchaseRepositories;


    public HandlePurchaseService(CustomerRepositories customerRepository, PurchaseRepositories purchaseRepositories){
        this.customerRepository = customerRepository;
        this.purchaseRepositories = purchaseRepositories;
    }


    public void execute(String store, int amount, LocalDateTime time){


        //Verifica se existe um customer cadastrado
        if(!customerRepository.isExist()){
            //adiciona uma string de erro se não existir customer cadastrado
            System.out.println("{ account: "+ false+ ", "+"avaliable-limit: "+ 0 + ", fouls: " + "[" +"customer-not-exists"+  "]" + " }");
        }else{

            //Armazena uma instancia do customer existente
            Customer customer = customerRepository.getCustomer();
            //Verifica se o customer existente está ativo
            if(!customer.getActive()){

                //adiciona uma string de erro se não o customer não for ativo
                customer.setFoul("customer-not-active");

            }else{

                //Verifica se o customer teu limite para a compra
                if(customer.getAvaliable_limit() < amount){

                    //adiciona uma string de erro se o customer não tiver limite
                    customer.setFoul("insufficient-limit");

                }

                CreatePurchaseService createPurchaseService = new CreatePurchaseService(purchaseRepositories, customerRepository);
                //Cria uma instancia da classe purchase apos a compra ser aceita
                createPurchaseService.execute(store, amount, time);

            }
            System.out.printf(customer.toString());
        }
    }

}
