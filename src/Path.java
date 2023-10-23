public class Path {
    private City city;
    private City prev;
    private Integer dist;
    private Integer index;

    public Path(City city, City prevCity, Integer dist, Integer index) {
        this.city = city;
        this.city = prevCity;
        this.dist = dist;
        this.index = index;
    }

    public Integer getDist() {
        return this.dist;
    }

    public Integer getIndex() {
        return this.index;
    }

    public City getDestination()
    {
        return city;
    }    
    public City getPrevious()
    {
        return city;
    }
}
