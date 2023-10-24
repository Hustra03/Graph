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

        
        String endingCity[] = { "Eskilstuna" };

        for (int i = 0; i < startingCity.length; i++) {

            String from = startingCity[i];

            City fromCity = map.lookup(from);

            
            String to = endingCity[i];
            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            findPaths(fromCity,toCity);
            long time = (System.nanoTime() - t0) / 1_000_000;

            for (Path p : done) {
                if (p != null) {
                    System.out.print(from);

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
         
            System.out.println(p.getDestination().getName());

            for (Connection con : p.getDestination().getConnections()) {
                if (con != null) {

                    City connectedCity = con.getDestination();

                    System.out.println(connectedCity.getName());

                    Integer previousId = p.getPrevious().getId();

                    if (connectedCity.getId() == null) {
                        id++;
                        connectedCity.setId(id);
                        done[id] = new Path(connectedCity, p.getDestination(), con.getTime() + done[previousId].getDist());
                        priorityQueue.bubble(done[id]);
                    } else {
                        Integer currentId = connectedCity.getId();
                        if ((done[currentId].getDist() -done[previousId].getDist()) > con.getTime() ){
                            done[currentId] = new Path(connectedCity, p.getDestination(), con.getTime() + done[previousId].getDist());
                            // priorityQueue.updateElement(done[currentId]);
                        }
                    }
                }
            }
        }

    }

}
