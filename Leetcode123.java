class Solution {
    public int maxProfit(int[] prices) {
        int K = 2;
        int[] p = prices;
        int n = p.length;
        if(n <= 1) return(0);
        int[] lmin = new int[n];
        int[] ltran = new int[n];
        int[] rtran = new int[n];
        int[] rmax = new int[n];
        lmin[0] = p[0];
        ltran[0] = 0;
        rtran[n - 1] = 0;
        rmax[n - 1] = p[n - 1];
        for(int i = 1; i < n; i++){
            ltran[i] = Math.max(p[i] - lmin[i - 1], ltran[i - 1]);
            lmin[i] = Math.min(lmin[i - 1], p[i]);
            //lmax[i] = Math.max(lmax[i - 1], p[i]);
            int j = n - i - 1;
            rtran[j] = Math.max(rmax[j + 1] - p[j], rtran[j + 1]) ;
            //rmin[j] = Math.min(rmin[j + 1], p[i]);
            rmax[j] = Math.max(rmax[j + 1], p[j]);
        }
        int res = 0;
        for(int i = 0; i < n - 1; i++){
            res = Math.max(ltran[i] + rtran[i], res);
        }
        //System.out.println(Arrays.toString(ltran));
        //System.out.println(Arrays.toString(rtran));
        return(res);
    }
}

