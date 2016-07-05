package androidgames.fishcollection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import framework.base.FileIO;

/**
 * Created by SolarisD on 2016/05/19.
 */
public class Settings {
    public static boolean soundEnabled = true;
    public final static String file = "save.FishCollection";

    public static void save(FileIO files){
        BufferedWriter out = null;
        try{
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(file)));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            out.write(Long.toString(States.cashe));
            out.write("\n");
            for(int fGuide_i = 0;fGuide_i < 16; fGuide_i ++) {
                out.write(Long.toString(States.fishGuide[fGuide_i]));
                out.write("\n");
            }
        }catch (IOException e){
            // デフォルト設定があるのでエラー無視
        }finally{
            try {
                if (out != null)
                    out.close();
            }catch (IOException e){
            }
        }
    }

    public static void load(FileIO files){
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(files.readFile(file)));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            States.cashe = Integer.parseInt(in.readLine());
            for(int fGuide_i = 0;fGuide_i < 16; fGuide_i ++) {
                String nullCheck = in.readLine();
           //     if(nullCheck != null)
           //         States.fishGuide[fGuide_i] = Integer.parseInt(in.readLine());
            }
        }catch (IOException e){
            // デフォルト設定があるのでエラー無視
        }catch (NumberFormatException e){
            // 同上
        }finally{
            try {
                if (in != null)
                    in.close();
            }catch (IOException e){
            }
        }
    }

    public static void dataClear(FileIO files){
        BufferedWriter clear = null;
        try{
            clear = new BufferedWriter(new OutputStreamWriter(files.writeFile(file)));
            clear.write(Boolean.toString(soundEnabled));
            clear.write("\n");
            clear.write(Long.toString(0));
            clear.write("\n");
        }catch (IOException e){
            // デフォルト設定があるのでエラー無視
        }finally{
            try {
                if (clear != null)
                    clear.close();
            }catch (IOException e){
            }
        }
    }
}
