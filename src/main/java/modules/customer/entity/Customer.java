package modules.customer.entity;

import java.util.ArrayList;
import java.util.List;


public class Customer {
    private boolean active;
    private int avaliable_limit;
    List<String> fouls = new ArrayList<>();



    public Customer(boolean active, int avaliable_limit){
        this.active = active;
        this.avaliable_limit = avaliable_limit;
    }

    @Override
    public String toString(){
        return "%n { account: " +
                 "{ active: " + this.active +
                 ",  available-limit: " + this.avaliable_limit
                 + "}," +" fouls: " + this.fouls  + "}"
                ;
    }

    public void setFoul(String fould){
        if(!this.fouls.contains(fould)){
            this.fouls.add(fould);
        }
    }

    public void setAvaliable_limit(int avaliable_limit) {
        if(!this.fouls.contains("insufficient-limit")){
            this.avaliable_limit = avaliable_limit;
        }
    }

    public boolean existFoul(String foul){
        if (this.fouls.contains(foul)){
            return true;
        }else{
            return false;
        }
    }

    public int getAvaliable_limit() {
        return avaliable_limit;
    }

    public int getFouls() {
        return this.fouls.size();
    }

    public boolean getActive() {
        return active;
    }
}
