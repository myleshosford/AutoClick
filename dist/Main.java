/* Decompiled Main */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URI;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;

public class Main extends JFrame
    implements SwingListener
{

    public Main()
    {
        fc = new JFileChooser();
        initComponents();
        if(SystemTray.isSupported())
        {
            java.net.URL greySkull = Main.getResource("img/whiteSkull.gif");
            java.awt.Image trayImage = Toolkit.getDefaultToolkit().getImage(greySkull);
            SystemTray tray = SystemTray.getSystemTray();
            setIconImage(trayImage);
            jFrameAbout.setIconImage(trayImage);
            jFrameTrialUp.setIconImage(trayImage);
            try
            {
                trayIcon = new TrayIcon(trayImage, "AutoClick Pro", popupMenu1);
                trayIcon.setImageAutoSize(false);
                tray.add(trayIcon);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        profileObject = new Profile();
        initList();
        File current = new File(".");
        fc.setCurrentDirectory(current);
        mouseGrabber = new MouseGrabber();
        mouseGrabber.addListener(this);
        mouseGrabber.execute();
        robot = new RobotWorker();
        try
        {
            Thread.sleep(10000L);
        }
        catch(Exception e) { }
        checkLicense();
    }

    public void runFreeTrial()
    {
        jLabelLicense.setText("Demo Version");
        jMenuItemFileLoad.setEnabled(false);
        jMenuItemFileSave.setEnabled(false);
        freeTrial = new FreeTrial();
        freeTrial.addListener(this);
        freeTrial.execute();
    }

    public void checkLicense()
    {
        try
        {
            File file = new File(".\\bin\\AutoData");
            FileInputStream f_in = new FileInputStream(file.getCanonicalPath());
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            Object obj = obj_in.readObject();
            if(obj instanceof License)
            {
                License license = (License)obj;
                if(license.getType() == 65)
                {
                    jLabelLicense.setText("Full Version");
                } else
                {
                    int runAmount = license.getRunAmount();
                    System.out.println(runAmount);
                    System.out.println(license.getType());
                    if(runAmount == 5)
                    {
                        jFrameTrialUp.pack();
                        jFrameTrialUp.setVisible(true);
                        JOptionPane _tmp = box;
                        JOptionPane.showConfirmDialog(rootPane, "Thank you for trying AutoClick Pro! Visit http://www.autoclickpro.com to license your copy now!", "Evaluation ", 2);
                        System.exit(0);
                    } else
                    {
                        license.incrementRunAmount();
                        updateLicense(license);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
            jFrameTrialUp.pack();
            jFrameTrialUp.setVisible(true);
            JOptionPane _tmp1 = box;
            JOptionPane.showConfirmDialog(rootPane, "License not found, contact admin@autoclickpro.com for assitance.", "Error", 2);
            System.exit(0);
        }
    }

    public void initList()
    {
        listModelActions = new DefaultListModel();
        jListProfile.setModel(listModelActions);
    }

    public void notifyMouseMove(int theX, int theY)
    {
        jLabelMouseXY.setText((new StringBuilder()).append(String.valueOf(theX)).append(",").append(String.valueOf(theY)).toString());
    }

    public void freeTrialUp()
    {
        jFrameTrialUp.setBounds(getBounds());
        jFrameTrialUp.pack();
        trayIcon.setPopupMenu(popupMenu2);
        jFrameTrialUp.setVisible(true);
        setVisible(false);
    }

    private void initComponents()
    {
        jFrameAbout = new JFrame();
        jLabelAboutTitle = new JLabel();
        jLabelAboutVersion = new JLabel();
        jLabelAboutCopyright = new JLabel();
        jPanelAboutInfo = new JPanel();
        jLabelAboutInfo1 = new JLabel();
        jLabelLicense = new JLabel();
        jButtonAboutOk = new JButton();
        jLabelAboutLogo = new JLabel();
        jPopupMenuProfile = new JPopupMenu();
        jMenuItemProfileDelete = new JMenuItem();
        jMenuItemProfileClear = new JMenuItem();
        popupMenu1 = new PopupMenu();
        menuItemExit = new MenuItem();
        menuItemAbout = new MenuItem();
        menuItemHide = new MenuItem();
        menuItemShow = new MenuItem();
        menuItemStop = new MenuItem();
        menuItemStart = new MenuItem();
        jFrameTrialUp = new JFrame();
        jLabelAboutTitle1 = new JLabel();
        jLabelAboutVersion1 = new JLabel();
        jLabelAboutCopyright1 = new JLabel();
        jPanelAboutInfo1 = new JPanel();
        jLabelAboutInfo2 = new JLabel();
        jLabel7 = new JLabel();
        jLabel9 = new JLabel();
        jButtonAboutOk1 = new JButton();
        jLabelAboutLogo1 = new JLabel();
        popupMenu2 = new PopupMenu();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        jPanelProfile = new JPanel();
        jScrollPane1 = new JScrollPane();
        jListProfile = new JList();
        jPanelSettings = new JPanel();
        jLabel1 = new JLabel();
        jTextFieldProfile = new JTextField();
        jLabel2 = new JLabel();
        jTextFieldFreq = new JTextField();
        jLabel3 = new JLabel();
        jTextFieldDelay = new JTextField();
        jButtonStart = new JButton();
        jButtonStealth = new JButton();
        jPanelActions = new JPanel();
        jLabel4 = new JLabel();
        jTextFieldMoveX = new JTextField();
        jTextFieldMoveY = new JTextField();
        jButtonMoveAdd = new JButton();
        jLabel5 = new JLabel();
        jTextFieldKeyStroke = new JTextField();
        jButtonKeyStrokeAdd = new JButton();
        jButtonLeftAdd = new JButton();
        jButtonRightAdd = new JButton();
        jButtonScreenShotAdd = new JButton();
        jLabelMouseXY = new JLabel();
        jButton1 = new JButton();
        jMenuBarMain = new JMenuBar();
        jMenuFile = new JMenu();
        jMenuItemFileLoad = new JMenuItem();
        jMenuItemFileSave = new JMenuItem();
        jMenuItemFileExit = new JMenuItem();
        jMenuHelp = new JMenu();
        jMenuItemHelpAbout = new JMenuItem();
        jMenuItemHelpHelp = new JMenuItem();
        jFrameAbout.setTitle("About AutoClick Pro");
        jFrameAbout.setBackground(new Color(255, 255, 255));
        jFrameAbout.setBounds(new Rectangle(200, 200, 200, 200));
        jFrameAbout.setResizable(false);
        jLabelAboutTitle.setText("AutoClick Pro");
        jLabelAboutVersion.setText("Version 1.0");
        jLabelAboutCopyright.setText("Copyright \251 2010 AutoClick Pro Software. All Rights Reserved.");
        jPanelAboutInfo.setBorder(new SoftBevelBorder(1));
        jLabelAboutInfo1.setText("Visit http://www.autoclickpro.com for other great apps.");
        jLabelLicense.setText("Free trial version.");
        GroupLayout jPanelAboutInfoLayout = new GroupLayout(jPanelAboutInfo);
        jPanelAboutInfo.setLayout(jPanelAboutInfoLayout);
        jPanelAboutInfoLayout.setHorizontalGroup(jPanelAboutInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAboutInfoLayout.createSequentialGroup().addGroup(jPanelAboutInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabelLicense).addComponent(jLabelAboutInfo1, -1, 363, 32767)).addContainerGap()));
        jPanelAboutInfoLayout.setVerticalGroup(jPanelAboutInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAboutInfoLayout.createSequentialGroup().addComponent(jLabelAboutInfo1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelLicense).addContainerGap(77, 32767)));
        jButtonAboutOk.setText("OK");
        jButtonAboutOk.addActionListener(new  Object()     /* anonymous class not found */
    class _anm1 {}

);
        jLabelAboutLogo.setIcon(new ImageIcon(getClass().getResource("/img/acp1.png")));
        jLabelAboutLogo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        GroupLayout jFrameAboutLayout = new GroupLayout(jFrameAbout.getContentPane());
        jFrameAbout.getContentPane().setLayout(jFrameAboutLayout);
        jFrameAboutLayout.setHorizontalGroup(jFrameAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jFrameAboutLayout.createSequentialGroup().addGroup(jFrameAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutLogo)).addGroup(jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutTitle)).addGroup(jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutVersion))).addContainerGap()).addGroup(jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutCopyright, -1, 321, 32767).addGap(68, 68, 68)).addGroup(jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jPanelAboutInfo, -1, -1, 32767).addContainerGap()).addGroup(jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jButtonAboutOk, -2, 82, -2).addContainerGap(307, 32767)));
        jFrameAboutLayout.setVerticalGroup(jFrameAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameAboutLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutLogo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabelAboutTitle).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelAboutVersion).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelAboutCopyright).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanelAboutInfo, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButtonAboutOk).addContainerGap(13, 32767)));
        jMenuItemProfileDelete.setText("Delete Action");
        jMenuItemProfileDelete.addActionListener(new  Object()     /* anonymous class not found */
    class _anm2 {}

);
        jPopupMenuProfile.add(jMenuItemProfileDelete);
        jMenuItemProfileClear.setText("Clear All");
        jMenuItemProfileClear.addActionListener(new  Object()     /* anonymous class not found */
    class _anm3 {}

);
        jPopupMenuProfile.add(jMenuItemProfileClear);
        popupMenu1.setLabel("popupMenu1");
        menuItemExit.setLabel("Exit");
        menuItemExit.addActionListener(new  Object()     /* anonymous class not found */
    class _anm4 {}

);
        popupMenu1.add(menuItemExit);
        menuItemAbout.setLabel("About");
        menuItemAbout.addActionListener(new  Object()     /* anonymous class not found */
    class _anm5 {}

);
        popupMenu1.add(menuItemAbout);
        popupMenu1.addSeparator();
        menuItemHide.setLabel("Hide");
        menuItemHide.addActionListener(new  Object()     /* anonymous class not found */
    class _anm6 {}

);
        popupMenu1.add(menuItemHide);
        menuItemShow.setLabel("Show");
        menuItemShow.addActionListener(new  Object()     /* anonymous class not found */
    class _anm7 {}

);
        popupMenu1.add(menuItemShow);
        popupMenu1.addSeparator();
        menuItemStop.setLabel("Stop");
        menuItemStop.addActionListener(new  Object()     /* anonymous class not found */
    class _anm8 {}

);
        popupMenu1.add(menuItemStop);
        menuItemStart.setLabel("Start");
        menuItemStart.addActionListener(new  Object()     /* anonymous class not found */
    class _anm9 {}

);
        popupMenu1.add(menuItemStart);
        jFrameTrialUp.setTitle("Thank You For Trying AutoClick Pro!");
        jFrameTrialUp.setBackground(new Color(255, 255, 255));
        jFrameTrialUp.setBounds(new Rectangle(200, 200, 200, 200));
        jFrameTrialUp.setResizable(false);
        jLabelAboutTitle1.setText("AutoClick Pro");
        jLabelAboutVersion1.setText("Version 1.0");
        jLabelAboutCopyright1.setText("Copyright \251 2010 AutoClick Pro Software. All Rights Reserved.");
        jPanelAboutInfo1.setBorder(new SoftBevelBorder(1));
        jLabelAboutInfo2.setText("Visit http://www.autoclickpro.com to license your copy now!");
        jLabel7.setText("admin@www.autoclickpro.com");
        jLabel9.setText("Thank you for trying out AutoClick Pro!");
        GroupLayout jPanelAboutInfo1Layout = new GroupLayout(jPanelAboutInfo1);
        jPanelAboutInfo1.setLayout(jPanelAboutInfo1Layout);
        jPanelAboutInfo1Layout.setHorizontalGroup(jPanelAboutInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAboutInfo1Layout.createSequentialGroup().addGroup(jPanelAboutInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7).addComponent(jLabelAboutInfo2, -1, 363, 32767).addComponent(jLabel9)).addContainerGap()));
        jPanelAboutInfo1Layout.setVerticalGroup(jPanelAboutInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAboutInfo1Layout.createSequentialGroup().addComponent(jLabel9).addGap(6, 6, 6).addComponent(jLabelAboutInfo2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel7).addContainerGap(49, 32767)));
        jButtonAboutOk1.setText("OK");
        jButtonAboutOk1.addActionListener(new  Object()     /* anonymous class not found */
    class _anm10 {}

);
        jLabelAboutLogo1.setIcon(new ImageIcon(getClass().getResource("/img/acp1.png")));
        jLabelAboutLogo1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        GroupLayout jFrameTrialUpLayout = new GroupLayout(jFrameTrialUp.getContentPane());
        jFrameTrialUp.getContentPane().setLayout(jFrameTrialUpLayout);
        jFrameTrialUpLayout.setHorizontalGroup(jFrameTrialUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jFrameTrialUpLayout.createSequentialGroup().addGroup(jFrameTrialUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutLogo1)).addGroup(jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutTitle1)).addGroup(jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutVersion1))).addContainerGap()).addGroup(jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutCopyright1, -1, 321, 32767).addGap(68, 68, 68)).addGroup(jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jPanelAboutInfo1, -1, -1, 32767).addContainerGap()).addGroup(jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jButtonAboutOk1, -2, 82, -2).addContainerGap(307, 32767)));
        jFrameTrialUpLayout.setVerticalGroup(jFrameTrialUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameTrialUpLayout.createSequentialGroup().addContainerGap().addComponent(jLabelAboutLogo1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabelAboutTitle1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelAboutVersion1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelAboutCopyright1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanelAboutInfo1, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButtonAboutOk1).addContainerGap(-1, 32767)));
        popupMenu2.setLabel("popupMenu2");
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);
        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        setDefaultCloseOperation(3);
        setTitle("AutoClick Pro");
        setBounds(new Rectangle(250, 250, 250, 250));
        setResizable(false);
        jPanelProfile.setBorder(BorderFactory.createTitledBorder("Profile"));
        jListProfile.setModel(new  Object()     /* anonymous class not found */
    class _anm11 {}

);
        jListProfile.setComponentPopupMenu(jPopupMenuProfile);
        jScrollPane1.setViewportView(jListProfile);
        GroupLayout jPanelProfileLayout = new GroupLayout(jPanelProfile);
        jPanelProfile.setLayout(jPanelProfileLayout);
        jPanelProfileLayout.setHorizontalGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, -2, 136, -2));
        jPanelProfileLayout.setVerticalGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, -1, 305, 32767));
        jPanelSettings.setBorder(BorderFactory.createTitledBorder("Settings"));
        jLabel1.setText("Profile:");
        jTextFieldProfile.setText("custom");
        jTextFieldProfile.setToolTipText("Profile name");
        jLabel2.setText("Freq:");
        jTextFieldFreq.setText("30");
        jTextFieldFreq.setToolTipText("Time in seconds to perform the set of actions");
        jLabel3.setText("Delay:");
        jTextFieldDelay.setText("1");
        jTextFieldDelay.setToolTipText("Time in seconds to delay between actions");
        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new  Object()     /* anonymous class not found */
    class _anm12 {}

);
        jButtonStealth.setText("Hide");
        jButtonStealth.addActionListener(new  Object()     /* anonymous class not found */
    class _anm13 {}

);
        GroupLayout jPanelSettingsLayout = new GroupLayout(jPanelSettings);
        jPanelSettings.setLayout(jPanelSettingsLayout);
        jPanelSettingsLayout.setHorizontalGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelSettingsLayout.createSequentialGroup().addContainerGap().addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel2).addComponent(jLabel1)).addGroup(jPanelSettingsLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel3))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jTextFieldDelay).addComponent(jTextFieldFreq).addComponent(jTextFieldProfile, -1, 77, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER).addComponent(jButtonStart, -1, 67, 32767).addComponent(jButtonStealth, -1, 67, 32767)).addContainerGap(73, 32767)));
        jPanelSettingsLayout.setVerticalGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelSettingsLayout.createSequentialGroup().addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextFieldProfile, -2, -1, -2).addComponent(jButtonStart)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jTextFieldFreq, -2, -1, -2).addComponent(jButtonStealth)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jTextFieldDelay, -2, -1, -2).addComponent(jLabel3)).addContainerGap(-1, 32767)));
        jPanelActions.setBorder(BorderFactory.createTitledBorder("Actions"));
        jLabel4.setText("Mouse Move");
        jTextFieldMoveX.setText("X");
        jTextFieldMoveY.setText("Y");
        jButtonMoveAdd.setText("+");
        jButtonMoveAdd.addActionListener(new  Object()     /* anonymous class not found */
    class _anm14 {}

);
        jLabel5.setText("Key Stroke");
        jTextFieldKeyStroke.setEditable(false);
        jTextFieldKeyStroke.addKeyListener(new  Object()     /* anonymous class not found */
    class _anm15 {}

);
        jButtonKeyStrokeAdd.setText("+");
        jButtonLeftAdd.setText("Left Click");
        jButtonLeftAdd.addActionListener(new  Object()     /* anonymous class not found */
    class _anm16 {}

);
        jButtonRightAdd.setText("Right Click");
        jButtonRightAdd.addActionListener(new  Object()     /* anonymous class not found */
    class _anm17 {}

);
        jButtonScreenShotAdd.setText("Screenshot");
        jButtonScreenShotAdd.addActionListener(new  Object()     /* anonymous class not found */
    class _anm18 {}

);
        jLabelMouseXY.setText("1000 , 1000");
        jButton1.setText("jButton1");
        jButton1.addActionListener(new  Object()     /* anonymous class not found */
    class _anm19 {}

);
        GroupLayout jPanelActionsLayout = new GroupLayout(jPanelActions);
        jPanelActions.setLayout(jPanelActionsLayout);
        jPanelActionsLayout.setHorizontalGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelActionsLayout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel5).addComponent(jLabel4)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelActionsLayout.createSequentialGroup().addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(jPanelActionsLayout.createSequentialGroup().addComponent(jTextFieldMoveX, -2, 34, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextFieldMoveY, -2, 35, -2)).addComponent(jTextFieldKeyStroke)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelActionsLayout.createSequentialGroup().addComponent(jButtonMoveAdd).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabelMouseXY)).addComponent(jButtonKeyStrokeAdd))).addGroup(jPanelActionsLayout.createSequentialGroup().addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jButtonScreenShotAdd, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(jButtonLeftAdd, -1, -1, 32767).addComponent(jButtonRightAdd, -1, -1, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1)))));
        jPanelActionsLayout.setVerticalGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelActionsLayout.createSequentialGroup().addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jTextFieldMoveX, -2, -1, -2).addComponent(jTextFieldMoveY, -2, -1, -2).addComponent(jButtonMoveAdd).addComponent(jLabelMouseXY)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(jTextFieldKeyStroke, -2, -1, -2).addComponent(jButtonKeyStrokeAdd)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButtonLeftAdd).addComponent(jButton1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonRightAdd).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonScreenShotAdd).addContainerGap(36, 32767)));
        jMenuFile.setText("File");
        jMenuItemFileLoad.setText("Load");
        jMenuItemFileLoad.addActionListener(new  Object()     /* anonymous class not found */
    class _anm20 {}

);
        jMenuFile.add(jMenuItemFileLoad);
        jMenuItemFileSave.setText("Save");
        jMenuItemFileSave.addActionListener(new  Object()     /* anonymous class not found */
    class _anm21 {}

);
        jMenuFile.add(jMenuItemFileSave);
        jMenuItemFileExit.setText("Exit");
        jMenuItemFileExit.addActionListener(new  Object()     /* anonymous class not found */
    class _anm22 {}

);
        jMenuFile.add(jMenuItemFileExit);
        jMenuBarMain.add(jMenuFile);
        jMenuHelp.setText("Help");
        jMenuItemHelpAbout.setText("About");
        jMenuItemHelpAbout.addActionListener(new  Object()     /* anonymous class not found */
    class _anm23 {}

);
        jMenuHelp.add(jMenuItemHelpAbout);
        jMenuItemHelpHelp.setText("Help");
        jMenuItemHelpHelp.addActionListener(new  Object()     /* anonymous class not found */
    class _anm24 {}

);
        jMenuHelp.add(jMenuItemHelpHelp);
        jMenuBarMain.add(jMenuHelp);
        setJMenuBar(jMenuBarMain);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanelProfile, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jPanelSettings, -1, -1, 32767).addComponent(jPanelActions, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanelSettings, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanelActions, 0, -1, 32767)).addComponent(jPanelProfile, -2, -1, -2)).addContainerGap()));
        pack();
    }

    private void jButtonStartActionPerformed(ActionEvent evt)
    {
        if(jButtonStart.getText().equalsIgnoreCase("start"))
        {
            if(listModelActions.size() < 1)
            {
                JOptionPane _tmp = box;
                JOptionPane.showConfirmDialog(rootPane, "No actions added to profile", "Profile Error", 2);
                System.out.println("no actions");
                return;
            }
            System.out.println("Starting...");
            profileObject.setModel(listModelActions);
            profileObject.setName(jTextFieldProfile.getText());
            int delay = Integer.parseInt(jTextFieldDelay.getText()) * 1000;
            profileObject.setDelay(delay);
            profileObject.setFreq(Integer.parseInt(jTextFieldFreq.getText()));
            robot = new RobotWorker();
            robot.setProfile(profileObject);
            robot.execute();
            jButtonStart.setText("Stop");
        } else
        if(jButtonStart.getText().equalsIgnoreCase("stop"))
        {
            System.out.println("Stoping...");
            robot.stopWorker();
            robot = null;
            jButtonStart.setText("Start");
        }
    }

    private void jTextFieldKeyStrokeKeyTyped(KeyEvent keyevent)
    {
    }

    private void jTextFieldKeyStrokeKeyPressed(KeyEvent evt)
    {
        int keyCode = evt.getKeyCode();
        String stringCode = KeyEvent.getKeyText(keyCode);
        jTextFieldKeyStroke.setText(stringCode);
    }

    private void jMenuItemFileExitActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    private void jTextFieldKeyStrokeKeyReleased(KeyEvent keyevent)
    {
    }

    private void jMenuItemHelpHelpActionPerformed(ActionEvent evt)
    {
        try
        {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("http://www.google.com");
            desktop.browse(uri);
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
        }
    }

    private void jButtonAboutOkActionPerformed(ActionEvent evt)
    {
        jFrameAbout.setVisible(false);
    }

    private void jMenuItemHelpAboutActionPerformed(ActionEvent evt)
    {
        jFrameAbout.setBounds(getBounds());
        jFrameAbout.pack();
        jFrameAbout.setVisible(true);
    }

    private void jMenuItemProfileDeleteActionPerformed(ActionEvent evt)
    {
        listModelActions.removeElement(jListProfile.getSelectedValue());
    }

    private void jMenuItemFileSaveActionPerformed(ActionEvent evt)
    {
        try
        {
            fc.setDialogTitle("Save AutoCick Profile");
            fc.showSaveDialog(this);
            File saveFile = fc.getSelectedFile();
            String profileName = jTextFieldProfile.getText().trim();
            FileOutputStream f_out = new FileOutputStream(saveFile);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            profileObject.setName(jTextFieldProfile.getText());
            profileObject.setFreq(Integer.parseInt(jTextFieldFreq.getText()));
            profileObject.setDelay(Integer.parseInt(jTextFieldDelay.getText()));
            profileObject.setModel(listModelActions);
            obj_out.writeObject(profileObject);
            System.out.println((new StringBuilder()).append("Saved profile ").append(profileName).toString());
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
        }
    }

    private void jMenuItemFileLoadActionPerformed(ActionEvent evt)
    {
        try
        {
            fc.setDialogTitle("Open AutoClick Profile");
            fc.showOpenDialog(this);
            String filename = fc.getSelectedFile().getCanonicalPath();
            FileInputStream f_in = new FileInputStream(filename);
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            Object obj = obj_in.readObject();
            if(obj instanceof Profile)
            {
                System.out.println("Loading profile...");
                profileObject = (Profile)obj;
                jTextFieldProfile.setText(profileObject.getName());
                jTextFieldFreq.setText(String.valueOf(profileObject.getFreq()));
                jTextFieldDelay.setText(String.valueOf(profileObject.getDelay()));
                jListProfile.setModel(profileObject.getModelActions());
                listModelActions = profileObject.getModelActions();
            }
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("Error: ").append(e).toString());
        }
    }

    private void jButtonLeftAddActionPerformed(ActionEvent evt)
    {
        Action action = new Action();
        action.setDisplayName("Left Click");
        action.setType("LClick");
        listModelActions.addElement(action);
    }

    private void jMenuItemProfileClearActionPerformed(ActionEvent evt)
    {
        listModelActions.clear();
    }

    private void jButtonMoveAddActionPerformed(ActionEvent evt)
    {
        try
        {
            Action action = new Action();
            action.setDisplayName((new StringBuilder()).append("Move ").append(jTextFieldMoveX.getText()).append(" ").append(jTextFieldMoveY.getText()).toString());
            action.setType("Move");
            action.setX(Integer.parseInt(jTextFieldMoveX.getText()));
            action.setY(Integer.parseInt(jTextFieldMoveY.getText()));
            listModelActions.addElement(action);
        }
        catch(Exception e)
        {
            JOptionPane _tmp = box;
            JOptionPane.showConfirmDialog(rootPane, "Mouse move values must be numeric", "Profile Error", 2);
        }
    }

    private void jButtonRightAddActionPerformed(ActionEvent evt)
    {
        Action action = new Action();
        action.setDisplayName("Right Click");
        action.setType("RClick");
        listModelActions.addElement(action);
    }

    private void jButtonScreenShotAddActionPerformed(ActionEvent evt)
    {
        Action action = new Action();
        action.setDisplayName("Screenshot");
        action.setType("Screenshot");
        listModelActions.addElement(action);
    }

    private void jButtonStealthActionPerformed(ActionEvent evt)
    {
        setVisible(false);
    }

    private void menuItemExitActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    private void menuItemHideActionPerformed(ActionEvent evt)
    {
        setVisible(false);
    }

    private void menuItemShowActionPerformed(ActionEvent evt)
    {
        setVisible(true);
    }

    private void menuItemStartActionPerformed(ActionEvent evt)
    {
        jButtonStart.doClick();
    }

    private void menuItemAboutActionPerformed(ActionEvent evt)
    {
        jFrameAbout.setBounds(getBounds());
        jFrameAbout.pack();
        jFrameAbout.setVisible(true);
    }

    private void jButtonAboutOk1ActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    private void jButton1ActionPerformed(ActionEvent evt)
    {
        License license = new License();
        license.setRunAmount(0);
        license.setType(66);
        try
        {
            FileOutputStream f_out = new FileOutputStream("AutoData");
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(license);
            System.out.println("Created Licesne.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void menuItemStopActionPerformed(ActionEvent evt)
    {
        try
        {
            System.out.println("Stoping...");
            robot.cancel(false);
            robot = null;
            jButtonStart.setText("Start");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void updateLicense(License theLicense)
    {
        try
        {
            File file = new File(".\\bin\\AutoData");
            FileOutputStream f_out = new FileOutputStream(file.getCanonicalPath());
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(theLicense);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("Error:").append(e).toString());
        }
        EventQueue.invokeLater(new  Object()     /* anonymous class not found */
    class _anm25 {}

);
    }

    private JButton jButton1;
    private JButton jButtonAboutOk;
    private JButton jButtonAboutOk1;
    private JButton jButtonKeyStrokeAdd;
    private JButton jButtonLeftAdd;
    private JButton jButtonMoveAdd;
    private JButton jButtonRightAdd;
    private JButton jButtonScreenShotAdd;
    private JButton jButtonStart;
    private JButton jButtonStealth;
    private JFrame jFrameAbout;
    private JFrame jFrameTrialUp;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel9;
    private JLabel jLabelAboutCopyright;
    private JLabel jLabelAboutCopyright1;
    private JLabel jLabelAboutInfo1;
    private JLabel jLabelAboutInfo2;
    private JLabel jLabelAboutLogo;
    private JLabel jLabelAboutLogo1;
    private JLabel jLabelAboutTitle;
    private JLabel jLabelAboutTitle1;
    private JLabel jLabelAboutVersion;
    private JLabel jLabelAboutVersion1;
    private JLabel jLabelLicense;
    private JLabel jLabelMouseXY;
    private JList jListProfile;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JMenuBar jMenuBarMain;
    private JMenu jMenuFile;
    private JMenu jMenuHelp;
    private JMenuItem jMenuItemFileExit;
    private JMenuItem jMenuItemFileLoad;
    private JMenuItem jMenuItemFileSave;
    private JMenuItem jMenuItemHelpAbout;
    private JMenuItem jMenuItemHelpHelp;
    private JMenuItem jMenuItemProfileClear;
    private JMenuItem jMenuItemProfileDelete;
    private JPanel jPanelAboutInfo;
    private JPanel jPanelAboutInfo1;
    private JPanel jPanelActions;
    private JPanel jPanelProfile;
    private JPanel jPanelSettings;
    private JPopupMenu jPopupMenuProfile;
    private JScrollPane jScrollPane1;
    private JTextField jTextFieldDelay;
    private JTextField jTextFieldFreq;
    private JTextField jTextFieldKeyStroke;
    private JTextField jTextFieldMoveX;
    private JTextField jTextFieldMoveY;
    private JTextField jTextFieldProfile;
    private MenuItem menuItemAbout;
    private MenuItem menuItemExit;
    private MenuItem menuItemHide;
    private MenuItem menuItemShow;
    private MenuItem menuItemStart;
    private MenuItem menuItemStop;
    private PopupMenu popupMenu1;
    private PopupMenu popupMenu2;
    private Profile profileObject;
    JFileChooser fc;
    private DefaultListModel listModelActions;
    private TrayIcon trayIcon;
    private MouseGrabber mouseGrabber;
    private FreeTrial freeTrial;
    private JOptionPane box;
    private RobotWorker robot;

























}