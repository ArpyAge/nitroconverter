package com.joopie.ffconverter.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logging {

    private static PrintWriter furnitureWriter;
    private static PrintWriter figureWriter;
    private static PrintWriter effectWriter;
    private static PrintWriter petWriter;

    public Logging()
    {
        File furniture = new File("logging//errors//furniture.txt");
        File figure = new File("logging//errors//figure.txt");
        File effect = new File("logging//errors//effect.txt");
        File pet = new File("logging//errors//pet.txt");

        try
        {
            if (!furniture.exists())
            {
                if (!furniture.getParentFile().exists())
                {
                    furniture.getParentFile().mkdirs();
                }

                furniture.createNewFile();
            }

            if (!figure.exists())
            {
                if (!figure.getParentFile().exists())
                {
                    figure.getParentFile().mkdirs();
                }

                figure.createNewFile();
            }

            if (!effect.exists())
            {
                if (!effect.getParentFile().exists())
                {
                    effect.getParentFile().mkdirs();
                }

                effect.createNewFile();
            }

            if (!pet.exists())
            {
                if (!pet.getParentFile().exists())
                {
                    pet.getParentFile().mkdirs();
                }

                pet.createNewFile();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            furnitureWriter = new PrintWriter(new FileWriter(furniture, true));
            figureWriter = new PrintWriter(new FileWriter(figure, true));
            effectWriter = new PrintWriter(new FileWriter(effect, true));
            petWriter = new PrintWriter(new FileWriter(pet, true));
        }
        catch (IOException e)
        {
            System.out.println("[CRITICAL] FAILED TO LOAD LOGGING COMPONENT!");
        }
    }

    public void logFurniture(Object line)
    {
        this.write(furnitureWriter, line.toString());
    }

    public void logFigure(Object line)
    {
        this.write(figureWriter, line.toString());
    }

    public void logEffect(Object line)
    {
        this.write(effectWriter, line.toString());
    }

    public void logPet(Object line)
    {
        this.write(petWriter, line.toString());
    }

    private synchronized void write(PrintWriter printWriter, Object message)
    {
        if(printWriter != null && message != null)
        {
            if(message instanceof Throwable)
            {
                ((Exception) message).printStackTrace(printWriter);
            }
            else
            {
                printWriter.write("MSG: " + message.toString() + "\r\n");
            }

            printWriter.flush();
        }
    }
}
