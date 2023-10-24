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

        String endingCity[] = { "Göteborg" };

        for (int i = 0; i < startingCity.length; i++) {

            args[0] = startingCity[i];
            String from = args[0];
            args[1] = endingCity[i];
            String to = args[1];

            City fromCity = map.lookup(from);

            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            priorityQueue.bubble(new Path(fromCity, null, 0, 0));
            findPaths(fromCity, toCity);
            long time = (System.nanoTime() - t0) / 1_000_000;
            for (Path p : done) {
                if (p != null) {
                    if (p.getPrevious() != null) {
                        System.out.print("Path From: " + p.getPrevious().getName());
                    } else {
                        System.out.print("Path From Start: " + fromCity.getName());
                    }

                    if (p.getDestination() != null) {
                        System.out.print(" |To: " + p.getDestination().getName());
                    } else {
                        System.out.print(" |To Start: " + fromCity.getName());
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
        System.out.println(currentPath.getDestination());

        if (from.connections != null) {
            for (int i = 0; i < from.connections.length; i++) {

                Connection conn = from.connections[i];

                if (conn != null) {
                    if (conn.destinationCity != null) {

                        City connectedCity = conn.getDestination();

                        System.out.println(connectedCity.getName());

                        if (connectedCity.getId() != null) {
                            if (done[currentPath.getIndex()].getDist() > conn.getTime()) {
                                done[currentPath.getIndex()] = new Path(connectedCity, from, conn.getTime(),
                                        connectedCity.getId());
                            }

                        } else {
                            id++;
                            connectedCity.setId(id);
                            done[id] = new Path(null, from, conn.getTime(), id);
                            priorityQueue.bubble(new Path(connectedCity, from, conn.getTime(), id));

                        }
                        findPaths(connectedCity, to);

                    }

                }
            }
        }

    }

    public static void findPaths2(City from, City to) {

        if (from == to) {
            return;
        }

        if (from.connections != null) {
            for (int i = 0; i < from.connections.length; i++) {

                Connection conn = from.connections[i];

                if (conn != null) {
                    City connectedCity = conn.getDestination();
                    System.out.println(connectedCity.getName());

                    if (connectedCity.getId() == null) {
                        id++;
                        connectedCity.setId(id);
                        done[id] = new Path(connectedCity, from, conn.getTime(), id);findPaths2(connectedCity,to);

                    } else {
                        if (done[connectedCity.getId()].getDist() > conn.getTime()) {
                            done[connectedCity.getId()] = new Path(connectedCity, from, conn.getTime(),connectedCity.getId());
                        }
                    }
                    
                }
            }
        }

    }

}
