package avatar.core;

import avatar.entities.benders.*;
import avatar.entities.monuments.*;
import avatar.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public class AvatarsManager {

    private List<Bender> airNation;
    private List<Bender> waterNation;
    private List<Bender> fireNation;
    private List<Bender> earthNation;

    private List<Monument> airMonuments;
    private List<Monument> waterMonuments;
    private List<Monument> fireMonuments;
    private List<Monument> earthMonuments;

    private List<String> warRecords;

    public AvatarsManager() {
        this.airNation = new ArrayList<>();
        this.waterNation = new ArrayList<>();
        this.fireNation = new ArrayList<>();
        this.earthNation = new ArrayList<>();

        this.airMonuments = new ArrayList<>();
        this.waterMonuments = new ArrayList<>();
        this.fireMonuments = new ArrayList<>();
        this.earthMonuments = new ArrayList<>();

        this.warRecords = new ArrayList<>();
    }

    public void createBender(String type, String name, int power, double secondaryParam) {

        switch (type) {
            case Constants.AIR_COMMAND:
                airNation.add(new AirBender(name, power, secondaryParam));
                break;
            case Constants.WATER_COMMAND:
                waterNation.add(new WaterBender(name, power, secondaryParam));
                break;
            case Constants.FIRE_COMMAND:
                fireNation.add(new FireBender(name, power, secondaryParam));
                break;
            case Constants.EARTH_COMMAND:
                earthNation.add(new EarthBender(name, power, secondaryParam));
                break;
        }
    }

    public void createMonument(String type, String name, int affinity) {

        switch (type) {
            case Constants.AIR_COMMAND:
                airMonuments.add(new AirMonument(name, affinity));
                break;
            case Constants.WATER_COMMAND:
                waterMonuments.add(new WaterMonument(name, affinity));
                break;
            case Constants.FIRE_COMMAND:
                fireMonuments.add(new FireMonument(name, affinity));
                break;
            case Constants.EARTH_COMMAND:
                earthMonuments.add(new EarthMonument(name, affinity));
                break;
        }
    }

    public String statusNation(String type) {
        return this.statusString(type);
    }

    public void war(String nation) {
        double airPower = this.totalNationPower(airNation, airMonuments);
        double waterPower = this.totalNationPower(waterNation, waterMonuments);
        double firePower = this.totalNationPower(fireNation, fireMonuments);
        double earthPower = this.totalNationPower(earthNation, earthMonuments);

        if (airPower > waterPower && airPower > firePower && airPower > earthPower) {
            destroyEarthNation();
            destroyFireNation();
            destroyWaterNation();
        } else if (waterPower > airPower && waterPower > firePower && waterPower > earthPower) {
            destroyFireNation();
            destroyEarthNation();
            destroyAirNation();
        } else if (firePower > airPower && firePower > waterPower && firePower > earthPower) {
            destroyEarthNation();
            destroyAirNation();
            destroyEarthNation();
        } else {
            destroyAirNation();
            destroyFireNation();
            destroyWaterNation();
        }

        this.warRecords.add(String.format("War %d issued by %s", warRecords.size() + 1, nation));
    }

    private void destroyAirNation() {
        this.airNation = new ArrayList<>();
        this.airMonuments = new ArrayList<>();
    }

    private void destroyWaterNation() {
        this.waterNation = new ArrayList<>();
        this.waterMonuments = new ArrayList<>();
    }

    private void destroyFireNation() {
        this.fireMonuments = new ArrayList<>();
        this.fireNation = new ArrayList<>();
    }

    private void destroyEarthNation() {
        this.earthNation = new ArrayList<>();
        this.earthMonuments = new ArrayList<>();
    }

    public String quit() {
        StringBuilder sb = new StringBuilder();
        warRecords.forEach(wr -> sb.append(wr).append(System.lineSeparator()));
        return sb.toString();
    }

    private String statusString(String type) {
        List<Bender> benders = new ArrayList<>();
        List<Monument> monuments = new ArrayList<>();

        switch (type) {
            case Constants.AIR_COMMAND:
                benders = airNation;
                monuments = airMonuments;
                break;
            case Constants.WATER_COMMAND:
                benders = waterNation;
                monuments = waterMonuments;
                break;
            case Constants.EARTH_COMMAND:
                benders = earthNation;
                monuments = earthMonuments;
                break;
            case Constants.FIRE_COMMAND:
                benders = fireNation;
                monuments = fireMonuments;
                break;

        }
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(" Nation").append(System.lineSeparator()).append("Benders:");
        if (benders.size() == 0) {
            sb.append(" None");
        } else {
            benders.forEach(an -> sb.append(System.lineSeparator()).append(an.toString()));
        }
        sb.append(System.lineSeparator()).append("Monuments:");
        if (monuments.size() == 0) {
            sb.append(" None");
        } else {
            monuments.forEach(am -> sb.append(System.lineSeparator()).append(am.toString()));
        }
        return sb.append(System.lineSeparator()).toString();
    }

    private double totalNationPower(List<Bender> benders, List<Monument> monuments) {
        double power = 0;
        for (Bender bender : benders) {
            power += bender.benderPower();
        }
        int affinity = 0;
        for (Monument monument : monuments) {
            affinity += monument.getAffinity();
        }
        return power + ((power / 100) * affinity);
    }
}
