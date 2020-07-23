import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import javax.swing.border.EmptyBorder;

public class mainWindow extends JFrame implements ActionListener {

    //Component declaration
    JButton buttonChoose;
    JButton buttonAnalyze;
    JLabel image;
    JList listColorName;
    JPanel palletteColor;
    JPanel colorCat;
    JButton buttonColor[];
    JButton option;
    JSplitPane split;
    JSplitPane splitTwo;
    JLabel catLabel;
    String filePath = "";
    Multimap<String, String> colorDict;
    int size;

    JButton buttonOption;
    JLabel panel;

    //The height and weight of resizing the picture.
    static int pv = 0;
    static int sw = 10;
    static int sh = 10;

    String type;

    //File choose to open the window for use to choose the file.
    private final JFileChooser fc;

    private static String[] colorName = {"No Color"};

    public mainWindow(String title){
        super(title);

        //Set background picture.
        try{
            BufferedImage image = ImageIO.read(new File("assets/image/background2.png"));
            panel = new JLabel(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Layout
        panel.setLayout(new GridBagLayout());
        fc = new JFileChooser();


        //Content panel
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0,10,0,10);

        //Choose button
        buttonChoose = new JButton("Choose Picture");
        buttonChoose.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(buttonChoose,c);

        //Analyze picture
        buttonAnalyze = new JButton("Analyze");
        buttonAnalyze.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(buttonAnalyze,c);

        //Image panel
        image = new JLabel(new ImageIcon("assets/image/no-image.png"));
        image.setPreferredSize(new Dimension(400,533));
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        panel.add(image, c);

        //Color panel
        palletteColor = new JPanel();
        palletteColor.setLayout(new BoxLayout(palletteColor, BoxLayout.LINE_AXIS));

        colorCat = new JPanel();
        colorCat.setLayout(new FlowLayout());

        listColorName = new JList(colorName);
        listColorName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listColorName.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listColorName.setVisibleRowCount(-1);
        listColorName.setFont(new Font("Serif",Font.BOLD,20));
        listColorName.setFixedCellHeight(50);
        listColorName.setFixedCellWidth(200);
        listColorName.setBorder(new EmptyBorder(10,30, 10, 0));

        //Create button of color when user click on each color
        listColorName.addListSelectionListener(e -> {
            if (listColorName.getValueIsAdjusting()) {
                palletteColor.removeAll();

                size = colorDict.get((String) listColorName.getSelectedValue()).size();

                if (listColorName.getSelectedValue().equals("No Color"))
                {
                    colorCat.removeAll();
                    colorCat.revalidate();
                    colorCat.repaint();
                    palletteColor.removeAll();
                    palletteColor.revalidate();
                    palletteColor.repaint();
                }
                else {
                    buttonColor = new JButton[size];
                    String[] convertColorToInt;
                    for (int k = 0; k < size; k++)
                    {
                        String rgbAsString = Iterables.get((colorDict.get((String) listColorName.getSelectedValue())),k);
                        convertColorToInt = rgbAsString.split(",");
                        Color setColor = new Color(Integer.parseInt(convertColorToInt[0]),Integer.parseInt(convertColorToInt[1]),Integer.parseInt(convertColorToInt[2]));
                        buttonColor[k] = new customButton(setColor);
                    }
                    try{
                        colorCat.removeAll();
                        colorCat.revalidate();
                        colorCat.repaint();
                        catLabel = new JLabel();
                        imageColor catergory = new imageColor();
                        type = catergory.getCatergory(Iterables.get((colorDict.get((String) listColorName.getSelectedValue())),0));
                        catLabel.setText(type);
                        catLabel.setFont(new Font("Serif",Font.BOLD,20));
                        colorCat.add(catLabel);
                        colorCat.revalidate();
                        colorCat.repaint();
                    }
                    catch (IOException err)
                    {
                        System.out.println("Fail Loading Color Type");
                    }
                    for (int j = 0; j < size; j++)
                    {
                        palletteColor.add(Box.createRigidArea(new Dimension(20,0)));
                        palletteColor.add(buttonColor[j]);
                        buttonColor[j].addActionListener(this);
                        palletteColor.revalidate();
                        palletteColor.repaint();
                    }
                    palletteColor.add(Box.createRigidArea(new Dimension(20,0)));

                }
            }
        });

        splitTwo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(colorCat), new JScrollPane(palletteColor));
        splitTwo.setOneTouchExpandable(true);
        splitTwo.setResizeWeight(0.1);

        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(listColorName), new JScrollPane(splitTwo));

        split.setOneTouchExpandable(true);

        split.setResizeWeight(0.28);

        Dimension size = new Dimension(400, 533);
        split.setPreferredSize(size);

        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 4;
        panel.add(split,c);

        //Option button
        buttonOption = new JButton("Option");
        buttonOption.addActionListener(this);
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 0;
        panel.add(buttonOption,c);


        c.weighty = 1.0;
        add(panel);
        pack();
        setVisible(true);
    }

    //Action perform when click
    public void actionPerformed(ActionEvent e) {

        //Click choose button
        if (e.getSource() == buttonChoose){
            int data = fc.showOpenDialog(mainWindow.this);

            //Open file chooser window
            if (data == JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();
                String ext = getExt(file);
                //Check input type
                if (ext.matches("jpg|gif|tif|png")) {
                    image.setIcon(new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(800, 533, Image.SCALE_DEFAULT)));
                    filePath = file.getPath();
                }
                else {
                    final JOptionPane alertPane = new JOptionPane("File is not a picture");
                    final JDialog m = alertPane.createDialog(null, "Warning");
                    m.setVisible(true);
                }
            }
            else {
                System.out.println("Cancelled\n");
            }
        }
        //Button analyze click
        else if (e.getSource() == buttonAnalyze) {

            palletteColor.removeAll();
            palletteColor.revalidate();
            palletteColor.repaint();

            JOptionPane load = new JOptionPane("Please wait or press cancel to stop ... ");
            final JDialog wait = load.createDialog(null, "Analyzing");
            Object[] options = { "CANCEL" };
            load.setOptions(options);

            DefaultListModel list = new DefaultListModel();

            //Create thread for picture process.
            SwingWorker<DefaultListModel,Void> thread = new SwingWorker<>() {
                @Override
                protected DefaultListModel doInBackground() throws Exception {
                    colorDict = getColorDict(filePath);
                    for (String key : colorDict.keySet()) {
                        Thread.sleep(100);
                        list.addElement(key);
                    }
                    list.addElement("No Color");
                    return list;
                }

                @Override
                protected void done() {
                    try{
                        DefaultListModel r = get();
                        listColorName.setModel(r);
                        wait.setVisible(false);
                        System.out.println("Done");
                    }
                    catch (CancellationException e) {
                        DefaultListModel c = new DefaultListModel();
                        c.addElement("No Color");
                        listColorName.setModel(c);
                        System.out.println("Cancelled");
                    }
                    catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                    catch (ExecutionException e){
                        DefaultListModel errMessage = new DefaultListModel();
                        errMessage.addElement("No Color");
                        listColorName.setModel(errMessage);
                        final JOptionPane alertPane = new JOptionPane("Cannot find file");
                        final JDialog m = alertPane.createDialog(null, "Warning");
                        wait.setVisible(false);
                        m.setVisible(true);

                    }
                }
            };

            thread.execute();

            wait.setVisible(true);

            Object select = load.getValue();
            if (select != null)
            {
                thread.cancel(true);
            }
        }
        //Option button click choose quality
        else if (e.getSource() == buttonOption){
            Object[] value = { "Low", "Medium", "High" };

            Object selectedValue = JOptionPane.showInputDialog(null, "Choose level of analyze", "Analyze Option", JOptionPane.INFORMATION_MESSAGE, null, value, value[pv]);

            if (selectedValue == "Low") {
                pv = 0;
                sw = 10;
                sh = 10;
                System.out.println(selectedValue);
            } else if (selectedValue == "Medium") {
                pv = 1;
                sw = 30;
                sh = 30;
                System.out.println(selectedValue);
            } else if (selectedValue == "High") {
                pv = 2;
                sw = 50;
                sh = 50;
                System.out.println(selectedValue);
            }
        }
        else
        {
            //play the sound with other button is clicked
            for (int v = 0; v < size; v++)
            {
                if (e.getSource() == buttonColor[v])
                {
                    sound ob = new sound();
                    ob.play(type,0,v);
                }
            }
        }
    }

    //Get file extension name
    public static String getExt(File f){
        int last = f.getName().lastIndexOf('.');
        String ext = "";

        if (last > 0 && last < f.getName().length() - 1)
        {
            ext = f.getName().substring(last + 1).toLowerCase();
        }
        return ext;
    }

    //Get the color name from api
    public static Multimap<String,String> getColorDict(String filePath) throws IOException {
        imageColor obj = new imageColor();
        Multimap<String, String> colorDict = obj.imgData(filePath,sw,sh);
        return colorDict;
    }
}
