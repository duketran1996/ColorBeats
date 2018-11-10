import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
import com.google.common.collect.*;
import java.util.List;

public class imageColor {

    //Resize the picture in case the user put in a high quality picture that get to thousand pixels
    public static void resize(String imgPath, String reimgPath, int w, int h, String type) {
        try {
            BufferedImage img = ImageIO.read(new File(imgPath));
            BufferedImage reimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D ob = reimg.createGraphics();
            ob.drawImage(img, 0, 0, w, h, null);
            ob.dispose();
            ImageIO.write(reimg, type, new File(reimgPath));
        }
        catch(IOException e) {
            System.out.println("File does not exist\n");
        }
    }

    //Get the value of rgb in the picture
    public static List<Color> valRGB(String filepath) {
        List<Color> rgbArr = null;
        try{
            BufferedImage img = ImageIO.read(new File(filepath));
            int height = img.getHeight();
            int width = img.getWidth();
            int i = 0;
            rgbArr = new ArrayList<>();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int r = img.getRGB(x,y);
                    Color val = new Color(r,true);
                    if (!rgbArr.contains(val)) {
                        rgbArr.add(val);
                        i++;
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println("File does not exist\n");
        }
        return rgbArr;
    }

    //Multipmap to store the color and its name. There are different rgb with for the same name so multimap can put one name that has many rgb values.
    public static Multimap<String, String> imgData(String image, int w, int h) throws IOException
    {
        imageColor imgobj = new imageColor();
        parseJson colorName = new parseJson();
        String rgbVal;
        String nameVal;

        String reImage = "assets/image/re.png";
        imgobj.resize(image,reImage,w,h,"png");

        List<Color> rgb = imgobj.valRGB(reImage);
        SortedSetMultimap<String,String> dict = TreeMultimap.create();

        for (Color out:rgb) {
            rgbVal = out.getRed()+","+out.getGreen()+","+out.getBlue();

            //Get name from class parseJson.
            nameVal = colorName.getName(rgbVal);

            dict.put(nameVal,rgbVal);
        }

        //Delete the file after using it.
        File file = new File("assets/image/re.png");

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        return dict;
    }

    //Get the color types based on the HSL value of the color.
    public String getCatergory(String rgb) throws IOException
    {
        String hsl;
        Integer h;
        Integer s;
        Integer l;
        String[] convertHSLToInt;
        parseJson obj = new parseJson();
        hsl = obj.getHSL(rgb);
        convertHSLToInt = hsl.split(",");
        h = Integer.parseInt(convertHSLToInt[0]);
        s = Integer.parseInt(convertHSLToInt[1]);
        l = Integer.parseInt(convertHSLToInt[2]);

        if (l < 20)
            return "Black";
        if (l > 80)
            return "White";
        if (s < 25)
            return "Gray";
        if (h < 30)
            return "Red";
        if (h < 90)
            return "Yellow";
        if (h < 150)
            return "Green";
        if (h < 210)
            return "Cyan";
        if (h < 270)
            return "Blue";
        if (h < 330)
            return "Magenta";
        return "Red";
    }
}

