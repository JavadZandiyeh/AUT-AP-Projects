import java.util.HashMap;

/**
 * this class is for stores in country that is in a province of this country
 * stores have name and authorization and a map of crops that each crop has
 * a key that is its name and a value that is its price;
 * @version 0.0
 * @author Mohammad Javad Zandiyeh
 */
public class Store{
   //fields of store
    private String name;
    private String type;
    private boolean authorization;
    private HashMap<String, Integer> crops = new HashMap<String, Integer>();
    private int allPrice;

    /**
     * constructor of Store
     * @param name name of store
     * @param type can be MUSIC and MOVIE and GAME
     * @param authorization is the deal between stores and Culture ministry
     *                      that can be dissolution against sale of forbidden
     *                      crops.
     */
    public Store(String name, String type, boolean authorization){
        allPrice = 0;
        this.name = name;
        this.type = type;
        this.authorization = authorization;
    }

    /**
     * can be true for legal sale and false for illegal sales
     * @param authorization is a boolean that can be true or false
     */
    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    /**
     * @return boolean  true for legal and false for illegal sales
     */
    public boolean getAuthorization(){
        return authorization;
    }

    /**
     * we can add a crop with its name and price to our store
     * @param name name of crop
     * @param price price of crop
     */
    public void addCrop(String name, Integer price){
        crops.put(name, price);
        allPrice += price;
    }

    /**
     * make an string that contains detail of each store and its total sale
     * @return String detail of store
     */
    public String toString() {
        return name + "{" + "allPrice=" + allPrice + '}';
    }
}
