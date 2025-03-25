For this question there are 2 different solutions
1. DP based 
2. Non DP based

//DP based solution
In this we will create a m*n table and add True or false. 
If the character matches the character on which we are or it is a ? then diagonal will be the answer 
If there is a * then its 0 case will be above and 1 case will be beside and there is one observation if there is a * and we find a True
then further all will be true in that row

Time Complexity : O(mn)
Space Complexity : O(mn)


class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }
        if(p==null || p.length()==0){
            return false;
        }
        int m = p.length();
        int n= s.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //rest all " " row will be false
        for(int i=1;i< m+1;i++){
            for(int j=0;j<n+1;j++){
                if(p.charAt(i-1) !='*'){
                    if((j > 0) && ((p.charAt(i-1)==s.charAt(j-1)) || (p.charAt(i-1)=='?'))){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    //now here if j=0
                    dp[i][j] = dp[i-1][j];
                    if(j > 0){
                        dp[i][j] =  dp[i][j] || dp[i][j-1];
                    }
                    
                }
            }
        }
        return dp[m][n];
    }
}

//Non DP Solution
Time Complexity : O(mn)
Space Complexity : O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }
        if(p==null || p.length()==0){
            return false;
        }
        int pp = 0;
        int sp = 0;
        int sstar=-1;
        int pstar=-1;

        while(sp < s.length()){
            if((pp< p.length()) && (s.charAt(sp)== p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }
            else if((pp< p.length()) && (p.charAt(pp) == '*')){
                sstar = sp;
                pstar = pp;
                pp++;
            }
            else if(sstar == -1){
                return false;
            }
            else{
                sp=sstar;
                pp=pstar;
                sp++;
                sstar=sp;
                pp++;
            }
        }

        while(pp<p.length()){
            if(p.charAt(pp)!='*'){
                return false;
            }
            pp++;
        }
        return true;
        
    }
}

