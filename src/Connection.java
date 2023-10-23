public class Connection {
    int time;
    City destinationCity;

    Connection(int time, City destinationCity)
    {
        this.time=time;
        this.destinationCity=destinationCity;
    }

    public int getTime()
    {return this.time;}

    
    public City getDestination()
    {return this.destinationCity;}
}
