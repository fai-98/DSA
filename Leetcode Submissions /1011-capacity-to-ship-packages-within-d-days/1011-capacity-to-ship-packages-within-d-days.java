class Solution {
    public int shipWithinDays(int[] arr, int days) {
        int lo = -(int)1e9, hi = 0;

        for(int i=0; i<arr.length; i++){
            lo = Math.max(arr[i],lo);
            hi += arr[i];
        }

        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(isPos(arr,days,mid)) hi = mid;
            else lo = mid + 1;
        }

        return hi;
    }

    public boolean isPos(int[] arr, int days, int cap){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            if(sum + arr[i] > cap){
                days--;
                sum = 0;
            }
            sum += arr[i];
        }

        return days > 0;
    }
}


//max cap = sum(arr)
//min cap = max(arr)

//serach space min ... max 

// random cap using B.S and test if you can ship within d days 
// and minimize it if true, else look for higher cap