public class Dijkstra {

    static Path done[];
    static ArrayHeap priorityQueue;
    static Integer id;

    public Dijkstra(int Length) {
        this.done = new Path[Length];
        this.priorityQueue = new ArrayHeap(Length);
        this.id = 0;
    }

    public static void main(String[] args) {
        Map map = new Map("src\\trains.csv");
        Dijkstra Dijkstra = new Dijkstra(300);
        args = new String[3];
        String startingCity[] = { "Malmö" };

        String endingCity[] = { "Göteborg", "Stockholm", "Stockholm", "Umeå", "Sundsvall", "Umeå", "Umeå", "Göteborg" };

        for (int i = 0; i < startingCity.length; i++) {

            args[0] = startingCity[i];
            String from = args[0];
            args[1] = endingCity[i];
            String to = args[0];

            City fromCity = map.lookup(from);

            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            priorityQueue.bubble(new Path(fromCity, null, 0, 0));
            findPaths(fromCity, toCity);
            long time = (System.nanoTime() - t0) / 1_000_000;
            for (Path p : done) {
                if (p != null) {
                    System.out.print("Path From: " + fromCity.getName());
                    if (p.getDestination() != null) {
                        System.out.print(" |To: " + p.getDestination().getName());
                    }
                    System.out.print(" |Distance: " + p.getDist());
                    
                    System.out.println(" |Id: " + p.getIndex());
                }
            }
            System.out.println(time + " ms");
        }
    }

    public static void findPaths(City from, City to) {

        Path currentPath = priorityQueue.sink();

        if (currentPath.getDestination() == to) {
            return;
        }

        done[currentPath.getIndex()] = currentPath;

        if (from.connections != null) {
            for (int i = 0; i < from.connections.length; i++) {

                Connection conn = from.connections[i];

                if (conn != null) {
                    if (conn.destinationCity != null) {

                        City connectedCity = conn.getDestination();

                        if (connectedCity.getId() != null) {
                            if (done[currentPath.getIndex()].getDist() > conn.getTime()) {
                               // done[currentPath.getIndex()] = new Path(destinationCity, from, conn.getTime(),destinationCity.getId());
                            }

                        } else {
                            id++;
                            connectedCity.setId(id);
                            done[id] = new Path(connectedCity, from, conn.getTime(), id);
                            priorityQueue.bubble(done[id]);

                        }
                        findPaths(connectedCity, to);
                    }

                }

            }

        }

    }

}
