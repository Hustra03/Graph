import java.util.PriorityQueue;

public class Dijkstra {

    static Path done[];
    static PriorityQueue<Path> priorityQueue;
    Map map;

    public Dijkstra(Map map) {
        this.map = map;
        this.done = new Path[map.id + 1];
        this.priorityQueue = new PriorityQueue();
        String fileTemp = "src\\europe.csv";
        String fileTemp2 = "src\\trains.csv";
    }

    public static void main(String[] args) {

        String file = "src\\trains.csv";

        Map map = new Map(file);

        Dijkstra d = new Dijkstra(map);

        String startingCity[] = { "Sundsvall" };

        String endingCity[] = { "Umeå" };

        for (int i = 0; i < endingCity.length; i++) {

            String from = startingCity[0];

            City fromCity = map.lookup(from);

            String to = endingCity[i];
            City toCity = map.lookup(to);

            long t0 = System.nanoTime();
            findPaths(fromCity, toCity);
            long time = (System.nanoTime() - t0);

            System.out.println("Shortest Path From " + from);

            int size=0;
            for (Path p : done) {
                if (p != null) {

                    if (p.getDestination() == toCity) {
                        // System.out.print("From: " + p.getPrevious().getName() + " | ");

                        System.out.print("To: " + p.getDestination().getName());

                        System.out.print(" |Id: " + p.getDestination().getId());

                        System.out.println(" |Distance: " + p.getDist());
                    }
                    size+=1;

                }
            }

            
            System.out.print("Size: " + size +"| ");
            time /= 1_000;
            System.out.println(time + " ms");

            // System.out.println(time + " ns");
            // Dijstra is a Ordo (n * log(n)) search function
        }
    }

    public static void findPaths(City from, City to) {

        priorityQueue.add(new Path(from));

        while (!priorityQueue.isEmpty()) {

            Path p = priorityQueue.remove();

            City c = p.getDestination();

            if (done[c.getId()] == null) {
                done[c.getId()] = p;
                if (c == to) {
                    return;
                }
            }

            Integer prevDist = p.getDist();

            for (Connection con : c.getConnections()) {
                if (con != null) {
                    City toNow = con.getDestination();
                    if (done[toNow.getId()] == null) {
                        priorityQueue.add(new Path(toNow, c, prevDist + con.getTime()));
                    }
                }
            }
        }

    }

    /*
     * public static void findPathsIntQueue(City from, City to) {
     * 
     * done = new Path[Length];
     * priorityQueue2 = new ArrayHeapInt(Length);
     * 
     * int id = 0;
     * from.setId(id);
     * done[id] = new Path(from, from, 0);
     * priorityQueue2.bubble(id);
     * 
     * while (priorityQueue2.currentMaxIndex > 0) {
     * Path p = done[priorityQueue2.sink()];
     * 
     * if (p.getDestination() == to) {
     * return;
     * }
     * 
     * // System.out.println(p.getDestination().getName());
     * 
     * for (Connection con : p.getDestination().getConnections()) {
     * if (con != null) {
     * 
     * City connectedCity = con.getDestination();
     * 
     * // System.out.println(connectedCity.getName());
     * 
     * Integer previousId = p.getDestination().getId();
     * 
     * if (connectedCity.getId() == null) {
     * id++;
     * connectedCity.setId(id);
     * done[id] = new Path(connectedCity, p.getDestination(),
     * con.getTime() + done[previousId].getDist());
     * priorityQueue2.bubble(connectedCity.getId());
     * 
     * } else {
     * Integer currentId = connectedCity.getId();
     * 
     * if (done[currentId].getDist() - con.getTime() > done[previousId].getDist()) {
     * 
     * done[currentId] = new Path(connectedCity, p.getDestination(),
     * con.getTime() + done[previousId].getDist());
     * priorityQueue2.bubble(connectedCity.getId());
     * 
     * } // System.out.println(97);}
     * 
     * // Ask if this is an okey implemenetation or not, appears to work but may be
     * // slower than the intended implementation
     * // System.out.println(done[connectedCity.getId()].getDestination().getId());
     * }
     * }
     * }
     * 
     * }
     * }
     */
    // Annan Algorithm än den han beskrev i uppgiftsbeskrivningen, lägg till en
    // boolean värde för varje stad som säger om kortast väg hittats, börja med att
    // markera start som hittad, för alla andra skapa vägar genom tidigare väg +
    // koppling, lägg till path mellan varje sann och dess osanna kopplinar till
    // kön, ta bort
    // den högst upp, sätt den till true
}
