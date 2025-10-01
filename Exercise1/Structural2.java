interface Coffee {
    double getCost();
    String getDescription();
}

class SimpleCoffee implements Coffee {
    public double getCost() { return 5.0; }
    public String getDescription() { return "Simple coffee"; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
    public double getCost() { return decoratedCoffee.getCost(); }
    public String getDescription() { return decoratedCoffee.getDescription(); }
}

class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee coffee) { super(coffee); }
    public double getCost() { return super.getCost() + 1.5; }
    public String getDescription() { return super.getDescription() + ", with milk"; }
}

class WithSugar extends CoffeeDecorator {
    public WithSugar(Coffee coffee) { super(coffee); }
    public double getCost() { return super.getCost() + 0.5; }
    public String getDescription() { return super.getDescription() + ", with sugar"; }
}

public class CoffeeShop {
    public static void main(String[] args) {
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());

        myCoffee = new WithMilk(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());

        myCoffee = new WithSugar(myCoffee);
        System.out.println(myCoffee.getDescription() + " $" + myCoffee.getCost());
    }
}