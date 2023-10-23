public class Dijkstra {

    Path done[];

    private class Path {
        private City city;
        private City prev;
        private Integer dist;
        private Integer index;

        public Path(City city, City prevCity, Integer dist, Integer index)
        {
            this.city=city;
            this.city=prevCity;
            this.dist=dist;
            this.index=index;
        }
    }

    

}
