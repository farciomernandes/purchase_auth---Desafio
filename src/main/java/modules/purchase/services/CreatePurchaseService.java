package modules.purchase.services;

import modules.customer.repositories.CustomerRepositories;
import modules.purchase.entity.Purchase;
import modules.purchase.repositories.PurchaseRepositories;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CreatePurchaseService {

    private PurchaseRepositories purchaseRepositoriesrepository;
    private CustomerRepositories customerRepositories;

    public CreatePurchaseService(PurchaseRepositories purchaseRepositoriesrepository, CustomerRepositories customerRepositories){
        this.purchaseRepositoriesrepository = purchaseRepositoriesrepository;
        this.customerRepositories = customerRepositories;
    }

    public void execute(String store, int amount, LocalDateTime time){
        List<Purchase> purchases = purchaseRepositoriesrepository.getAll();

        //Contador que armazena a quantidade de compras na mesma loja nos ultimos 2 minutos
        int latestPurchasesIn2Minutes = 1;

        if(purchases.size() >= 3){
            //Percorre as ultimas 3 indices no array de purchases
            for(int k = purchases.size()-2; k < purchases.size(); k++){


                //Verifica se o valor do indice e diferente de null
                if(purchases.get(k) != null){

                    //Verifica se a compra atual e na mesma loja da compra anterior
                    if(purchases.get(k).getStore().equals(purchases.get(k-1).getStore())){

                        //Verifica o intervalo entre a ultima compra e agora
                        long minutes = purchases.get(k).getTime().until(time, ChronoUnit.MINUTES);

                        //Verifica se faz 2 minutos ou menos desde a ultima compra
                        if(minutes <= 2){
                            //Atualiza o contador de compras nos ultimos 2 minutos
                            int count = latestPurchasesIn2Minutes + 1;
                            latestPurchasesIn2Minutes = count;
                        }
                    }

                }
            }

        }

        if(purchases.size() >= 1){
            if(purchases.get(purchases.size()-1).getStore().equals(store)){
                if(purchases.get(purchases.size()-1).getAmount() == amount) {
                    long minutes = purchases.get(purchases.size() - 1).getTime().until(time, ChronoUnit.MINUTES);
                    if (minutes <= 2) {
                        customerRepositories.getCustomer().setFoul("repeated-purchase");

                    }
                }
            }
        }


        //Verifica se o contador esta menor que 3
        if(latestPurchasesIn2Minutes < 3){
            if(!customerRepositories.getCustomer().existFoul("repeated-purchase")){
                Purchase new_purchase = new Purchase(store, amount, time);
                purchaseRepositoriesrepository.Create(new_purchase);
                //Atualiza o limite do customer
                customerRepositories.HandlePurchase(amount);
            }
        }else{
            customerRepositories.getCustomer().setFoul("too-many-purchases");

        }
    }

}
