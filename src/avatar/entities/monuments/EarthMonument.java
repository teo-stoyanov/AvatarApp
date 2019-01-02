package avatar.entities.monuments;

public class EarthMonument extends Monument {

    private int earthMonument;

    public EarthMonument(String name, int earthMonument) {
        super(name);
        this.earthMonument = earthMonument;
    }

    @Override
    public int getAffinity() {
        return this.earthMonument;
    }

    @Override
    public String toString() {
        return String.format("###Earth Monument: %s, Earth Affinity: %d",super.getName(),this.earthMonument);
    }
}
