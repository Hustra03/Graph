public class Dijkstra {

    static Path done[];
    static ArrayHeap priorityQueue;

    public Dijkstra(int Length) {
        this.done = new Path[Length];
        this.priorityQueue = new ArrayHeap(Length);
        String fileTemp = "src\\europe.csv";
        String fileTemp2 = "src\\trains.csv";
    }

    public static void main(String[] args) {
        Map map = new Map("src\\trains.csv");
        Dijkstra Dijkstra = new Dijkstra(202);

        String startingCity[] = { "Stockholm" };
        String endingCity[] = { "Sundsvall" };

        for (int i = 0; i < startingCity.length; i++) {

            String from = startingCity[i];
            String to = endingCity[i];

            City fromCity = map.lookup(from);

            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            findPaths(fromCity, toCity);
            long time = (System.nanoTime() - t0) / 1_000_000;

            for (Path p : done) {
                if (p != null) {
                    System.out.print("Path From: " + p.getPrevious().getName());

                    System.out.print(" |To: " + p.getDestination().getName());

                    System.out.print(" |Id: " + p.getDestination().getId());

                    System.out.println(" |Distance: " + p.getDist());
                }
            }
            System.out.println(time + " ms");
        }
    }

    public static void findPaths(City from, City to) {

        int id = 0;
        from.setId(id);
        done[id] = new Path(from, from, 0);
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

                    int previousId= p.getPrevious().getId();

                    if (connectedCity.getId() == null) {
                        id++;
                        connectedCity.setId(id);
                        done[id] = new Path(connectedCity, p.getDestination(), con.getTime() + done[previousId].getDist());
                        priorityQueue.bubble(done[id]);
                    } else {
                        int currentId = connectedCity.getId();
                        if (done[currentId].getDist() > (con.getTime() +done[previousId].getDist())) {
                            done[currentId] = new Path(connectedCity, p.getDestination(), con.getTime() + done[previousId].getDist());
                            //priorityQueue.updateElement(done[currentId]);
                        }
                    }
                }
            }
        }

    }

}
