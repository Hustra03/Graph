public class Dijkstra {

    static Path done[];
    static TreeHeap priorityQueue;
    static Integer id;

    public Dijkstra() {
        this.done = new Path[100];
        this.priorityQueue = new TreeHeap();
        this.id=0;
    }

    public static void main(String[] args) {
        Map map = new Map("src\\trains.csv");
        Dijkstra Dijkstra = new Dijkstra();
        args = new String[3];
        String startingCity[] = { "Malmö" };

        String endingCity[] = { "Göteborg", "Stockholm", "Stockholm", "Umeå", "Sundsvall", "Umeå", "Umeå", "Göteborg" };

        for (int i = 0; i < startingCity.length; i++) {

            args[0] = startingCity[i];
            String from = args[0];
            args[1] = startingCity[i];
            String to = args[0];

            long t0 = System.nanoTime();
            City fromCity = map.lookup(from);

            City toCity = map.lookup(to);

            priorityQueue.enqueue(new Path(fromCity, null, 0, 0));
            findPaths(fromCity, toCity);
            long time = (System.nanoTime() - t0) / 1_000_000;
            for (Path p : done) {
                if (p != null) {
                    System.out.println("Path");
                }
            }
            System.out.println(time + " ms");
        }
    }

    public static void findPaths(City from, City to) {

        Path currentPath = priorityQueue.dequeue();

        if (currentPath.getDestination()==to) {
            return;
        }

        done[currentPath.getIndex()] = currentPath;

        if (from.connections != null) {
            for (int i = 0; i < from.connections.length; i++) {

                Connection conn = from.connections[i];
                if (conn != null) {
                    if (conn.getDestination().getId()!=null) {
                        if (done[currentPath.getIndex()].getDist()<conn.getTime()) {
                            done[currentPath.getIndex()]=new Path(conn.getDestination(), from, conn.getTime(), conn.getDestination().getId());
                        }
                    }
                    else
                    {
                        conn.getDestination().setId(id);
                        priorityQueue.enqueue(new Path(from, to,conn.getTime() , id));
                        id++;
                    }

                }

            }

        }

    }

}
