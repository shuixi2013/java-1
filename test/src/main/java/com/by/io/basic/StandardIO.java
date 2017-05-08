package by.io.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandardIO {  
	  
    public static void main(String[] args) throws IOException {  
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
        String s;  
        while ((s = in.readLine()) != null && s.length() != 0)  
            System.out.println(s);  
    }  
    
//    public static void main(String[] args) throws IOException {  
//        Scanner in = new Scanner(System.in);  
//        String s;  
//        while((s = in.next()) != null && s.length() != 0){  
//            System.out.println(s);  
//        }  
//    } 
}  