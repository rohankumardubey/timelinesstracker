package edu.ncsu.csc510.project.guilayer;

/**
 * Hello world!
 *
 */
import com.apple.eawt.Application;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
public class Main 
{
    public static void main( String[] args )
    {
	    EventQueue.invokeLater(new Runnable() {
		    public void run() {
			    try {
				    TrackerGui gui = new TrackerGui();
				    gui.setVisible(true);
                    Image im  = ImageIO.read(getClass().getClassLoader().getResource("trackericon.png"));

                    Application application = Application.getApplication();
                    application.setDockIconImage(im);
			    } catch (Exception e) {
				    e.printStackTrace();
			    }
		    }
	    });
    }
}
