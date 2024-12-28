
package a;

public class Q1_ConceptPart {


    public static void main(String[] args) {

        char[] lettarInCared = {'J', 'K',};
        int numberOftims = 4;
        printAllWaya(numberOftims, lettarInCared, "");

    }

    private static void printAllWaya(int numberOftims, char[] lettarInCared, String way) {

        if (numberOftims == 0) {
            System.out.println(way);
            return;
        }
        for (int i = 0; i < lettarInCared.length; ++i) {

            String newWay = way + lettarInCared[i];
            printAllWaya(numberOftims - 1, lettarInCared, newWay);
        }
    }
}

    

    

