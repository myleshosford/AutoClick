import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

public class RobotWorker extends SwingWorker<Void,Integer> {
    private Profile profile;
    private Robot robot;
    private Dimension dim;
    private Rectangle screenSize;
    private java.util.Timer timer;
    private java.util.TimerTask tt;
    private boolean running = true;

    public void setProfile(Profile theProfile) {
        profile = theProfile;
    }

    public void startWorker() {
        running = true;
    }

    public void stopWorker() {
        running = false;
        System.out.println("Stoped Worker");
    }


    public RobotWorker() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize = new Rectangle(dim);
        timer = new java.util.Timer();
        tt = new java.util.TimerTask() {
            public void run() {
                doActions();
            }

        };
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }


    public Void doInBackground() {                
        while (running) {
            //Check the time, if the time elapsed matches
            //frequency then call doActions();
            doActions();
        }
        return null;
    }

        public void doActions() {
            int actionSize = profile.getModelActions().getSize();
            System.out.println("Starting profile with " + actionSize + " actions.");
            for (int i=0; i<actionSize; i++) {
                if (!isCancelled()){
                    Action action = (Action)profile.getModelActions().get(i);
                //the types available are: LClick, RCLick, Move, Screenshot, Return
                if (action.getType().equalsIgnoreCase("screenshot")) {
                    BufferedImage image = robot.createScreenCapture(screenSize);
                    try {
                    String currentTime = String.valueOf(System.currentTimeMillis());
                    File outputFile = new File(currentTime + ".png");
                    ImageIO.write(image, "png", outputFile);
                } catch (Exception e) {
                   System.out.println("Error: "+e);
                }

                }
                if (action.getType().equalsIgnoreCase("move")) {
                    robot.mouseMove(action.getX(), action.getY());
                }
                if (action.getType().equalsIgnoreCase("lclick")) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }
                if (action.getType().equalsIgnoreCase("rclick")) {
                   robot.mousePress(InputEvent.BUTTON2_MASK);
                   robot.mouseRelease(InputEvent.BUTTON2_MASK);
                }
                try {
                    Thread.sleep(profile.getDelay());
            } catch (Exception e) {
                System.out.println("Error: "+e);
            }
        }
        }
        System.out.println("Finished actions");
        }

    }