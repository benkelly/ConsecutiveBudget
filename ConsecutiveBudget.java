import java.util.HashSet;

public class ConsecutiveBudget {

    public static void main(String[] args) {

        int seqArray[] = { 2, 3, 10, 1, 1, 2, 1 };
        int budget = 7;

        System.out.println("m elements: ");
        System.out.println(printElementList(seqArray));
        int conNum = longestConsecutive(seqArray);

        budgetSelection(seqArray, budget, conNum, seqArray.length);
        System.out.println("QED");
    }

    private static String printElementList(int[] seqArray) {
        int Len = seqArray.length;
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < Len; i++) {
            sb.append(seqArray[i] + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(" ]");

        return sb.toString();
    }

    private static int longestConsecutive(int[] seqArray) {
        int longestConsecutiveIndex = 0;
        int Len = seqArray.length;

        HashSet<Integer> numSet = new HashSet<Integer>();
        for (int i = 0; i < Len; i++) {
            numSet.add(seqArray[i]);
        }
        for (int i = 0; i < Len; i++) {
            int tempConsecutive = 0;
            int val = seqArray[i];
            tempConsecutive = checkConsecutive(tempConsecutive, val, numSet);
            if (tempConsecutive > longestConsecutiveIndex) {
                longestConsecutiveIndex = tempConsecutive;
            }
        }
        return longestConsecutiveIndex;
    }

    private static int checkConsecutive(int tempConsecutive, int num, HashSet<Integer> numSet) {
        if (numSet.contains(num)) {
            tempConsecutive++;
            numSet.remove(num);

            tempConsecutive = checkConsecutive(tempConsecutive, num + 1, numSet);
            tempConsecutive = checkConsecutive(tempConsecutive, num - 1, numSet);
        }

        return tempConsecutive;
    }

    private static void budgetSelection(int[] seqArray, int budget, int consecutiveIndex, int num) {

        int[] budgetSelect = new int[num - consecutiveIndex];
        for (int i = consecutiveIndex; i < num; i++) {
            budgetSelect[(i - consecutiveIndex)] = (int) (seqArray[i]);
        }

        int sum = 0;
        for (int number : budgetSelect) {
            sum += number;
        }

        System.out.println("output elements: ");
        System.out.println(printElementList(budgetSelect));
        System.out.println("The budget Selection length is " + budgetSelect.length + ". and sum is " + sum + ".");

        if (sum < budget) {
            System.out.println("The budget is under by: " + (budget - sum));
        } else {
            System.out.println("The budget is over by: " + (sum - budget));
        }
    }
}