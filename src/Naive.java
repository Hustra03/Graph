public class Naive {
    public static void main(String[] args) {
        Map map = new Map("src\\trains.csv");
        args = new String[3];
        args[0] = "Stockholm";
        args[1] = "Södertälje";
        args[2] = "100";
        String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);
        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;
        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }

    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0)
            return null;

        if (from == to)
            return 0;

        Integer shrt = Integer.MAX_VALUE;

        for (int i = 0; i < from.connections.length; i++) {

            if (from.connections != null) {
                Connection conn = from.connections[i];
                if (conn != null) {
                    if (max - conn.getTime() > 0) {
                        if (shrt > shortest(conn.getDestination(), to, max - conn.getTime())) {
                            shrt = shortest(conn.getDestination(), to, max - conn.getTime());
                        }

                    }
                }

            }
        }
        return shrt;
    }
}
