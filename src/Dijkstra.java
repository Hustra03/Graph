public class Dijkstra {

    static Path done[];
    static ArrayHeap priorityQueue;

    public Dijkstra(int Length) {
        this.done = new Path[Length];
        this.priorityQueue = new ArrayHeap(Length);
    }

    public static void main(String[] args) {
        Map map = new Map("src\\europe.csv");
        Dijkstra Dijkstra = new Dijkstra(300);
        args = new String[3];
        String startingCity[] = { "Barcelona" };

        String endingCity[] = { "Umeå" };

        for (int i = 0; i < startingCity.length; i++) {

            args[0] = startingCity[i];
            String from = args[0];
            args[1] = endingCity[i];
            String to = args[1];

            City fromCity = map.lookup(from);

            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            findPaths(fromCity, toCity);
            long time = (System.nanoTime() - t0) / 1_000_000;

            for (Path p : done) {
                if (p != null) {
                    System.out.print("Path From: " + from);

                    if (p.getDestination() != null) {
                        System.out.print(" |To: " + p.getDestination().getName());
                    } else {
                        System.out.print(" |To Start: " + fromCity.getName());
                    }
                    System.out.println(" |Distance: " + p.getDist());
                }
            }
            System.out.println(time + " ms");
        }
    }

    public static void findPathsWrong(City from, City to, int id) {
        Path currentPath = priorityQueue.sink();

        if (currentPath.getDestination() == to) {
            return;
        }

        done[currentPath.getDestination().getId()] = currentPath;
        System.out.println(currentPath.getDestination());

        if (from.connections != null) {
            for (int i = 0; i < from.connections.length; i++) {

                Connection conn = from.connections[i];

                if (conn != null) {
                    if (conn.destinationCity != null) {

                        City connectedCity = conn.getDestination();

                        System.out.println(connectedCity.getName());

                        if (connectedCity.getId() != null) {
                            if (done[connectedCity.getId()].getDist() > conn.getTime()) {
                                done[connectedCity.getId()] = new Path(connectedCity, from, conn.getTime());
                            }

                        } else {
                            id++;
                            connectedCity.setId(id);
                            done[id] = new Path(null, from, conn.getTime());
                            priorityQueue.bubble(new Path(connectedCity, from, conn.getTime()));

                        }
                        findPathsWrong(connectedCity, to, id);

                    }

                }
            }
        }

    }

    public static void findPathsWrong2(City from, City to, int id) {

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
                        done[id] = new Path(connectedCity, from, conn.getTime());
                        done[id].setDist(done[id].getDist() + done[id - 1].getDist());
                        findPathsWrong2(connectedCity, to, id);

                    } else {
                        if (done[connectedCity.getId()].getDist() > conn.getTime()) {
                            done[connectedCity.getId()] = new Path(connectedCity, from, conn.getTime());
                        }
                    }

                }
            }
        }
    }

    public static void findPaths(City from, City to) {

        int id = 0;
        from.setId(id);
        done[id] = new Path(from, null, 0);
        priorityQueue.bubble(done[id]);

        while (priorityQueue.currentMaxIndex != 0) {
            Path p = priorityQueue.sink();
            if (p.getDestination() == to) {
                return;
            }
            System.out.println(p.getDestination());

            for (Connection con : p.getDestination().getConnections()) {
                if (con != null) {

                    City connectedCity = con.getDestination();

                    System.out.println(connectedCity.getName());

                    if (connectedCity.getId() == null) {
                        id++;
                        connectedCity.setId(id);
                        done[id] = new Path(connectedCity, p.getDestination(),
                                con.getTime() + done[p.getDestination().getId()].getDist());
                        priorityQueue.bubble(done[id]);
                    } else {
                        int currentId = connectedCity.getId();
                        if (done[currentId].getDist() > (con.getTime() + done[p.getDestination().getId()].getDist())) {
                            done[currentId] = new Path(connectedCity, p.getDestination(),
                                    con.getTime() + done[p.getDestination().getId()].getDist());
                        }
                    }
                }
            }
        }

    }

}
