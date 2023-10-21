package us.marseilles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Determine what weights you can lift for a given combination of plates
 */
public class PlatePermutationCalculator
{
    private static final int BAR_WEIGHT = 45;
    
    private static final double[] DEFAULT_PLATES = {2.5, 2.5, 2.5, 5d, 10d, 25d, 45d};
    
    
    public static void main(String[] args)
    {
        double[] plates = getPlates(args);
        SortedSet<List<Double>> permutations =  createPermutations(plates);
        printPermutations(plates, permutations);
        printUnavailableWeights(plates, permutations);
    }

    /**
     * @return all possible combinations of provided plates, including combos which do not include all plates and combos
     *         with the same sum total weight, but excluding combinations which contain exactly the same plates. Combos
     *         are sorted by sum total weight.
     *         Example: for [5,5,5,10] you'll receive back [], [5], [5,5], [10], [5,5,5], [5,10], [5,5,10], [5,5,5,10]
     *         which includes same-sum combinations [5,5] & [10] and [5,5,5] & [5,10], but only contains [5,5] once.
     */
    public static SortedSet<List<Double>> createPermutations(double[] plates)
    {
        // get all possible bit arr permutations for an arr of this size (e.g.: for [5,10], get [0,0] [0,1] [1,0] [1,1])
        SortedSet<boolean[]> bitPerms = createBitPermutations(plates.length);
        
        // use the bit arr to determine which plates to include for each permutation (e.g.: [] [5] [10] [5,10])
        SortedSet<List<Double>> permutations = new TreeSet<>(PlatePermutationCalculator::compareDoubleLists);
        for (boolean[] bitPerm : bitPerms)
        {
            List<Double> platePerm = new ArrayList<>();
            for (int i = 0; i < bitPerm.length; i++)
            {
                if (bitPerm[i])
                {
                    platePerm.add(plates[i]);
                }
            }
            permutations.add(platePerm);
        }
        
        return permutations;
    }

    public static void printPermutations(double[] plates, Set<List<Double>> permutations)
    {
        for (List<Double> permutation : permutations)
        {
            double sum = sumDoubleList(permutation);
            System.out.printf("%-3d :\t\t", totalBarWeight(sum));

            // Build a queue of all possible plates in order to know in which column to print the next weight. We'll
            // poll each plate to see if the weight in the permutation matches it; if it does, print it; else blank col.
            Queue<Double> plateQueue = new LinkedList<>(permutation);

            for (Double plate : plates)
            {
                String plateToPrint = null;
                if (plate.equals(plateQueue.peek()))
                {
                    plateQueue.poll();

                    // For printing, determine whether plate weight includes a fractional pound (like 2.5)
                    if (plate == Math.floor(plate))
                    {
                        plateToPrint = String.format("%.0f", plate);
                    }
                    else
                    {
                        plateToPrint = Double.toString(plate);
                    }
                }

                if (plateToPrint == null)
                {
                    System.out.print(" ".repeat(7));
                }
                else
                {
                    System.out.print(plateToPrint + "s" + " ".repeat(6 - plateToPrint.length()));
                }
            }

            System.out.println("(+ 45)");
        }
    }

    /**
     * Determine which weights are unattainable between the bar weight and all of these plates on the bar.
     * The increment between plates to use is determined by doubling the smallest plate.
     */
    public static void printUnavailableWeights(double[] plates, SortedSet<List<Double>> permutations)
    {
        System.out.println();
        System.out.print("Unreachable weights: ");
        
        if (plates.length > 1) // if only 0 or 1 plates are provided, then all increments are "available"
        {
            final double smallestPlate = Arrays.stream(plates).min().getAsDouble();
            
            if (smallestPlate > 0) // If all plates are the same size, skip printing "unreachable weights" section
            {
                // Get the difference between first and last plates, then how many increments should exist between them
                double spread = sumDoubleList(permutations.last()) - sumDoubleList(permutations.first());
                int numIncrements = (int) (spread / smallestPlate);

                Set<Integer> allIncrements = IntStream.rangeClosed(0, numIncrements)
                    .mapToDouble(val -> val * smallestPlate)
                    .mapToInt(PlatePermutationCalculator::totalBarWeight)
                    .boxed()
                    .collect(Collectors.toSet());

                Set<Integer> reachableIncrements = permutations.stream()
                    .map(PlatePermutationCalculator::sumDoubleList)
                    .mapToInt(PlatePermutationCalculator::totalBarWeight)
                    .boxed()
                    .collect(Collectors.toSet());

                List<Integer> unreachableIncrements = allIncrements.stream()
                    .filter(weight -> !reachableIncrements.contains(weight))
                    .sorted()
                    .toList();

                System.out.print("(using increment " + (int) (smallestPlate * 2) + ") ");

                if (!unreachableIncrements.isEmpty())
                {
                    System.out.print(unreachableIncrements + " and ");
                }
            }
        }

        int ceiling = totalBarWeight(sumDoubleList(permutations.last()));
        System.out.println("weights > " + ceiling);
    }

    /**
     * Create bit permutations by iterating all binary numbers from 0 to 2^numItems
     *
     * For example, 2 items would produce 4 bit permutations: [0,0] [0,1] [1,0] [1,1]
     */
    private static SortedSet<boolean[]> createBitPermutations(int numItems)
    {
        SortedSet<boolean[]> bitPerms = new TreeSet<>(PlatePermutationCalculator::compareBoolArr);
        int numPerms = (int) Math.pow(2, numItems);
        
        for (int num = 0; num < numPerms; num++)
        {                                                                   // num == 0 |num == 1 |num == 2 |num == 3
            String bitString = Integer.toBinaryString(num);                 //      "0" |     "1" |     "10"|    "11"
            String zeroPadding = "0".repeat(numItems - bitString.length()); //  "0"     | "0"     | ""      | ""
            String[] bitStrArr = (zeroPadding + bitString).split("");       // ["0","0"]|["0","1"]|["1","0"]|["1","1"]

            // Convert string arr to boolean arr
            boolean[] bitPerm = new boolean[numItems];                      //   [0,0]  |  [0,1]  |  [1,0]  |  [1,1]
            for (int i = 0; i < numItems; i++)  // cannot collect a stream to a primitive boolean array (T_T)
            {
                bitPerm[i] = bitStrArr[i].equals("1");
            }
            bitPerms.add(bitPerm);
        }
        
        return bitPerms;
    }

    /**
     * @return 0 if arrays are identical; else the array to first show true when the other doesn't (read ltr) is greater
     */
    private static int compareBoolArr(boolean[] arr1, boolean[] arr2)
    {
        for (int i = 0; i < arr1.length; i++)
        {
            if (arr1[i] == arr2[i])
            {
                continue;
            }
            return arr1[i] ? 1 : -1;
        }
        return 0;
    }

    /**
     * @return 0 if the lists are identical; else the list with the greater sum is greater.
     *         Returning 0 indicates one of the plate sets is a duplicate and can be disregarded; however, different
     *         plate combinations with the same sum total will return -1, indicating they are not identical.
     */
    private static int compareDoubleLists(List<Double> list1, List<Double> list2)
    {
        if (list1.equals(list2))
        {
            return 0;
        }
        else if (sumDoubleList(list1) > sumDoubleList(list2))
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    private static double sumDoubleList(List<Double> list)
    {
        return list.stream().mapToDouble(Double::valueOf).sum();
    }

    /**
     * Total weight on bar for plate, include matching plate and the bar itself
     */
    private static int totalBarWeight(double plateWeight)
    {
        return (int) (plateWeight * 2) + BAR_WEIGHT;
    }

    /**
     * Get plate weights passed as args; else, default demo plate set
     */
    private static double[] getPlates(String[] args)
    {
        double[] plates;
        if (args.length == 0)
        {
            plates = DEFAULT_PLATES;
            System.out.println("Using default plate set because no values were passed as args:");
        }
        else
        {
            plates = Arrays.stream(args)
                .mapToDouble(Double::valueOf)
                .sorted()
                .toArray();
            System.out.println("Plate weights passed as args:");
        }
        System.out.println(Arrays.toString(plates));
        System.out.println();
        return plates;
    }
}