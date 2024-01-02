package hackerRank;

public class JumpingOnTheClouds {
    public static void main(String[] args) {
        int[] clouds = {0, 0, 1, 0, 1, 0, 0,0,0}; // İki atladığı yerde 1 yok ise, 1 atlama yapar

        int jumps=0;
        int i=0;
        while (i<clouds.length-1){
            if((clouds.length == (i+2)) || clouds[i+2] == 1){
                i++;
            }else {
                i+=2;
            }
            jumps++;
        }
        System.out.println(jumps);
    }

}
