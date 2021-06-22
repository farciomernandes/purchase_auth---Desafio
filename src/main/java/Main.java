import modules.customer.repositories.CustomerRepositories;
import modules.customer.services.CreateCustomerService;
import modules.customer.services.HandlePurchaseService;
import modules.purchase.repositories.PurchaseRepositories;
import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {



        CustomerRepositories customerRepositories = new CustomerRepositories();
        PurchaseRepositories purchaseRepositories = new PurchaseRepositories();

        CreateCustomerService createCustomerService = new CreateCustomerService(customerRepositories);

        HandlePurchaseService handlePurchaseService = new HandlePurchaseService(customerRepositories,purchaseRepositories);

        JSONParser parser = new JSONParser();
        JSONArray operations = (JSONArray) parser.parse(new InputStreamReader(System.in));

        operations.forEach(x->{
            JSONObject item = (JSONObject) x;
            JSONObject filterCustomer = (JSONObject) item.getOrDefault("customer", null);
            JSONObject filterPurchases = (JSONObject) item.getOrDefault("purchase", null);

            if(filterCustomer != null){
                boolean active = (boolean) filterCustomer.getOrDefault("active", null);
                int available_limit = Integer.valueOf(String.valueOf(filterCustomer.getOrDefault("available-limit", null)));
                createCustomerService.execute(active,available_limit );
            }

            if (filterPurchases != null){
                String store = (String) filterPurchases.getOrDefault("store", null);
                int amount = Integer.valueOf(String.valueOf(filterPurchases.getOrDefault("amount", null)));


                String stringTime = String.valueOf(filterPurchases.getOrDefault("time", null));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime time = LocalDateTime.parse(stringTime, formatter);

                handlePurchaseService.execute(store, amount, time);
            }

        });

    }

}