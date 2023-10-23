public class App {
    public static void main(String[] args) throws Exception {
        //mapTest();
        cityTest();
    }

    public static void mapTest()
    {
        Map map = new Map("src\\trains.csv");
        map.mapPrint();
        map.lookup("Stockholm").cityPrint();
    }

    public static void cityTest()
    {
        City city1= new City("Stockholm");
        City city2= new City("Malmö");
        city1.cityPrint();
        city1.connect(city2, 29);
        city1.cityPrint();
    }
}
