## Problem2: Bikes in a Campus (https://leetcode.com/problems/campus-bikes/)
Why this is a greedy problem?
The Campus Bikes problem is considered greedy because at each step, we make the locally optimal choice (assigning the closest 
available bike to an unassigned worker) without reconsidering previous choices.
Greedy Choice:
We always assign the bike to the worker who has the smallest distance to it without looking ahead to see if a better assignment is 
possible in the future.
Once a worker is assigned a bike, we do not backtrack or change assignments.

Time Complexity : As in this question we are using a TreeMap so anything u do with it it sorts so it will take logn.
Time Complexity : O(mn logmn)
Space Complexity : O(mn) -> TreeMap

Steps:
First we will find out the absolute manhattanDistance from each worker to each bike.
Second, We will store it in a treemap because we need a sorted order. In treemap the distance will be the key and int[] will be a value 
which will have worker and bike which will have the distance
Third, We will iterate through that map and take each pair and assign the bike as we will be iterating through the shortest distance to longest
Also while doing this we will check if the bike is already assigned to a worker or not and if not then only we will assign it
import java.util.*;
class BikesInACampus{
    private int[] assignBikes(int[][] workers, int[][] bikes){
        if(bikes==null || workers==null || bikes.length==0 || workers.length==0){
            return new int[]{};
        }
        TreeMap<Integer,List<int[]>> tree = new TreeMap<>();
        boolean[] bikeStatus = new boolean[bikes.length];
        int[] result = new int[workers.length];
        Arrays.fill(result,-1);
        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                int distance = manhattanDistance(workers[i],bikes[j]);
                if(!tree.containsKey(distance)){
                    tree.put(distance,new ArrayList<int[]>());
                }
                tree.get(distance).add(new int[]{i,j});
            }
        }
        
        for(Map.Entry<Integer,List<int[]>> entry : tree.entrySet()){
            int distance = entry.getKey();
            List<int[]> list = entry.getValue();
            for(int[] pair : list){
                if(result[pair[0]]==-1){
                    if(bikeStatus[pair[1]]==false){
                        bikeStatus[pair[1]] = true;
                        result[pair[0]]=pair[1];
                    }
                }
            }
        }
        return result;

    }
    private int manhattanDistance(int[] workers, int[] bikes){
        return Math.abs(workers[0]-bikes[0])+Math.abs(workers[1]-bikes[1]);
    }
    public static void main(String[] args){
        int[][] workers = new int[][]{{0,0},{1,1},{2,0}};
        int[][] bikes = new int[][]{{1,0},{2,2},{2,1}};
        BikesInACampus bikescampus = new BikesInACampus();
        System.out.println(Arrays.toString(bikescampus.assignBikes(workers, bikes)));


    }
}

No there are multiple steps where we can optimize above problem
1. If we have already assigned all the workers all the bikes still we are fetching from map and iterating so this can be optimized 
    by maintaing a variable of size bikes array. and ones we assign we will decrement it and ones it is zero we will break it.
2. We are using a treemap which takes logn for insertion so instead we can use HashMap and we can maintain 2 variables min max which will hold 
    the min and max values of keys so that we can iterate from min upto max