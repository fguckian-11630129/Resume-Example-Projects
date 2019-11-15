/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationfooling;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author MartaandFintan
 */
public class RunningMedian {
    
    
    
    public static void main (String[] args){
        double[] b = {2,3,4,5,6,7,8};
        RunningMedian r = new RunningMedian(b);
    }
    
    private RunningMedian(double[] b) {
        /*
         * Write your code here.
         */
        

         //first I need to create max and min heaps
         PriorityQueue<Double> minHeap = new PriorityQueue<>();
         //Comp c = new Comp();
         PriorityQueue<Double> maxHeap = new PriorityQueue<>(new Comp());
         
         //make a little array for use
         
         
         //for each element in b compare with previous median. If greate than
         //add to the min heap and if less than add to the max heap
         double median = 0;
         double[] ret = new double[b.length];
         int index = 0;
         
         for(double e: b){
             if(e>median){
                 minHeap.add(e);
             }
             else{
                 maxHeap.add(e);
             }
             
             //make sure the heaps are the same size
             //if the max heap is more than 1 larger
             if(maxHeap.size()>minHeap.size()+1){
                 //then pop the top off maxHeap and push onto minHeap
                 minHeap.add(maxHeap.poll());                 
             }
             //or if the reverse is true make similar correction
             else if(minHeap.size()>maxHeap.size()+1){
                 maxHeap.add(minHeap.poll());
             }
             
             //now the heaps are either the same size or not.
             //if they are take the average
             if(minHeap.size()==maxHeap.size()){
                 ret[index] = Double.valueOf((minHeap.peek()+maxHeap.peek())/2);
                
             }
             //other wise return the top of the larger list
             else if(minHeap.size()>maxHeap.size()){
                 ret[index] = minHeap.peek();
             }
             else{
                 ret[index] = maxHeap.peek();
             }
             
             index++;
         }
         
         
         
         for(double e: ret){
             System.out.println(e);
         };
    }

    class Comp implements Comparator<Double>{
        
        @Override
        public int compare(Double a, Double b){
            if(a>b){
                return -1;
            }
            else if(a == b){
                return 0;
            }
            else{
                return 1;
            }
        }
    }
    
}
