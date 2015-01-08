package cachesim.view;

import java.awt.Dimension; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cachesim.util.InstructionType;
import java.util.ArrayList;
/**
 * The user specifies which instruction to execute in this panel.
 */
class ExecutePanel extends Box {
    private static final int COMPONENT_SPACING = 5;
    private static final int ADDR_FIELD_SIZE = 7;
    private static final Dimension FIXED_COMPONENT_SIZE = new Dimension(1, 25);
    private MainPanel mainPanel;
    //private Controller contr;

    /**
     * Creates a new <code>ExecutionPanel</code> with all its components.
     *
     * @param mainPanel Holds references to all panels and manages the Gui layout.
     */
    public ExecutePanel(MainPanel mainPanel) {
        super(BoxLayout.X_AXIS);
        this.mainPanel = mainPanel;
        createGui();
    }

    /**
     * Called when user clicks the <code>Execute</code> button in the execute panel.
     *
     * CALL CONTROLLER FROM THIS METHOD!!
     *
     * @param instrType The type of instruction that shall be simulated.
     * @param address   The address in main memory used by the instruction that shall be simulated.
     *                  The address is hexadecimal.
     */
    private void simulateInstruction(InstructionType instrType, int address) 
    {
    	mainPanel.simulateInstruction(instrType, address);
    }

    /**
     * Called when user clicks the <code>End Simulation</code> button in the execute panel.
     *
     * CALL CONTROLLER FROM THIS METHOD!!
     */
    private void endSimulation() 
    {
    	mainPanel.Display();
    	
    }

    private void createGui() {
        setBorder(new TitledBorder(new EtchedBorder(), "Execute Instruction"));
        add(Box.createHorizontalGlue());

        add(new JLabel("Instruction:"));

        final JSpinner instructionSpinner = new JSpinner(new SpinnerListModel(InstructionType.
                values()));
        makeSpinnerUneditable(instructionSpinner);
        instructionSpinner.setMaximumSize(FIXED_COMPONENT_SIZE);
        add(instructionSpinner);

        add(Box.createHorizontalStrut(COMPONENT_SPACING));

        add(new JLabel("Address:"));

        final JTextField addressField = new JTextField(ADDR_FIELD_SIZE);
        addressField.setMaximumSize(FIXED_COMPONENT_SIZE);
        add(addressField);

        add(Box.createHorizontalStrut(COMPONENT_SPACING));

        JButton execButton = new JButton("Execute");
        add(execButton);
        execButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int hexAddress = 16;
                        String address = addressField.getText();
                        if (!isValidAddress(address)) {
                            return;
                        }
                        simulateInstruction((InstructionType) instructionSpinner.getValue(),
                                            Integer.parseInt(addressField.getText(), hexAddress));
                    }

                });

        add(Box.createHorizontalStrut(COMPONENT_SPACING));

        JButton endSimulButton = new JButton("End Simulation");
        add(endSimulButton);
        endSimulButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainPanel.changeCacheLayout(0, 0, 0);
                        endSimulation();
                    }
                });

        add(Box.createHorizontalGlue());
    }

    private boolean isValidAddress(String address) {
        int hexadecimal = 16;
        try {
            Integer.parseInt(address, hexadecimal);
        } catch (NumberFormatException invalidValueWasEntered) {
            JOptionPane.showMessageDialog(null, "Only hexadecimal values are allowed.",
                                          "Invalid Value", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void makeSpinnerUneditable(JSpinner instructionSpinner) {
        ((JSpinner.DefaultEditor) instructionSpinner.getEditor()).getTextField().setEditable(false);
    }
}
