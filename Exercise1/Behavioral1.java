import java.util.ArrayList;
import java.util.List;

interface Stock {
    void register(Investor observer);
    void unregister(Investor observer);
    void notifyInvestors();
}

class StockTicker implements Stock {
    private List<Investor> investors = new ArrayList<>();
    private String stockName;
    private double price;

    public StockTicker(String stockName) { this.stockName = stockName; }

    public void setPrice(double price) {
        this.price = price;
        notifyInvestors();
    }
    public void register(Investor o) { investors.add(o); }
    public void unregister(Investor o) { investors.remove(o); }
    public void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(stockName, price);
        }
    }
}

interface Investor {
    void update(String stockName, double price);
}

class MobileAppDisplay implements Investor {
    private String name;
    public MobileAppDisplay(String name) { this.name = name; }
    public void update(String stockName, double price) {
        System.out.println(name + " received update: " + stockName + " is now $" + price);
    }
}

public class StockMarket {
    public static void main(String[] args) {
        StockTicker googleStock = new StockTicker("GOOGL");
        Investor app1 = new MobileAppDisplay("InvestorApp1");
        Investor app2 = new MobileAppDisplay("InvestorApp2");

        googleStock.register(app1);
        googleStock.register(app2);

        googleStock.setPrice(150.00);
        googleStock.setPrice(152.50);
    }
}