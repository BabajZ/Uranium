package me.alpha432.oyvey.features.modules.client.ChatLisner;

import java.io.File;
import java.util.Scanner;
public class filescaner
{

    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        File file = new File("cfg.txt");
        Scanner sc = new Scanner(file);
        String cfg = new String(String.valueOf(sc));
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }

}