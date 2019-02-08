package com.tripco.t11.planner;

public class Optimization extends Thread {
    private int [][] Dtable;
    private int start;
    private String optLevel;
    public int[] results;
    public int totalDist;



    public Optimization (int [][] table, int start,String optlevel){
        Dtable = table;
        this.start = start;
        optLevel = optlevel;

        results = new int[Dtable.length];
    }


    public void run(){
        pickOpt();

    }
    //picks the correct find dist method
    private void pickOpt(){

        fillArray(results);
        results[0] = start;
        results[start] = 0;


        if("short".equals(optLevel)){
            NN();
        }
        else if("shorter".equals(optLevel)){
            setTwoOpt();
        }
        else if("shortest".equals(optLevel)){
            //System.out.println("starts 3Opt");
            setThreeOpt();
        }

        totalDist = findLength(results,Dtable);
        //System.out.println("thread #"+start+" is done");
    }


    //NN section

    private void NN(){
        int nearest = 1;//set the first nearest
        int curbest = Integer.MAX_VALUE;//set the best as the first option
        int totaldist = 0;
        int hold = 0;
        //0 in P is already "correct" find the thing that needs to go in the next place and repeat place
        for(int i = 1; i < results.length-1;i++){//for each location

            for(int k = i; k < results.length;k++){//find the closest place
                //find the nearest
                hold =  Dtable[results[i-1]][results[k]];
                if(curbest > hold){
                    curbest = hold;
                    nearest = k;//nearest to i-1
                }
            }
            //swap the best option into the slot
            hold = results[nearest];//hold the new nearest
            results[nearest] = results[i];//put the i + 1 in nearest
            results[i] = hold;//put nearest in i + 1
            totaldist += curbest; //add the new dist to the total
            curbest = Dtable[results[i]][results[i+1]];//sloppy reset of value
            nearest = i + 1;
        }
        //add the distance of the first to last place to complete the circle
        totaldist += curbest;//(length-1 to length-2)
        totaldist += Dtable[results[0]][results[results.length-1]];
        //System.out.println(totaldist);
        totalDist = totaldist;
    }


    //two-Opt section

    private void setTwoOpt(){

        NN();//first run a NN on the trip to get a mostly optimized step


        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 1; i < results.length - 1; i++) {//i probably starts at 0 if this is broken try that
                for (int k = i + 1; k < results.length; k++){
                    int change = findChange(results, i, k,Dtable);
                    if (change < 0) {
                        segmentReverse(results, i , k);
                        improvement = true;
                    }
                }
            }
        }
    }


    //three-Opt section
    private void setThreeOpt(){
        int delta = 0;
        NN();//first run a NN on the trip to get a mostly optimized step
        //System.out.println("outside while loop");
        int curDist = findLength(results, Dtable);

        boolean improvement = true;
        boolean test = false;
        while (improvement) {
            //System.out.println("inside while loop");
            improvement = false;
            //test = false;
            //int count = 0;
            delta = findLength(results, Dtable);
            for (int i = 0; i < results.length - 2; i++) {
                //System.out.println("inside i loop");
                for (int j = i + 1; j < results.length - 1; j++) {//indexing is likely wrong
                    //System.out.println("inside j loop");
                    for (int k = j + 1; k < results.length; k++) {
                        //System.out.println("inside k loop");
                        if(reverse_segment_if_better(results, i, j, k, Dtable)){
                            //test = true;
                            //count++; //these variables were used for testing
                            //improvement = true;
                        }

                    }
                }
            }
            delta = delta - findLength(results, Dtable);
            if(delta > 0){
                improvement = true;
            }
            //else if(test){
                //System.out.println("test is true, but delta <= 0"); //executes occasionally (not on every case)
                //System.out.println("Count: "+count);
        /*
        reverse_segment_if_better() did something, but not something to improve delta
        reverse_segment_if_better executed multiple times, and didn't improve on the total length of trip
        I'm guessing because it did something, then undid it because it was faster from each of their
          perspectives. How could that happen?
         */
            //}
        }
    }



    //helper methods


    public void segmentReverse(int[] loc, int i, int k){
        while (i < k){
            int temp = loc[k];
            loc[k] = loc[i];
            loc[i] = temp;
            k--;i++;
        }
    }


    private int findChange(int[] loc,int i, int k,int[][] Dtable){
        int size = loc.length;//k+1 is out of bounds so i just point it back at the start
        int orig = Dtable[loc[i]][loc[i-1]] + Dtable[loc[k]][loc[(k+1)%size]];
        int mod = Dtable[loc[i]][loc[(k+1)%size]] + Dtable[loc[k]][loc[i-1]];
        return mod - orig;//return the difference between
    }


    public void fillArray(int[] array){
        for(int i = 0;i < array.length;i++){
            array[i] = i;
        }
    }


    private int findLength(int[] loc,int[][] Dtable){
        int size = loc.length;
        int length = 0;
        for(int i = 0; i < loc.length;i++){
            length += Dtable[loc[i]][loc[(i+1)%size]];
        }
        //length += Dtable[loc[0]][loc[loc.length-1]];
        return length;
    }


    public boolean reverse_segment_if_better(int[] loc,int i,int j,int k,int [][] Dtable){

        int A = loc[i];
        int B = loc[i+1];
        int C = loc[j];
        int D = loc[j+1];
        int E = loc[k];
        int F = loc[(k+1)%loc.length];

        int[] c = new int[8];

        c[0] = Dtable[A][B] + Dtable[C][D] + Dtable[E][F];//original

        c[1] = Dtable[A][C] + Dtable[B][D] + Dtable[E][F];//2opt
        c[2] = Dtable[A][B] + Dtable[C][E] + Dtable[D][F];
        c[3] = Dtable[A][E] + Dtable[D][C] + Dtable[B][F];

        c[4] = Dtable[A][C] + Dtable[B][E] + Dtable[D][F]; //3opt
        c[5] = Dtable[A][D] + Dtable[E][C] + Dtable[B][F];
        c[6] = Dtable[A][E] + Dtable[D][B] + Dtable[C][F];
        c[7] = Dtable[A][D] + Dtable[E][B] + Dtable[C][F];

        int holder = c[0];
        int index = 0;
        for(int iter = 1; iter < c.length ; iter ++){
            if(c[iter] <= holder) {
                holder = c[iter];
                index = iter;
            }
        }


        if(index > 3){//three Opt
            if(index == 4){//two two opt swaps i+1 to j and j+1 to k
                segmentReverse(loc,i+1,j);
                segmentReverse(loc,j+1,k);
                return true;
            }
            if(index == 5){//segment reverse on J+1 to k and then swaping on i j k
                segmentReverse(loc,j+1,k);
                swapSection(loc,i,j,k);//come back later posible bug
                return true;
            }
            if(index == 6){//segment reverse on i+1 to j and then swaping on i j k
                segmentReverse(loc,i+1,j);
                swapSection(loc,i, j, k);
                return true;
            }
            if(index == 7){
                swapSection(loc,i,j,k);
                return true;
            }

        }
        else if(index > 0){//two Opt
            if(index == 1){
                segmentReverse(loc,i+1,j);
                return true;
            }
            if(index == 2){
                segmentReverse(loc,j+1,k);
                return true;
            }
            if(index == 3){
                segmentReverse(loc,i+1,k);
                return true;
            }
        }
        return false;
    }


    public void swapSection(int[] loc,int i, int j, int k){
        int[] holder = new int[k-i];
        //System.out.println("in swap");
        int begining = i+1;
        int end = k;//these are inclusive

        int sec1 = j - i;
        int sec2 = k - j;

        //add in the i -  j values
        for(int x = 0 ; x < sec1; x++){
            //add the values from i to j in loc to the
            // k-(j-i) to k locations in
            holder[x + end - j] = loc[x + begining];

        }
        //add in the j - k values
        for(int x = 0; x < sec2; x++){
            //add the values from j to k in loc to the
            //0 to sec2 vals in holder
            holder[x] = loc[j + x + 1];//the Plus one makes it not include j
        }
        //now fill the original back in
        for(int x = 0; x < holder.length; x++){
            loc[begining + x] = holder[x];
        }
    }





}
