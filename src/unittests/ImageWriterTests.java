package unittests;

import org.junit.jupiter.api.Test;
import renderer.ImageWriter;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageWriterTests {
    @Test
    /**
     * create a picture with white grid on black background
     */
    void ViewPlaneTest(){
        ImageWriter Image=new ImageWriter("viewPlane",10,10,500,500);
        for (int i = 0; i<500;i++)
            for(int j=0;j<500;j+=50)
            {
                Image.writePixel(i, j, Color.white);
                Image.writePixel(j, i, Color.white);
            }
        Image.writeToimage();
    }
}
