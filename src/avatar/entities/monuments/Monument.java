package avatar.entities.monuments;

public abstract class Monument {

    private String name;

    public Monument(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract int getAffinity();
}
