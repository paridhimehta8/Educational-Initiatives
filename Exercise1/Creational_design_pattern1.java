interface Transport {
    void deliver();
}

class Truck implements Transport {
    public void deliver() { System.out.println("Delivering by land in a truck."); }
}

class Ship implements Transport {
    public void deliver() { System.out.println("Delivering by sea in a ship."); }
}

abstract class TransportFactory {
    public abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

class RoadLogistics extends TransportFactory {
    public Transport createTransport() { return new Truck(); }
}

class SeaLogistics extends TransportFactory {
    public Transport createTransport() { return new Ship(); }
}

public class LogisticsApp {
    public static void main(String[] args) {
        TransportFactory factory = new RoadLogistics();
        factory.planDelivery(); 

        factory = new SeaLogistics();
        factory.planDelivery();
    }
}