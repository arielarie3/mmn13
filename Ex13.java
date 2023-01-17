/**
 * This class represents all the mmn13's methods
 * @version (2023a)
 * @author Ariel Arie
 */

public class Ex13
{
    // Q1
    /**
     * This method get a string consisting of the characters '0' and '1'
     * and returns the minimum number of substitutions required to get a binary string
     * so that the characters '0' and '1' will be alternating
     * @param s - The string to chack
     * @return the minimum number of substitutions required to get a binary string
     * so that the characters '0' and '1' will be alternating
     * Time complexity - In this method we loop over the string of length n only once and therefore Time complexity = O(n)
     * Space complexity - In this method we create two new variables and therefore Space complexity = O(1)
     */
    public static int alternating(String s)                                               
    {                                                                                     // Checking the lowest number from the private method
        return (Math.min(MinNumberOfRplacements(s,'0'),MinNumberOfRplacements(s,'1')))/2; // and dividing it by 2 will return us the lowest 
    }                                                                                     // number of exchanges required
    // end of alternating method

    /**
     * This private method get a binary string and a number '0' or '1'
     * and compares it to a binary string starting with the chosen number
     * and alternating between the characters '0' and '1'
     * @param s - The string to chack
     * @param startingNum - The number of the character in the string from which we want to start the test
     * @return the number of matching errors between the obtained string
     * and the ideal string
     */
    private static int MinNumberOfRplacements(String x, char startingNum)
    { 
        int errorCounter = 0; // This variable sums the amount of errors relative to the ideal binary string

        for (int i=0; i<(x.length()) ;i++)
        {
            if (x.charAt(i) != startingNum) // check if the character is the same as the character in the same place in the ideal string 
            {
                errorCounter++; 
            }

            if (startingNum == '0') //Each time after the test we will replace the character value with the ideal string in order to get an alternating binary string
            {
                startingNum = '1'; 
            }
            else startingNum = '0';
        }
        return (errorCounter); // return the number of mismatches between our string and the ideal binary string
    }
    // end of MinNumberOfRplacements method

    // Q2
    /**
     * 1. The What method get an array of integers and returns the size of the largest subset whose sum is an even number
     * 2. Time complexity = O(n^3)
     *    Space complexity = O(1)
     * @param a - int arry
     * @return the largest subset whose sum is an even number
     * 4. New method time complexity = O(n)
     *    New method Space complexity = O(1) 
     */
    public static int what (int []a)
    {
        int sum = 0;
        int oddArrayDivider = 0; // This variable will be used to check the maximum length of a sub-array in case of an odd sum of the array

        for (int i = 0; i<a.length; i++)
        {
            sum = sum + a[i]; // The sum of the array
            if ((a[i] != 0) && (a[i] % 2 ==1)) // Checking whether the number is odd 
            {
                if (Math.max(i,a.length-1-i)>oddArrayDivider)    // Calculating the sub-array on both sides of the odd cell and
                {                                                // checking whether the length of the larger sub-array of the 
                    oddArrayDivider = Math.max(i,a.length-1-i);  // two is also greater than the sub-array entered in the current variable
                    // If so, we will enter the length of the array into the variable
                }
            }
        }

        if (sum == 0 || sum % 2 == 0) // If the sum of the array is even, the method 
            return a.length;          // return the length of the entire array
        else return oddArrayDivider;  // Otherwise, the method return the length of the longest subarray
    } // end of what method

    /**
     * This method accepts an array filled with positive integers, and checks
     * If there is a valid route to the last cell.
     * A valid route in the array is a route that starts at index 0 and advances in the array 
     * a number of steps to the right or left according to the value in the cell.
     * @param a - int arry
     * @return true if there is a valid route in the array, and false otherwise.
     */
    public static boolean isWay(int[] a)
    {
        return isWay(a, 0);
    }

    private static boolean isWay(int[] a, int index)

    {
        if (index == a.length-1) 
            return true; 

        if (index < 0 || index >= a.length || a[index] <= 0) // Checking cell validity
            return false; 

        int temp = a[index]; // Saving the value in favor of returning the original array
        a[index] = 0; // Mark the cell as used

        boolean goRight = isWay(a, index + temp); // Checking the road on the right
        boolean goLeft = isWay(a, index - temp);  // Checking the road on the left

        a[index] = temp; // Returning the original value of the cell
        return goRight || goLeft;
    } // end of isWay method

    /**
     * This method returns the length of the shortest path in a matrix from the selected cell to the cell containing -1.
     * You can move between cells only if the designated cell is the same or larger by 1 or smaller by 2.
     * @param drm - The matrix
     * @param i,j - The starting cell in the matrix
     * @return shortest path in a matrix from the selected cell to the cell containing -1.
     */
    public static int prince(int[][] drm, int i, int j)
    {
        if (drm[i][j] == -1) // Checking whether the selected cell is the final cell
            return 1;

        // In this part we will check the validity of the cells that are on the sides of the received cell.
        // For each adjacent cell we will check whether it is part of the matrix and whether it is not used (contains -2)
        // After that we will check whether the cell next to the tested cell is the target cell

        boolean up = (i-1>=0 && drm[i-1][j] != -2);                                                                                                                 
        if (up && drm[i-1][j] == -1)                              
            return 2;                                             

        boolean down = (i+1<drm.length && drm[i+1][j] != -2);                                                                                     
        if (down && drm[i+1][j] == -1)                                                                                      
            return 2;                                                                                      

        boolean right = (j+1<drm[0].length && drm[i][j+1] != -2);                                                                                     
        if (right && drm[i][j+1] == -1)                                                                                      
            return 2;                                                                                      

        boolean left = (j-1>=0 && drm[i][j-1] != -2);                           
        if (left && drm[i][j-1] == -1)                                   
            return 2;                                                      

        // End of validity check

        if (up != true && down != true && right != true && left != true) // If all four adjacent cells are invalid, we will return -1 because there is no way to solve it
            return -1;                                                   

        int temp = drm[i][j]; // Saving the cell value in a temporary variable so that we can return its original value later 
        drm[i][j] = -2; // Mark the cell as used 

        // With these variables we will check the length of the routes in each of the directions
        int sumOfUp = 0;
        int sumOfDown = 0;
        int sumOfRight = 0;
        int sumOfLeft = 0;

        // In this section we will check whether the existing number in the nearest legal cell allows the continuation of the route. 
        // As mentioned this case is only when the adjacent cell is the same, 2 lower or 1 greater than the number in the received cell.
        // If one of the conditions is met, we will run a recursion on the next cell and add 1 to the sum parameter, the length of the route.

        if (up && (drm[i-1][j] == temp || drm[i-1][j]+1 == temp || drm[i-1][j]+2 == temp || drm[i-1][j]-1 == temp))
        {
            sumOfUp = 1 + prince(drm,i-1,j);
        }
        if (down && (drm[i+1][j] == temp || drm[i+1][j]+1 == temp || drm[i+1][j]+2 == temp || drm[i+1][j]-1 == temp))
        {
            sumOfDown = 1 + prince(drm,i+1,j);
        }
        if (right && (drm[i][j+1] == temp || drm[i][j+1]+1 == temp || drm[i][j+1]+2 == temp || drm[i][j+1]-1 == temp))
        {
            sumOfRight = 1 + prince(drm,i,j+1);
        }
        if (left && (drm[i][j-1] == temp || drm[i][j-1]+1 == temp || drm[i][j-1]+2 == temp || drm[i][j-1]-1 == temp))
        {
            sumOfLeft = 1 + prince(drm,i,j-1);
        }
        // End of validity check

        if (sumOfUp == 0 && sumOfDown== 0 && sumOfRight== 0 && sumOfLeft == 0) // Checking whether a valid route does not exist
            return -1; 

        // In order to avoid a situation where 0 returns as the minimum number, we will specify that any SUM defined as 0 will be redefined as Integer.MAX_VALUE
        sumOfUp = (sumOfUp > 0) ? sumOfUp : Integer.MAX_VALUE;
        sumOfDown = (sumOfDown > 0) ? sumOfDown : Integer.MAX_VALUE;
        sumOfRight = (sumOfRight > 0) ? sumOfRight : Integer.MAX_VALUE;
        sumOfLeft = (sumOfLeft > 0) ? sumOfLeft : Integer.MAX_VALUE;

        drm[i][j] = temp; // Returning the original value of the cell

        return Math.min(Math.min(sumOfUp, sumOfDown), Math.min(sumOfRight,sumOfLeft)); // Returning the shortest route out of all the options

    } // end of prince method

}// end of method main
