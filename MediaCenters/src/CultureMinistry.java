import java.util.HashSet;

public class CultureMinistry {

    private static HashSet<String> forbiddenCrops = new HashSet<String>();

    public static void main(String[] args) {
        Country country = new Country();

        //forbidden crops
        forbiddenCrops.add("forbidden1");
        forbiddenCrops.add("forbidden2");
        forbiddenCrops.add("forbidden3");
        forbiddenCrops.add("forbidden4");
        forbiddenCrops.add("forbidden5");
        forbiddenCrops.add("forbidden6");


        //adding provinces to country
        country.addProvince("province1");
        country.addProvince("province2");
        country.addProvince("province3");
        country.addProvince("province4");
        country.addProvince("province5");
        country.addProvince("province6");
        country.addProvince("province7");
        country.addProvince("province8");
        country.addProvince("province9");
        country.addProvince("province10");

        //making stores
        Store store1 = new Store("name1","GAME",true);
        Store store2 = new Store("name2","MOVIE",true);
        Store store3 = new Store("name3","MUSIC",false);
        Store store4 = new Store("name4","GAME",true);
        Store store5 = new Store("name5","GAME",true);
        Store store6 = new Store("name6","MUSIC",false);
        Store store7 = new Store("name7","MOVIE",true);
        Store store8 = new Store("name8","GAME",true);
        Store store9 = new Store("name9","MUSIC",true);
        Store store10 = new Store("name10","GAME",true);
        Store store11 = new Store("name11","MOVIE",false);
        Store store12 = new Store("name12","MUSIC",false);
        Store store13 = new Store("name13","MUSIC",true);
        Store store14 = new Store("name14","GAME",true);
        Store store15 = new Store("name15","MOVIE",true);
        Store store16 = new Store("name16","MUSIC",true);
        Store store17 = new Store("name17","GAME",false);
        Store store18 = new Store("name18","MOVIE",true);
        Store store19 = new Store("name19","MUSIC",true);
        Store store20 = new Store("name20","GAME",true);
        Store store21 = new Store("name21","MUSIC",false);
        Store store22 = new Store("name22","MUSIC",true);
        Store store23 = new Store("name23","MOVIE",true);
        Store store24 = new Store("name24","GAME",true);
        Store store25 = new Store("name25","MOVIE",true);
        Store store26 = new Store("name26","MUSIC",false);
        Store store27= new Store("name27","MUSIC",true);
        Store store28 = new Store("name28","MOVIE",true);
        Store store29 = new Store("name29","GAME",false);
        Store store30 = new Store("name30","MOVIE",true);

        //adding stores that make
        country.addStore("province1", store1);
        country.addStore("province1", store2);
        country.addStore("province1", store3);
        country.addStore("province2", store4);
        country.addStore("province2", store5);
        country.addStore("province2", store6);
        country.addStore("province3", store7);
        country.addStore("province3", store8);
        country.addStore("province3", store9);
        country.addStore("province4", store10);
        country.addStore("province4", store11);
        country.addStore("province4", store12);
        country.addStore("province5", store13);
        country.addStore("province5", store14);
        country.addStore("province5", store15);
        country.addStore("province6", store16);
        country.addStore("province6", store17);
        country.addStore("province6", store18);
        country.addStore("province7", store19);
        country.addStore("province7", store20);
        country.addStore("province7", store21);
        country.addStore("province8", store22);
        country.addStore("province8", store23);
        country.addStore("province8", store24);
        country.addStore("province9", store25);
        country.addStore("province9", store26);
        country.addStore("province9", store27);
        country.addStore("province10", store28);
        country.addStore("province10", store29);
        country.addStore("province10", store30);

        //adding crops to stores
        country.addCrop("province1",store1, forbiddenCrops,1000, "crop1");
        country.addCrop("province1",store2, forbiddenCrops,2000, "forbidden1");
        country.addCrop("province1",store3, forbiddenCrops,2000, "crop3");
        country.addCrop("province2",store4, forbiddenCrops,1000, "crop4");
        country.addCrop("province2",store5, forbiddenCrops,5000, "forbidden2");
        country.addCrop("province2",store6, forbiddenCrops,60000, "crop6");
        country.addCrop("province3",store7, forbiddenCrops,1000, "crop7");
        country.addCrop("province3",store8, forbiddenCrops,1000, "crop8");
        country.addCrop("province3",store9, forbiddenCrops,3000, "crop9");
        country.addCrop("province4",store10, forbiddenCrops,4000, "crop10");
        country.addCrop("province4",store11, forbiddenCrops,5000, "crop11");
        country.addCrop("province4",store12, forbiddenCrops,1000, "crop12");
        country.addCrop("province5",store13, forbiddenCrops,1000, "crop13");
        country.addCrop("province5",store14, forbiddenCrops,12000, "crop14");
        country.addCrop("province5",store15, forbiddenCrops,13000, "crop15");
        country.addCrop("province6",store16, forbiddenCrops,1000, "forbidden3");
        country.addCrop("province6",store17, forbiddenCrops,5000, "crop17");
        country.addCrop("province6",store18, forbiddenCrops,12000, "crop18");
        country.addCrop("province7",store19, forbiddenCrops,1000, "crop19");
        country.addCrop("province7",store20, forbiddenCrops,16000, "crop20");
        country.addCrop("province7",store21, forbiddenCrops,11000, "crop21");
        country.addCrop("province8",store22, forbiddenCrops,6000, "crop22");
        country.addCrop("province8",store23, forbiddenCrops,1000, "crop23");
        country.addCrop("province8",store24, forbiddenCrops,6000, "crop24");
        country.addCrop("province9",store25, forbiddenCrops,1000, "crop25");
        country.addCrop("province9",store26, forbiddenCrops,1000, "forbidden4");
        country.addCrop("province9",store27, forbiddenCrops,17000, "forbidden5");
        country.addCrop("province10",store28, forbiddenCrops,2000, "crop28");
        country.addCrop("province10",store29, forbiddenCrops,1000, "crop29");
        country.addCrop("province10",store30, forbiddenCrops,45000, "crop30");

        //printing output
        System.out.println("Stores without authorization can not have any sale and their all price is zero(0).\n");
        country.showProvinces();

    }

}
