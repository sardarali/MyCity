package newwest.mycity;

/**
 * Created by sali on 2017-02-25.
 */

public class ParkDataRow {
    String streetName;
    String streetNumber;
    String parkName;
    String parkCategory;
    String neighbourhood;
    String parkImageName;
    String parkURL;

    public ParkDataRow(String streetName, String streetNumber, String parkName, String parkCategory, String neighbourhood, String parkImageName, String parkURL) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.parkName = parkName;
        this.parkCategory = parkCategory;
        this.neighbourhood = neighbourhood;
        this.parkImageName = parkImageName;
        this.parkURL = parkURL;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkCategory() {
        return parkCategory;
    }

    public void setParkCategory(String parkCategory) {
        this.parkCategory = parkCategory;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getParkImageName() {
        return parkImageName;
    }

    public void setParkImageName(String parkImageName) {
        this.parkImageName = parkImageName;
    }

    public String getParkURL() {
        return parkURL;
    }

    public void setParkURL(String parkURL) {
        this.parkURL = parkURL;
    }
}
