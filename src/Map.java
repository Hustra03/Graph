import java.io.BufferedReader;
import java.io.FileReader;
public class Map {

    private City cities[];
    private final int mod = 541;

    private Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public Map(String file) {
        cities = new City[mod];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String firstCity = row[0];
                String secondCity = row[1];
                int time = Integer.valueOf(row[2]);

                City first = lookup(firstCity);

                City second = lookup(secondCity);

                first.connect(second, time);
                second.connect(first, time);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt" + e);
        }
    }

    public City lookup(String cityName) {
        int hash = hash(cityName);
        while (true) {
            if (cities[hash] == null) {
                cities[hash] = new City(cityName);
                break;
            } else {
                if (cities[hash].getName() == cityName) {
                    break;
                }
            }

            hash += 1;
        }
        return cities[hash];
    }

    public void mapPrint() {
        for (int j = 0; j < cities.length; j++) {

            City city = cities[j];
            if (city != null) {
                System.out.println("Name: " + city.getName());
                Connection connections[] = city.getConnections();
                for (int i = 0; i < connections.length; i++) {

                    if (connections[i] != null) {
                        System.out.println("\t Connected: " + connections[i].destinationCity.getName() + "| Time: "
                                + connections[i].getTime());
                    }
                }
            }
        }

    }
}
