import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class MouseGrabber extends SwingWorker<Void,Integer> {

    private List progressListeners = new ArrayList<SwingListener>();


    public void notifyMouseMove(int theX, int theY) {
        SwingListener listener = (SwingListener) progressListeners.get(0);
        listener.notifyMouseMove(theX, theY);
    }

    public void addListener(SwingListener theListener) {
        progressListeners.add(theListener);
    }

    protected Void doInBackground() {
  
        while (true)
		{
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY();
			//System.out.println(x + ":" + y);
                        
			try
			{
                            
                                notifyMouseMove(x,y);
				
			}
			catch (Exception e)
			{
				System.out.println("Error: "+e);
                                e.printStackTrace();
			}
                        
		}       
    }

}
