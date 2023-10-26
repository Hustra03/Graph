public class Path implements Comparable<Path>{
    private City city;
    private City prev;
    private Integer dist;

    public Path(City city) {
        this.city = city;
        this.prev = null;
        this.dist = 0;
    }

    public Path(City city, City prev, Integer dist) {
        this.city = city;
        this.prev = prev;
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
        return prev;
    }

    @Override
    public int compareTo(Path o) {
        if(this.dist==o.dist)
        {return 0;}
        else{
            if (this.dist>o.dist) {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }

}
