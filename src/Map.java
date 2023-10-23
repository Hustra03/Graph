import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

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
                int hash1 = hash(firstCity);
                String secondCity = row[1];
                int hash2 = hash(secondCity);
                int time = Integer.valueOf(row[2]);

                while (true) {
                    if (cities[hash1] == null) {
                        cities[hash1] = new City(firstCity);
                        break;
                    }
                    if (cities[hash1].getName() == firstCity) {
                        break;
                    }
                    hash1 += 1;
                }

                while (true) {
                    if (cities[hash2] == null) {
                        cities[hash2] = new City(secondCity);
                        break;
                    }
                    if (cities[hash2].getName() == secondCity) {
                        break;
                    }
                    hash2 += 1;
                }

                cities[hash1].connect(cities[hash2], time);
                cities[hash2].connect(cities[hash1], time);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt" + e);
        }
    }

    public City lookup(String cityName) {
        int hash = hash(cityName);
        while (true) {
            if (cities[hash] == null) {
                return null;
            }
            if (cities[hash].getName() == cityName) {
                return cities[hash];
            }
            hash += 1;
        }
    }

    public void mapPrint() {
        for (City city : cities) {
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
