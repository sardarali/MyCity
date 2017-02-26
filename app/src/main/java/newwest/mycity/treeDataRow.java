package newwest.mycity;

/**
 * Created by sali on 2017-02-26.
 */

public class treeDataRow {
    private String cultivar;
    private String genus;
    private String species;
    private String commonName;
    private String sciName;
    private String locType;
    private double xLoc;
    private double yLoc;
    private String neighName;
    private String imageName;

    public treeDataRow(String cultivar, String genus, String species, String commonName, String sciName,
                       String locType, double xLoc, double yLoc, String neighName, String imageName) {
        this.cultivar = cultivar;
        this.genus = genus;
        this.species = species;
        this.commonName = commonName;
        this.sciName = sciName;
        this.locType = locType;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.neighName = neighName;
        this.imageName = imageName;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        this.sciName = sciName;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public double getxLoc() {
        return xLoc;
    }

    public void setxLoc(double xLoc) {
        this.xLoc = xLoc;
    }

    public double getyLoc() {
        return yLoc;
    }

    public void setyLoc(double yLoc) {
        this.yLoc = yLoc;
    }

    public String getNeighName() {
        return neighName;
    }

    public void setNeighName(String neighName) {
        this.neighName = neighName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
