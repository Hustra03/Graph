public class City {
    String cityName;
    Integer id;
    Connection connections[];
    Boolean shortestPathFound;

    private final int mod = 541;

    City(String name,Integer id) {
        this.cityName = name;
        this.connections = new Connection[mod];
        this.id = id;
        this.shortestPathFound = false;
    }

    public void connect(City toConnect, int time) {
        int hash = hash(toConnect.getName());
        while (true) {
            if (connections[hash] == null) {
                connections[hash] = new Connection(time, toConnect);
                ;
                break;
            }
            hash += 1;
        }

    }

    private Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public String getName() {
        return cityName;
    }

    public Connection[] getConnections() {
        return connections;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer newId) {
        this.id = newId;
    }

    public Boolean getFound() {
        return this.shortestPathFound;
    }

    public void setFoundBoolean(Boolean newFound) {
        this.shortestPathFound = newFound;
    }

    public void cityPrint() {
        System.out.println(this.cityName + " id " + this.id);
        for (Connection connection : connections) {
            if (connection != null) {
                System.out.println("\t Connected: " + connection.destinationCity.getName() + "| Time: "
                        + connection.getTime());
            }
        }
    }
}
