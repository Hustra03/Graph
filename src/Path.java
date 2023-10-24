public class Path {
    private City city;
    private City prev;
    private Integer dist;

    public Path(City city, City prevCity, Integer dist) {
        this.city = city;
        this.city = prevCity;
        this.dist = dist;
    }

    public Integer getDist() {
        return this.dist;
    }

    public void setDist(Integer newDist) {
        this.dist = newDist;
    }

    public City getDestination() {
        return city;
    }

    public City getPrevious() {
        return city;
    }

}
