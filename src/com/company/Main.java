package com.company;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String fileArray[] = new String[]{"file1.txt", "file2.txt", "file3.txt"};
        for(int i = 0; i < fileArray.length; i++){
            Thread::start();
        }

//        try{
//            URL url1 = new URL("https://gist.githubusercontent.com/anonymous/356beefaaa42e07873ef6f61b21b160b/raw/c5dacb906d5f3ffc8d13e767594f661cfaeb43d9/file1.txt");
//            URL url2 = new URL("https://gist.githubusercontent.com/anonymous/35505b6919a7d08f2eb514928357e994/raw/aaa2721fe2c971609dd93aa8a133dc3d10ad13dd/file2.txt");
//            URL url3 = new URL( "https://gist.githubusercontent.com/anonymous/0bc66de5c50b1858ec23294f86f4dcd0/raw/39ed6ef1a7d384d7ec37dec2a11aedeb2114f005/file3.txt");
//            new Thread(new SumCalculating(url1)).start();
//            new Thread(new SumCalculating(url2)).start();
//            new Thread(new SumCalculating(url3)).start();
//        }catch (MalformedURLException e){
//            System.out.println("wrong url");
//        }

    }
}
