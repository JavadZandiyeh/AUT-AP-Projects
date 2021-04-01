import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * this class has a map for the provinces that each each province
 * has an ArrayList of Stores and each store has a list of crops
 * @version 0.2
 * @author Mohammad Javad Zandiyeh
 */
public class Country {

    //a nested map for provinces an their stores
    private HashMap< String, ArrayList<Store>> provinces;

    /**
     * constructor of Country class
     */
    public Country(){
       provinces = new HashMap<String, ArrayList<Store>>();
    }

    /**
     * add a province to our country with its name
     * @param province name of province to add
     */
    public void addProvince(String province) {
        provinces.put(province, new ArrayList<Store>());
    }

    /**
     * add store to the province we want
     * @param province name of province to add store to it
     * @param store the store we want to add it to our province
     */
    public void addStore(String province, Store store){
        provinces.get(province).add(store);
    }

    /**
     * add a crop with its name and its price to the province and store we want
     * @param province name of province we want to add crop to one of its stores
     * @param store name of store we want add a crop to it
     * @param forbiddenCrops list of forbidden crop that lead to dissolve authorization
     * @param price price of crop we want to add it to our store
     * @param name name of crop we want to add it to our store
     */
    public void addCrop(String province, Store store, HashSet<String> forbiddenCrops,  Integer price, String name){

        if((authorization(name, forbiddenCrops))&&(provinces.get(province).get(provinces.get(province).indexOf(store)).getAuthorization())){
            provinces.get(province).get(provinces.get(province).indexOf(store)).addCrop(name, price);
        }
        else {
            provinces.get(province).get(provinces.get(province).indexOf(store)).setAuthorization(false);
        }

    }

    /**
     * this method search to all forbidden crops and say use that is this crop forbidden or not
     * @param name name of crop to search
     * @param forbiddenCrops set of forbidden crops
     * @return true for legal crop and false for illegal crop
     */
    private boolean authorization(String name, HashSet<String> forbiddenCrops) {
        if (forbiddenCrops.contains(name)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * show details of all provinces and their stores and ...
     */
    public void showProvinces(){
        for(String province: provinces.keySet()){
            System.out.print(province + " = " + provinces.get(province) + "\n");
        }
    }

}
