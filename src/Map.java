import java.io.BufferedReader;
import java.io.FileReader;

public class Map {

    City cities[];
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
                City first = lookup(firstCity);

                City second = lookup(secondCity);
                int time = Integer.valueOf(row[2]);

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
            if (cities[hash] != null) {
                if (cities[hash].getName().equalsIgnoreCase(cityName)) {
                    return cities[hash];
                } else {
                    hash = (hash + 1) % mod;
                }
            } else {
                cities[hash] = new City(cityName);
                return cities[hash];
            }

        }
    }

    public void mapPrint() {
        for (int j = 0; j < cities.length; j++) {

            City city = cities[j];
            if (city != null) {
                city.cityPrint();
            }
        }

    }
}
