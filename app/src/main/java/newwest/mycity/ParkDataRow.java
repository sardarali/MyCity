package newwest.mycity;

/**
 * Created by sali on 2017-02-25.
 */

public class ParkDataRow {
    private String streetName;
    private int streetNumber;
    private String parkName;
    private String parkCategory;
    private String neighbourhood;
    private String parkImageName;
    private String parkURL;
    private float xCoord;
    private float yCoord;
    private int rating;


    public ParkDataRow(String streetName, int streetNumber, String parkName, String parkCategory,
                       String neighbourhood, String parkImageName, String parkURL, float xCoord, float yCoord, int rating) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.parkName = parkName;
        this.parkCategory = parkCategory;
        this.neighbourhood = neighbourhood;
        this.parkImageName = parkImageName;
        this.parkURL = parkURL;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.rating = rating;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
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

    public float getxCoord() {
        return xCoord;
    }

    public void setxCoord(float xCoord) {
        this.xCoord = xCoord;
    }

    public float getyCoord() {
        return yCoord;
    }

    public void setyCoord(float yCoord) {
        this.yCoord = yCoord;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
