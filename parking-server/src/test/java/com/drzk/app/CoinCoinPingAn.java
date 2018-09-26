package com.drzk.app;
import java.util.Scanner;

public class CoinCoinPingAn {
    public static void main(String[] args) {
        // 从命令行读取数据
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String[] s2 = s1.split("\\=|,");
        int n = Integer.parseInt(s2[1]); //总的钱数目
        int[] num = new int[6];   //这是每个钱的数目
        for(int i = 3; i < s2.length; i = i + 2) {
            num[i/2 - 1] = Integer.parseInt(s2[i]);
        }
        int times = coinCoinPingAn(n, num);  //总的拼凑次数
        System.out.println(times);
    }
    private static int coinCoinPingAn(int n, int[] num) {
        int[] coinValue = {1, 5, 10, 20, 50, 100};
        int m = coinValue.length - 1;
        int times = coinCoinPingAn(n, coinValue, m, num);
        return times;
    }
    private static int coinCoinPingAn(int n, int[] coinValue, int m, int[] num) {
        if(n == 0){
            return 1;
        }
        if(n < 0) {
            return 0;
        }
        if(m < 0) {
            return 0;
        }
        int times_temp = 0;
        for(int i = 0; i <= num[m]; i++) {
            times_temp = times_temp + coinCoinPingAn(n - i*coinValue[m], coinValue, m -1, num);
        }
        return times_temp;
    }
}