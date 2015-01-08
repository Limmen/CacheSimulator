package cachesim.view;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Contains all configurations for the cache simulator.
 */
class SettingsPanel extends JPanel {
    private static final int TEXT_FIELD_LENGTH = 3;
    private JLabel cacheSizeValue = new JLabel();
    private MainPanel mainPanel;

    /**
     * Creates a new <code>SettingsPanel</code> with all its components.
     *
     * @param mainPanel Holds references to all panels and manages the Gui layout.
     */
    SettingsPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        createGui();
    }

    /**
     * Called when user click the <code>Apply</code> button in the settings panel.
     * 
     * CALL CONTROLLER FROM THIS METHOD!!
     *
     * @param blockSize     The size, in 4 byte words, of a block in the cache.
     * @param blockCount    The number of blocks in each set in the cache.
     * @param associativity The number of sets in the cache.
     */
    private void applySettings(int blockSize, int blockCount, int associativity)
    {
    	int cachesize = mainPanel.applySettings(blockSize, blockCount, associativity);
    	showCacheSize(cachesize);
    }

    /**
     * Displays the size, in bytes, of the cache.
     * 
     * CALL THIS METHOD TO SHOW THE CACHE SIZE!! 
     *
     * @param cacheSize The size, in bytes, of the cache.
     */
    private void showCacheSize(int cacheSize) {
        cacheSizeValue.setText(Integer.toString(cacheSize) + " bytes.");
        revalidate();
    }
    
    private boolean isValidCacheLayout(String blockSizeStr, String blockCountStr, 
                                          String associativityStr) {
        int blockSize = 0;
        int blockCount = 0;
        int associativity = 0;
        try {
            blockSize = Integer.parseInt(blockSizeStr);
            blockCount = Integer.parseInt(blockCountStr);
            associativity = Integer.parseInt(associativityStr);
        } catch(NumberFormatException invalidValueWasEntered) {
            JOptionPane.showMessageDialog(null, "Only digits are allowed.",
                                          "Invalid Value", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isPowerOfTwo(blockSize)) {
            showInvalidValue("Block size");
            return false;
        }
        if (!isPowerOfTwo(blockCount)) {
            showInvalidValue("Block count");
            return false;
        }
        return true;
    }

    private boolean isPowerOfTwo(int operand) {
        return (operand % 2) == 0;
    }

    private void showInvalidValue(String valueName) {
        JOptionPane.showMessageDialog(null, valueName + " must be a power of two.",
                                      "Invalid " + valueName, JOptionPane.ERROR_MESSAGE);
    }

    private void createGui() {
        setBorder(new TitledBorder(new EtchedBorder(), "Settings"));
        setLayout(new GridBagLayout());
        Insets insets = new Insets(2, 2, 2, 2);
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = insets;
        constr.anchor = GridBagConstraints.LINE_START;

        constr.gridx = 0;
        constr.gridy = 0;
        add(new JLabel("Block Size:"), constr);

        constr.gridx = 1;
        constr.gridy = 0;
        final JTextField blockSizeText = new JTextField(TEXT_FIELD_LENGTH);
        add(blockSizeText, constr);

        constr.gridx = 0;
        constr.gridy = 1;
        final JTextField blockCountText = new JTextField(TEXT_FIELD_LENGTH);
        add(new JLabel("Block Count:"), constr);

        constr.gridx = 1;
        constr.gridy = 1;
        add(blockCountText, constr);

        constr.gridx = 2;
        constr.gridy = 0;
        add(new JLabel("Associativity:"), constr);

        constr.gridx = 3;
        constr.gridy = 0;
        final JTextField associativityText = new JTextField(TEXT_FIELD_LENGTH);
        add(associativityText, constr);

        constr.gridx = 3;
        constr.gridy = 1;
        final JButton applyButton = new JButton("Apply");
        add(applyButton, constr);
        applyButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String blockSize = blockSizeText.getText();
                        String blockCount = blockCountText.getText();
                        String associativity = associativityText.getText();
                        
                        if (!isValidCacheLayout(blockSize, blockCount, associativity)) {
                            return;
                        }
                        applySettings(Integer.parseInt(blockSize), 
                                      Integer.parseInt(blockCount), 
                                      Integer.parseInt(associativity));
                    }
 
                });

        constr.gridx = 4;
        constr.gridy = 0;
        add(new JLabel("Cache Size:"), constr);

        constr.gridx = 5;
        constr.gridy = 0;
        add(cacheSizeValue, constr);
    }

    
}
