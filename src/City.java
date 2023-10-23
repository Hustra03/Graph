public class City {
    String cityName;
    Connection connections[];

    private final int mod = 541;

    City(String name) {
        this.cityName = name;
        this.connections = new Connection[541];
    }

    private Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public void connect(City toConnect, int time) {
        Connection newConnection = new Connection(time, toConnect);
        int hash = hash(toConnect.getName());
        while (true) {
            if (connections[hash] == null) {
                connections[hash] = newConnection;
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
}
