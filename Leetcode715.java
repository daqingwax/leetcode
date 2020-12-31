//takes linear time, can be improved with treemap with floorKey functions/submap etc.. will do it again in the future.

class RangeModule {
    TreeSet<int[]> set;
    public RangeModule() {
        this.set = new TreeSet<>((a, b) -> a[0] - b[0]);
    }
    
    public void addRange(int left, int right) {
        if(this.set.isEmpty()) set.add(new int[]{left, right});
        else{
            //merge list
            List<int[]> secList = new ArrayList<>();
            for(int[] intv: this.set){
                if(intv[0] <= right && intv[1] >= left) secList.add(intv);
            }
            int start = left, end = right;
            for(int[] intv: secList){
                start = Math.min(start, intv[0]);
                end = Math.max(end, intv[1]);
                this.set.remove(intv);
            }
            this.set.add(new int[]{start, end});
        }
    }
    
    public boolean queryRange(int left, int right) {
        for(int[] intv: this.set){
            if(intv[0] <= left && intv[1] >= right) return(true);
        }
        return(false);
    }
    
    public void removeRange(int left, int right) {
        if(this.set.isEmpty()) return;
        else{
            //split list
            List<int[]> secList = new ArrayList<>();
            for(int[] intv: this.set){
                if(intv[0] < right && intv[1] > left) secList.add(intv);
            }
            int start = left, end = right;
            for(int[] intv: secList){
                this.set.remove(intv);
                //range inside, split to two
                if(intv[0] < left){
                    int[] intv1 = new int[]{intv[0], left};
                    this.set.add(intv1);
                }
                if(intv[1] > right){
                    int[] intv2 = new int[]{right, intv[1]};
                    this.set.add(intv2);
                }

            }
        }        
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
