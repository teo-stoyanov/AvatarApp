package avatar.entities.benders;

public class FireBender extends Bender {

    private double heatAggression;
    public FireBender(String name, int power, double heatAggression) {
        super(name, power);
        this.heatAggression = heatAggression;
    }

    @Override
    public double benderPower() {
        return getPower() * this.heatAggression;
    }

    @Override
    public String toString() {
        return String.format("###Fire Bender: %s, Power: %d, Heat Aggression: %.2f",super.getName(),super.getPower(),this.heatAggression);
    }
}
