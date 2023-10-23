public class App {
    public static void main(String[] args) throws Exception {
        // cityTest();
        mapTest();

    }

    public static void mapTest() {
        Map map = new Map("src\\trains.csv");
        map.mapPrint();
    }

    public static void cityTest() {
        City city1 = new City("Stockholm");
        City city2 = new City("Malmö");
        city1.cityPrint();
        city1.connect(city2, 29);
        city2.connect(city1, 29);
        city1.cityPrint();
        city2.cityPrint();
    }

    private static Integer hash(String name) {

        int mod = 541;
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }
}
