package fr.aurelien.worm_project.Core;

public class Logger
{
 
    public static void log(Object ... args)
    {
        String text = "";
        
        for(Object e: args)
        {
            if(e != null)
                text = text+e.toString() + " ";
        }
        System.out.println(text);
    }
}

