//Using map
class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        if(minSize > n) return(0);
        Map<String, Integer> map = new HashMap<>();
        Map<Character, Integer> chMap = new HashMap<>();
        for(int i = 0; i < minSize; i++){
            char ch = s.charAt(i);
            chMap.put(ch, chMap.getOrDefault(ch, 0) + 1);
        }
        int res = 0;
        if(chMap.size() <= maxLetters){
            map.put(s.substring(0, minSize), 1);
            res = 1;
        }
        for(int i = 1; i <= n - minSize; i++){
            String sub = s.substring(i, i + minSize);
            char newCh = s.charAt(i + minSize - 1);
            char oldCh = s.charAt(i - 1);
            if(newCh != oldCh){
                chMap.put(newCh, chMap.getOrDefault(newCh, 0) + 1);
                chMap.put(oldCh, chMap.get(oldCh) - 1);
                if(chMap.get(oldCh) == 0) chMap.remove(oldCh);
            }
            if(chMap.size() <= maxLetters){
                map.put(sub, map.getOrDefault(sub, 0) + 1);
                res = Math.max(res, map.get(sub));
            }
            
        }
        return(res);
    }
}

//use array
/* Runtime: 29 ms, faster than 83.88% of Java online submissions for Maximum Number of Occurrences of a Substring.
Memory Usage: 41.2 MB, less than 77.78% of Java online submissions for Maximum Number of Occurrences of a Substring.
/*
class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        if(minSize > n) return(0);
        Map<String, Integer> map = new HashMap<>();
        int[] chMap = new int[26];
        int unique = 0;
        for(int i = 0; i < minSize; i++){
            int chi = s.charAt(i) - 'a';
            if(chMap[chi] == 0) unique++;
            chMap[chi]++;
        }
        int res = 0;
        if(unique <= maxLetters){
            map.put(s.substring(0, minSize), 1);
            res = 1;
        }
        for(int i = 1; i <= n - minSize; i++){
            String sub = s.substring(i, i + minSize);
            char newCh = s.charAt(i + minSize - 1);
            char oldCh = s.charAt(i - 1);
            if(newCh != oldCh){
                if(chMap[newCh - 'a'] == 0) unique++;
                chMap[newCh - 'a']++;
                chMap[oldCh - 'a']--;
                if(chMap[oldCh - 'a'] == 0) unique--;
            }
            if(unique <= maxLetters){
                map.put(sub, map.getOrDefault(sub, 0) + 1);
                res = Math.max(res, map.get(sub));
            }
            
        }
        return(res);
    }
}
