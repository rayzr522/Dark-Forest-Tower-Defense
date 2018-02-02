package me.rayzr522.towerdefense.level;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LevelLoader {

    public static StringBuffer[] load(String location) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(LevelLoader.class.getResourceAsStream(location)));
            StringBuffer[] content;

            String nextLine = br.readLine();
            int pos = 0;
            while (nextLine != null) {

                nextLine = br.readLine();
                pos++;

            }

            content = new StringBuffer[pos];

            br = new BufferedReader(new InputStreamReader(LevelLoader.class.getResourceAsStream(location)));

            nextLine = br.readLine();
            pos = 0;
            while (nextLine != null) {

                content[pos] = new StringBuffer(nextLine);
                nextLine = br.readLine();
                pos++;

            }

            return content;

        } catch (Exception e) {

            System.err.println("Couldn't load level! Error:\n");
            e.printStackTrace();

            return null;

        }

    }
}
