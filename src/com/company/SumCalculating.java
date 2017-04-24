package com.company;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by admin on 08.04.2017.
 */
public class SumCalculating implements Runnable{

    private static volatile Integer sum = 0;
    private String fileName;
    private URL url;
    private final static Object lock = new Object();
    private static volatile boolean interrupted = false;
    private String stringArr[];
    private int intArr[];

    public SumCalculating(String fileName){
        this.fileName = fileName;
    }

    public SumCalculating(URL url) {
       this.url = url;
    }

    @Override
    public void run() {

            if (url == null) {
                readFile(fileName);
            } else {
                readFromURL(url);
            }

    }

    public boolean isInteger(String inputString){
        String regularExpression = "-?\\d+";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher match = pattern.matcher(inputString);
        return match.matches();
    }

    public void readingLine(BufferedReader reader){

        String str = null;
        //String stringArr[];

        try {
            while ((str = reader.readLine()) != null) {
                if(!interrupted) {
                    stringArr = str.split(" ");
                    for (String s : stringArr)
                        //synchronized (lock) {
                        while (!interrupted) {
                            if (isInteger(s)) {

//                                synchronized (lock) {
//                                    calculateSum(Integer.parseInt(s));
//                                }
                                break;
                            } else {
                                System.out.println(" invalid value ");
                                interrupted = true;
                            }
                        }
                    // }
                }
            }
        }catch(IOException e){
            System.out.println("reading file error");
        }
    }

    public void readFile(String fileName){

        File file = new File(fileName);
        BufferedReader reader = null;
        try{
                reader = new BufferedReader(new FileReader(file));
                readingLine(reader);

        }catch(FileNotFoundException e){
            System.out.println("No such file");
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            }catch(IOException e){
                System.out.println("closing file error");
                interrupted = true;
                }
        }
    }

    private int calculateSum(Integer element){
        Stream<String> streamFromArrays = Arrays.stream(stringArr);
        streamFromArrays.filter(o = );
        //Arrays.stream(stringArr).filter((Integer.parseInt(s))-> s%2 != 0).reduce()
        //Integer sumOdd = Integer.parallelStream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);

//        if (element > 0 & element % 2 == 0)
//                    sum = sum + element;
//                    System.out.println(sum);
        return sum;
    }

    public void readFromURL(URL url){
        BufferedReader reader = null;
        try{
            URLConnection con = url.openConnection();

            reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            readingLine(reader);

        }catch(MalformedURLException e){
            System.out.println(" wrong url ");

        }catch (IOException e){
            System.out.println("connection error");

        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            }catch(IOException e){
                System.out.println("closing connection error");
                interrupted = true;
            }
        }
    }
}
