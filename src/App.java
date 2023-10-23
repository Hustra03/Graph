public class App {
    public static void main(String[] args) throws Exception {
        mapTest();
    }

    public static void mapTest()
    {
        Map map = new Map("src\\trains.csv");
        map.mapPrint();
    }
}
