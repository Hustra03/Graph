public class City {
    String cityName;
    Integer id;
    Connection connections[];

    private final int mod = 541;

    City(String name, Integer id) {
        this.cityName = name;
        this.connections = new Connection[541];
        this.id=id;
    }

    private Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public void connect(City toConnect, int time) {
        int hash = hash(toConnect.getName());
        while (true) {
            if (connections[hash] == null) {
                connections[hash] =  new Connection(time, toConnect);;
                break;
            }
            hash += 1;
        }
        
    }

    public String getName() {
        return cityName;
    }

    public Connection[] getConnections() {
        return connections;
    }

    public void cityPrint()
    {
        System.out.println(this.cityName + " id " + this.id);
        for (Connection connection : connections) {
            if (connection != null) {
                System.out.println("\t Connected: " + connection.destinationCity.getName() + "| Time: "
                        + connection.getTime());
            }
        }
    }
}
