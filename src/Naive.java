public class Naive {
    public static void main(String[] args) {
        Map map = new Map("src\\trains.csv");
        args = new String[3];
        args[2] = "450";
        Integer max = Integer.valueOf(args[2]);
        String startingCity[] = { "Malmö", "Malmö", "Göteborg", "Göteborg", "Stockholm", "Stockholm", "Sundsvall",
                "Umeå" };
        String endingCity[] = { "Göteborg", "Stockholm", "Stockholm", "Umeå", "Sundsvall", "Umeå", "Umeå", "Göteborg" };

        for (int i = 0; i < endingCity.length; i++) {

            args[0] = startingCity[i];
            args[1] = endingCity[i];
            String from = args[0];
            String to = args[1];

            long t0 = System.nanoTime();
            Integer dist = shortest(map.lookup(from), map.lookup(to), max);
            long time = (System.nanoTime() - t0) / 1_000_000;
            System.out.println("shortest " + args[0] + " to " + args[1] + " : " + dist + " min (" + time + " ms)");
        }
    }

    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0)
            return -1;

        if (from == to)
            return 0;

        Integer shrt = Integer.MAX_VALUE;

        for (int i = 0; i < from.connections.length; i++) {

            if (from.connections != null) {
                Connection conn = from.connections[i];
                if (conn != null) {

                        int timeNext = shortest(conn.getDestination(), to, max - conn.getTime());
                        if (timeNext != -1 && (shrt- conn.getTime()) > timeNext) {
                            shrt = timeNext + conn.getTime();
                        }
                    
                }

            }
        }
        return shrt;
    }
}
