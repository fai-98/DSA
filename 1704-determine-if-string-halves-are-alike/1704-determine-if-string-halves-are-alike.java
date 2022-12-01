class Solution {
    public boolean halvesAreAlike(String s) {
        int i=0, j = s.length()-1, a = 0, b = 0;
        
        while(i < j){
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            
            if(isVowel(ch1)) a++;
            if(isVowel(ch2)) b++;
            
            i++; j--;
        }
        
        return a == b;
    }
    
    public boolean isVowel(char ch){
        char x = Character.toLowerCase(ch);
        if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u'){
            return true;
        }
        return false;
    }
}