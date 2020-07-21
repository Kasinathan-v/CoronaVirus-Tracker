package io.javabrains.coronavirustracker.Modules;

public class LocationStats {

    private String state;
    private String country;
    private int latestcases;
    private int delta;


    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestcases() {
        return latestcases;
    }

    public void setLatestcases(int latestcases) {
        this.latestcases = latestcases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestcases=" + latestcases +
                ", delta=" + delta +
                '}';
    }
}
