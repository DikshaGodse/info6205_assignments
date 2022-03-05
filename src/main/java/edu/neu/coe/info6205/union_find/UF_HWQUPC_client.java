package edu.neu.coe.info6205.union_find;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class UF_HWQUPC_client {
    public static void main(String args[]) throws IOException {
        int input=0;
        System.out.println("*****please enter -1 to exit or positive integer to continue*****");
        while(input!=-1) {
            int initVal=100; //number of times to test to take average
            System.out.print("Input : Number of objects/sites (n): ");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
            input = Integer.parseInt(bufReader.readLine());
            if (input == -1) {
                System.exit(1);
            }
            int totRandomConn=0; // total of all connection and take there average
            for (int i = 0; i < initVal; i++) {
                totRandomConn += count(input);
            }
            System.out.println(totRandomConn/initVal + " Random Pairs (m) generated for " + input + " objects (n). ");
        }
    }

    private static int count(int input) {
        int randomPair=0;

        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(input,true);
        Random randomObj = new Random();

        while(uf_hwqupc.components() > 1) {
            int pVal = randomObj.nextInt(input);
            int qVal = randomObj.nextInt(input);
            if(!uf_hwqupc.connected(pVal,qVal)) {
                uf_hwqupc.union(pVal, qVal);
            }
            randomPair++;
        }
        return randomPair;
    }
}
