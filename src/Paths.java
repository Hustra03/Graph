public class Paths {
    static City[] path;
    static int sp;
    static Integer maximumLength;

    public static void main(String[] args) {
        Map map = new Map("src\\trains.csv");
        Paths path = new Paths();
        args = new String[3];
        String startingCity[] = { "Malmö", "Malmö", "Göteborg", "Göteborg", "Stockholm", "Stockholm", "Sundsvall",
                "Umeå","Stockholm" };
        String endingCity[] = { "Göteborg", "Stockholm", "Stockholm", "Umeå", "Sundsvall", "Umeå", "Umeå", "Göteborg","Kiruna" };

        for (int i = 0; i < endingCity.length; i++) {

            args[0] = startingCity[i];
            args[1] = endingCity[i];
            String from = args[0];
            String to = args[1];

            long t0 = System.nanoTime();
            Integer dist = shortest(map.lookup(from), map.lookup(to));
            long time = (System.nanoTime() - t0) / 1_000_000;
            System.out.println("shortest " + args[0] + " to " + args[1] + " : " + dist + " min (" + time + " ms)");
        }
    }

    public Paths() {
        path = new City[54];
        sp = 0;
        maximumLength = null;
    }

    private static Integer shortest(City from, City to) {
        if (from == to)
            return 0;
        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }
        Integer shrt = Integer.MAX_VALUE;
        path[sp++] = from;
        if (from.connections != null) {
            for (int i = 0; i < from.connections.length; i++) {

                Connection conn = from.connections[i];
                if (conn != null) {

                    if (maximumLength == null || maximumLength - conn.getTime() > 0) {

                        Integer timeNext = shortest(conn.getDestination(), to);

                        if (timeNext != null && (shrt - conn.getTime()) > timeNext) {
                            shrt = timeNext + conn.getTime();
                            if (maximumLength == null || conn.getTime() > maximumLength) {
                                maximumLength = timeNext + conn.getTime();
                            }
                        }
                    }

                }

            }

        }
        path[sp--] = null;
        return shrt;
    }
}
