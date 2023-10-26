public class Dijkstra {

    static Path done[];
    static ArrayHeap priorityQueue;
    static ArrayHeapInt priorityQueue2;
    static final int Length = 300;

    public Dijkstra() {
        this.done = new Path[Length];
        this.priorityQueue = new ArrayHeap(Length);
        String fileTemp = "src\\europe.csv";
        String fileTemp2 = "src\\trains.csv";
    }

    public static void main(String[] args) {

        String file = "src\\trains.csv";

        Map map = new Map(file);

        String startingCity[] = { "Umeå" };

        String endingCity[] = { "Göteborg" };

        for (int i = 0; i < endingCity.length; i++) {

            String from = startingCity[0];

            City fromCity = map.lookup(from);

            String to = endingCity[i];
            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            findPathsIntQueue(fromCity, toCity);
            long time = (System.nanoTime() - t0);

            System.out.println("Shortest Path From " + from);

            for (Path p : done) {
                if (p != null) {

                    //System.out.print("From: " + p.getPrevious().getName() + " | ");

                    System.out.print("To: " + p.getDestination().getName());

                    System.out.print(" |Id: " + p.getDestination().getId());

                    System.out.println(" |Distance: " + p.getDist());
                }
            }

            time /= 1_000_000;
            System.out.println(time + " ms");

            // System.out.println(time + " ns");
            map = new Map(file);
        }
    }

    public static void findPaths(City from, City to) {

        done = new Path[Length];
        priorityQueue = new ArrayHeap(Length);

        for (Path path : done) {
            if (path != null) {
                path.getDestination().setId(null);
            }
        }

        int id = 0;
        from.setId(id);
        done[id] = new Path(from, from, 0);
        priorityQueue.bubble(done[id]);

        while (priorityQueue.currentMaxIndex > 0) {
            Path p = priorityQueue.sink();

            if (p.getDestination() == to) {
                return;
            }

            System.out.println(p.getDestination().getName());

            for (Connection con : p.getDestination().getConnections()) {
                if (con != null) {

                    City connectedCity = con.getDestination();

                    System.out.println(connectedCity.getName());

                    Integer previousId = p.getDestination().getId();

                    if (connectedCity.getId() == null) {
                        id++;
                        connectedCity.setId(id);
                        done[id] = new Path(connectedCity, p.getDestination(),
                                con.getTime() + done[previousId].getDist());
                        priorityQueue.bubble(done[connectedCity.getId()]);

                    } else {
                        Integer currentId = connectedCity.getId();
                        if (done[currentId].getDist() > (con.getTime() + done[previousId].getDist())) {

                            done[currentId] = new Path(connectedCity, p.getDestination(),
                                    con.getTime() + done[previousId].getDist());
                        }
                        System.out.println(97);
                    }
                    System.out.println(done[connectedCity.getId()].getDestination().getId());
                }
            }
        }

    }

    public static void findPathsIntQueue(City from, City to) {

        done = new Path[Length];
        priorityQueue2 = new ArrayHeapInt(Length);

        int id = 0;
        from.setId(id);
        done[id] = new Path(from, from, 0);
        priorityQueue2.bubble(id);

        while (priorityQueue2.currentMaxIndex > 0) {
            Path p = done[priorityQueue2.sink()];

            if (p.getPrevious() == to) {
                System.out.println("Size : " + (id + 1));
                return;
            }

            // System.out.println(p.getDestination().getName());

            for (Connection con : p.getDestination().getConnections()) {
                if (con != null) {

                    City connectedCity = con.getDestination();

                    // System.out.println(connectedCity.getName());

                    Integer previousId = p.getDestination().getId();

                    if (connectedCity.getId() == null) {
                        id++;
                        connectedCity.setId(id);
                        done[id] = new Path(connectedCity, p.getDestination(), con.getTime() + done[previousId].getDist());
                        priorityQueue2.bubble(connectedCity.getId());

                    } else {
                        Integer currentId = connectedCity.getId();
                        if (done[currentId].getDist() - con.getTime() > done[previousId].getDist()) {

                            done[currentId] = new Path(connectedCity, p.getDestination(), con.getTime() + done[previousId].getDist());
                            priorityQueue2.bubble(connectedCity.getId()); 
                        } // System.out.println(97);
                    }
                    // Ask if this is an okey implemenetation or not, appears to work but may be slower than the intended implementation
                    // System.out.println(done[connectedCity.getId()].getDestination().getId());
                }
            }
        }

    }

}
