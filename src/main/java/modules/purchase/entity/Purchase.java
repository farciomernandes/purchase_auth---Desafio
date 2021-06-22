package modules.purchase.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class Purchase {
    private String store;
    private int amount;
    private LocalDateTime time;

    public Purchase(String store, int amount, LocalDateTime time){
        this.store = store;
        this.amount = amount;
        this.time = time;
    }

    @Override
    public String toString(){
        return "%n { purchase: " + " store: " + this.store
                    + " amount: " + this.amount
                    +" time:" + this.time;
    }

    public int getAmount(){
        return this.amount;
    }

    public String getStore(){
        return this.store;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
