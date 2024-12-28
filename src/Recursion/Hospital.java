
package Recursion;

// Razan Alamri, Program 2: Recursion, 27-10-2021.
import java.util.ArrayList;

public class Hospital {

    // Data filed

    private String nameHospital;
    private ArrayList<String> districtsCovered;
    private int costForSite;

    // contructors

    public Hospital(String nameHospital, ArrayList<String> districtsCovered, int costForSite) {
        this.nameHospital = nameHospital;
        this.districtsCovered = districtsCovered;
        this.costForSite = costForSite;
    }

    // Setters and Getters of Data filed

    public String getNameHospital() {
        return nameHospital;
    }

    public void setNameHospital(String nameHospital) {
        this.nameHospital = nameHospital;
    }

    public ArrayList<String> getDistrictsCovered() {
        return districtsCovered;
    }

    public void setDistrictsCovered(ArrayList<String> districtsCovered) {
        this.districtsCovered = districtsCovered;
    }

    public int getCostForSite() {
        return costForSite;
    }

    public void setCostForSite(int costForSite) {
        this.costForSite = costForSite;
    }

    @Override
    public String toString() {
        return nameHospital + " " + districtsCovered + " Cost" + costForSite;
    }

}