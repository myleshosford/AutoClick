/* Decompiled Main$11 */
import javax.swing.AbstractListModel;

class Main$11 extends AbstractListModel
{

    public int getSize()
    {
        return strings.length;
    }

    public Object getElementAt(int i)
    {
        return strings[i];
    }

    String strings[] = {
        "Add Actions..."
    };
    final Main this$0;

    Main$11()
    {
        this$0 = Main.this;
        super();
    }
}