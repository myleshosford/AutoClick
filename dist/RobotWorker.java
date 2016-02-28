/* Decompiled RobotWorker */
import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

public class RobotWorker extends SwingWorker
{

    public void setProfile(Profile theProfile)
    {
        profile = theProfile;
    }

    public void startWorker()
    {
        running = true;
    }

    public void stopWorker()
    {
        running = false;
        System.out.println("Stoped Worker");
    }

    public RobotWorker()
    {
        running = true;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize = new Rectangle(dim);
        timer = new Timer();
        tt = new  Object()     /* anonymous class not found */
    class _anm1 {}

;
        try
        {
            robot = new Robot();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
        }
    }

    public Void doInBackground()
    {
        while(running) 
            doActions();
        return null;
    }

    public void doActions()
    {
        int actionSize = profile.getModelActions().getSize();
        System.out.println((new StringBuilder()).append("Starting profile with ").append(actionSize).append(" actions.").toString());
        for(int i = 0; i < actionSize; i++)
        {
            if(isCancelled())
                continue;
            Action action = (Action)profile.getModelActions().get(i);
            if(action.getType().equalsIgnoreCase("screenshot"))
            {
                java.awt.image.BufferedImage image = robot.createScreenCapture(screenSize);
                try
                {
                    String currentTime = String.valueOf(System.currentTimeMillis());
                    File outputFile = new File((new StringBuilder()).append(currentTime).append(".png").toString());
                    ImageIO.write(image, "png", outputFile);
                }
                catch(Exception e)
                {
                    System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
                }
            }
            if(action.getType().equalsIgnoreCase("move"))
                robot.mouseMove(action.getX(), action.getY());
            if(action.getType().equalsIgnoreCase("lclick"))
            {
                robot.mousePress(16);
                robot.mouseRelease(16);
            }
            if(action.getType().equalsIgnoreCase("rclick"))
            {
                robot.mousePress(8);
                robot.mouseRelease(8);
            }
            try
            {
                Thread.sleep(profile.getDelay());
            }
            catch(Exception e)
            {
                System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
            }
        }

        System.out.println("Finished actions");
    }

    public volatile Object doInBackground()
        throws Exception
    {
        return doInBackground();
    }

    private Profile profile;
    private Robot robot;
    private Dimension dim;
    private Rectangle screenSize;
    private Timer timer;
    private TimerTask tt;
    private boolean running;
}