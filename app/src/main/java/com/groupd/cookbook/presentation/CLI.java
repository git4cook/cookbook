package com.groupd.cookbook.presentation;

/**
 * Created by siyu on 2017/6/4.
 */
import com.groupd.cookbook.objects.Recipe;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;


public class CLI // command-line interface
{
    public static BufferedReader console;
    public static String inputLine;
    public static String[] inputTokens;

    public static Recipe currentRecipe ;
    public static String recipeNumber;


    public static void run()
    {
        try
        {
            console = new BufferedReader(new InputStreamReader(System.in));
            process();
            console.close();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void process()
    {
        readLine();
        while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
                && (!inputLine.equalsIgnoreCase("quit"))
                && (!inputLine.equalsIgnoreCase("q"))
                && (!inputLine.equalsIgnoreCase("bye")))
        {	// use cntl-c or exit to exit
            inputTokens = inputLine.split("\\s+");
            parse();
            readLine();
        }
    }



    public static void readLine()
    {
        try
        {
            System.out.print(">");
            inputLine = console.readLine();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void parse()
    {
        if (inputTokens[0].equalsIgnoreCase("get"))
        {
            processGet();
        }
        else
        {
            System.out.println("Invalid command.");
        }
    }

    public static void processGet(){



    }//

}
