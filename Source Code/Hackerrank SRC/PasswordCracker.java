/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author MartaandFintan
 */
public class PasswordCracker {
    static Map<Integer, HashSet<String>> map = new HashMap<>();
    
    public static void main(String[] args){
        
        long start = System.currentTimeMillis();
        
        File file = new File("C:\\Users\\MartaandFintan\\Desktop\\testcases\\t25.txt"); 
        try{
        //BufferedReader br = new BufferedReader(new FileReader(file)); 
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        
        

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> passwords = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .collect(toList());

                String loginAttempt = bufferedReader.readLine();

                //String result = passwordCracker(passwords, loginAttempt);

                //bufferedWriter.write(result);
                //bufferedWriter.newLine();
                System.out.println(passwordCracker(passwords, loginAttempt));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        long end = System.currentTimeMillis()-start;
        System.out.println("Time taken: " + end);
        
        }
        catch(Exception e){
            System.out.println("COULD NOT HANDLE");
        }
        //bufferedWriter.close();
        
        //List<String> passwords = Arrays.asList(new String[] {"the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie"});
        //String login = "thecakeisaliethecakeisalieakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieathecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethethecakeisaliethecakeisaliethecakeisaliethecakeisaliethethecakeisalieakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliesalthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliesalthecakeisaliethecakeisalielieakthecakeisalieliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieeithecakeisaliethecakeisalieeithecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieiethecakeisaliethecakeisaliethecakeisaliethecakeisalieisthecakeisaliethecakeisalieiscakeakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieakcakethecakeisaliethecieiethecthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieeithecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieathecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieeithecakeisaliethecakeisaliethecakeisaliethecakeisalieacakethecakeisaliethecakeisaliesalthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalie";
        
    }
    
   /** public static void main(String[] args){
        List<String> passwords = Arrays.asList(new String[] {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"});
        String login = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(passwordCracker(passwords, login));
    }*/
      
    private static int[] addFail(StringBuilder sb, Stack<Integer> index, Stack<String> res, int x){
        
        //System.out.println("Word not found: " + sb.toString());
        map.get(index.peek()).add(res.peek());                           
       //add the current word
       map.get(x).add(sb.toString());

        sb.setLength(0);
        sb.append(res.pop());        
        int current = index.pop();
        int i = current + sb.length()-1;
        
       //System.out.println("Start building from index " + i + " with starting string " +sb.toString());
        
        return(new int[]{current, i});
        
        

           
    }
  
    private static boolean isValid(int current, StringBuilder sb){
        //if this word has already been tried return false
        if(map.containsKey(current)){
            if(map.get(current).contains(sb.toString())){
                return false;
            }
            else{
                return true;
            } 
        }
        else{
            map.put(current, new HashSet<>());
            return true;
        }
    }
    
    private static int addWord(Stack<String> res, Stack<Integer> index, int current,int i, StringBuilder sb){
        res.push(sb.toString());
        index.push(current);
        //System.out.println("Adding "+ res.peek()+" at position "+ index.peek());
        //next word will start form i+1
        current = i+1;
        sb.setLength(0);
        
        return current;
    }
    
    public static String passwordCracker(List<String> passwords, String login){
        
        StringBuilder sb = new StringBuilder();
        Stack<String> res = new Stack<>();
        Stack<Integer> index= new Stack<>();
        int current = 0;        
        //
        for(int i = 0;  i <login.length(); i++){
            //check weather we have tried this combination before going through
            //the rest of the funciton
            if(isValid(current, sb.append(login.charAt(i)))){
           
                //check the list of passwords for the current contentes of sb
                if(passwords.contains(sb.toString())){
                    //if the string is present add it to the result
                    current = addWord(res, index, current, i, sb);          
                }
                //when we get to the final index we check to make sure sb.length = 0
                //so we know we dont have unmatched words sitting on our stringBuilder
                if(i == login.length()-1){
                    //if the string builder is not size zero we have left over letters
                    //and must backtrack from the point the last word was placed
                    if(sb.length() !=0){
                        if(!res.isEmpty())
                        {
                            int[] x = addFail(sb, index, res, current);
                            current = x[0];
                            i = x[1];
                        }
                        else{
                            return "WRONG PASSWORD";
                        }

                    }
                }
            }
            //if we know adding this word at this location wont work and if
            //we have more strings on our stack, pop that string and index and
            //add it to the fail list. If the res stack is empty return "WRONG PASSWRD"
            else{
                if(!res.isEmpty()){
                    int[] x = addFail(sb, index, res, current);
                    current = x[0];
                    i = x[1];
                }
                else{
                    return "WRONG PASSWORD";
                }
                
                
            }
        }
        
        System.out.println("EXIT FOR");       
        return formatRes(sb, res);
        
    }

    private static String formatRes(StringBuilder sb, Stack<String> res){
    sb.setLength(0);
    while(!res.isEmpty())
        {
          sb.insert(0,res.pop()); 
          sb.insert(0, " ");
        }

        return sb.substring(1);

}


}






    

