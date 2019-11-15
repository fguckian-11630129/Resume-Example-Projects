/*
 * This proram cheack weathers two numbers in an array can be added together to make 
* the target number
 */

package javaapplicationfooling;

import java.util.*;

/**
 *
 * @author MartaandFintan
 */
public class CheckNums {
    
    public static void main(String[] args){
        int[] x = {4,3,2};       
        int target = 9;
        System.out.println(findMatch(x, target));
        
        HashMap<String, Boolean> hash = new HashMap<String, Boolean>();
        ArrayList<Integer> m = new ArrayList<>();
        
    
    }
    
    public static boolean findMatch(int[] x, int target){
        HashSet<Integer> hash = new HashSet<>();
        for(int e:x){
            if(hash.contains(e)){
                return true;
            }
            else{
                hash.add(target-e);
            }
        }
        return false;
    }
    
}
