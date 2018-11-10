import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;

public class sound {

    //Handle the sound for different color.
    public static Mixer mix;

    public static void play (String type, Integer hue, Integer but)
    {
        String path;

        //Each different color classification will have different instrument plays
        if (type.equals("Red"))
        {
            path = "assets/sound/violin/";
        }
        else if (type.equals("Black"))
        {
            path = "assets/sound/piano/";
        }
        else if (type.equals("Blue")) {
            path = "assets/sound/flute/";
        }
        else if (type.equals("White")) {
            path = "assets/sound/trumpet/";
        }
        else if (type.equals("Green")) {
            path = "assets/sound/trombone/";
        }
        else if (type.equals("Yellow")) {
            path = "assets/sound/bass/";
        }
        else if (type.equals("Gray")) {
            path = "assets/sound/electricpiano/";
        }
        else if (type.equals("Cyan")) {
            path = "assets/sound/electricpiano2/";
        }
        else {
            path = "assets/sound/guitar/";
        }

        //Button assignment to different note of the instrument
        if (but == 0){
            path += "C.wav";
        }
        else if (but == 1){
            path += "D.wav";
        }
        else if (but == 2){
            path += "E.wav";
        }
        else if (but == 3){
            path += "F.wav";
        }
        else if (but == 4){
            path += "G.wav";
        }
        else if (but ==  5){
            path += "A.wav";
        }
        else if (but == 6){
            path += "B.wav";
        }
        else if (but == 7){
            path += "C1.wav";
        }
        else if (but == 8){
            path += "D1.wav";
        }
        else if (but == 9){
            path += "E1.wav";
        }
        else if (but == 10){
            path += "F1.wav";
        }

        //Play sound
        try {
            File soundPath = new File (path);
            AudioInputStream auStream = AudioSystem.getAudioInputStream(soundPath);
            Clip cl = AudioSystem.getClip();
            cl.open(auStream);
            cl.start();
        }
        catch(LineUnavailableException err1){
            err1.printStackTrace();
        }
        catch(UnsupportedAudioFileException err2){
            err2.printStackTrace();
        }
        catch(IOException err3){
            err3.printStackTrace();
        }
    }
}
