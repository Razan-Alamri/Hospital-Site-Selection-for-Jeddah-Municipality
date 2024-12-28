package Recursion;

// Razan Alamri, Program 2: Recursion, 27-10-2021.
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Recursion {

    public static void main(String[] args) throws Exception {

        File inputFile = new File("input.txt");
        // Check if the inputFile is exists.
        if (!inputFile.exists()) {
            System.out.println("The File " + inputFile.getName() + " is not exists");
            System.exit(0);
        }
        // Scanner to read from input txt file.
        Scanner inputSystem = new Scanner(inputFile);
        // read total available funds (budget).
        int availableFundNumber = inputSystem.nextInt();
        // read number of hospital Sites.
        int proposedSitesNumber = inputSystem.nextInt();
        // create array of object Hospital for save information about hospital.
        ArrayList<Hospital> hospitalSites = new ArrayList<Hospital>();

        // loop for save all hospital.
        for (int rr = 0; rr < proposedSitesNumber; rr++) {
            // read hospital name.
            String nameHospital = inputSystem.next();
            // creat array for save all districts Covered in each site.
            ArrayList<String> districtsCovered = new ArrayList<String>();

            while (inputSystem.hasNext()) {
                // read districts.
                String districts = inputSystem.next();

                if (districts.equals("Cost")) {
                    break;
                }
                // add district in districtsCovered array.
                districtsCovered.add(districts);
            }
            // read cost of site.
            int costForSite = inputSystem.nextInt();
            // add each site object in hospitalSites array.
            hospitalSites.add(new Hospital(nameHospital, districtsCovered, costForSite));

        }
        // number of hospital Sites.
        int hospitalSitesLenth = hospitalSites.size();

        // array to save list of hospitals that should be built to provide coverage to
        // the maximum number of cities.
        ArrayList<String> SavePossibleHospital = new ArrayList<String>();
        // call Recursive method.
        SavePossibleHospital = RecursiveOptimalCoverage(availableFundNumber, hospitalSites, hospitalSitesLenth,
                SavePossibleHospital);

        // save total Naumber Of Districts Covered.
        int totalNaumberOfCovered = totalNaumberOfDistrictsCovered(SavePossibleHospital, hospitalSites);

        // for change format of cost from 80000000 to 80,000,000.
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String formatavailableFundNumber = decimalFormat.format(availableFundNumber);

        // for print total available funds , optimal coverage site and total Naumber Of
        // Districts Covered.
        System.out.println("Suppose total available funds (budget) are: SARs " + formatavailableFundNumber + ".");

        System.out.print("The optimal coverage is [");
        for (int vv = SavePossibleHospital.size() - 1; vv >= 0; vv--) {
            System.out.print(SavePossibleHospital.get(vv) + ",");
        }
        System.out.print("] covers " + totalNaumberOfCovered + " districts.");

        // * closing the input for input.txt *.
        inputSystem.close();
        System.out.println();
    }

    // --------------------- this is Recursive method for returns a list of
    // hospitals that should be built to provide coverage to the maximum number of
    // cities.
    public static ArrayList<String> RecursiveOptimalCoverage(int availableFundNumber, ArrayList<Hospital> hospitalSites,
            int hospitalSitesLenth, ArrayList<String> SavePossibleHospital) {

        // Base Case
        if (availableFundNumber == 0 || hospitalSitesLenth == 0) {
            return SavePossibleHospital;
        }
        // If cost of the hospital Site is less than or equle available Fund Number,
        // then this hospital Sites can be included,
        // in the optimal solution.
        if (hospitalSites.get(hospitalSitesLenth - 1).getCostForSite() <= availableFundNumber) {
            // creat new array save sites for compare with SavePossibleHospital array whiche
            // sites will coverage.
            ArrayList<String> updateSavePossibleHospital = new ArrayList<>();
            for (int i = 0; i < SavePossibleHospital.size(); i++) {
                updateSavePossibleHospital.add(SavePossibleHospital.get(i));
            }
            SavePossibleHospital.add(hospitalSites.get(hospitalSitesLenth - 1).getNameHospital());
            // Return the maximum of two cases.
            return coverageLargeNumberOfCities(
                    RecursiveOptimalCoverage(
                            (availableFundNumber - hospitalSites.get(hospitalSitesLenth - 1).getCostForSite()),
                            hospitalSites, hospitalSitesLenth - 1, SavePossibleHospital),
                    RecursiveOptimalCoverage(availableFundNumber, hospitalSites, hospitalSitesLenth - 1,
                            updateSavePossibleHospital),
                    hospitalSites);
        } else {
            return RecursiveOptimalCoverage(availableFundNumber, hospitalSites, hospitalSitesLenth - 1,
                    SavePossibleHospital);
        }
    }

    // --------------------- this method for maximum number of cities.
    private static ArrayList<String> coverageLargeNumberOfCities(ArrayList<String> Sites1ToCompare,
            ArrayList<String> Sites2ToCompare, ArrayList<Hospital> hospitalSites) {

        // compare between sites and return sites whiche have maximum number of cities.
        return (totalNaumberOfDistrictsCovered(Sites1ToCompare,
                hospitalSites) > totalNaumberOfDistrictsCovered(Sites2ToCompare, hospitalSites))
                        ? Sites1ToCompare
                        : ((totalNaumberOfDistrictsCovered(Sites1ToCompare,
                                hospitalSites) < totalNaumberOfDistrictsCovered(Sites2ToCompare, hospitalSites))
                                        ? Sites2ToCompare
                                        : ((Sites1ToCompare
                                                .size() > Sites2ToCompare.size())
                                                        ? Sites1ToCompare
                                                        : ((Sites1ToCompare.size() < Sites2ToCompare.size())
                                                                ? Sites2ToCompare
                                                                : (Sites1ToCompare.get(0).charAt(4) >= Sites2ToCompare
                                                                        .get(0).charAt(4)) ? Sites1ToCompare
                                                                                : Sites2ToCompare)));
    }

    // --------------------- this method for return total Naumber Of Districts will
    // Covered.
    public static int totalNaumberOfDistrictsCovered(ArrayList<String> SavePossibleHospital,
            ArrayList<Hospital> hospitalSites) {

        // array for save total Naumber Of Districts.
        ArrayList<String> LargDistrictsCovered = new ArrayList<>();
        // loop for save all Districts will coverd in each Possible Hospital I found.
        for (int aa = 0; aa < SavePossibleHospital.size(); aa++) {

            for (int cc = 0; cc < hospitalSites.size(); cc++) {
                // takes only Districts in sites I will cover.
                if (SavePossibleHospital.get(aa).equals(hospitalSites.get(cc).getNameHospital())) {

                    for (int xx = 0; xx < hospitalSites.get(cc).getDistrictsCovered().size(); xx++) {
                        // counter For Coun Total Naumber Of Districts.
                        int ForCounTotalNaumOfDistricts = 0;
                        // loop for save Districts in array.
                        for (int rr = 0; rr < LargDistrictsCovered.size(); rr++) {
                            // when count up the total number of districts covered, we will do not
                            // double-count a district.
                            if (!(hospitalSites.get(cc).getDistrictsCovered().get(xx)
                                    .equals(LargDistrictsCovered.get(rr)))) {
                                ForCounTotalNaumOfDistricts++;
                            }
                        }
                        // then add Districts to LargDistrictsCovered array whithout repetition.
                        if (LargDistrictsCovered.size() == ForCounTotalNaumOfDistricts) {
                            LargDistrictsCovered.add(hospitalSites.get(cc).getDistrictsCovered().get(xx));
                        }
                    }
                    break;
                }
            }
        }
        // LargDistrictsCovered array size is egual the total Naumber Of Districts
        // Covered, so we will returned.
        return LargDistrictsCovered.size();
    }

}
