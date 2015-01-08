package cachesim.view;

import javax.swing.JOptionPane;

/**
 * A dialog used to get the user's nick name.
 */
class NickNameDialog {

    /**
     * Displays a dialog where the user can enter nick name.
     * 
     * CALL THIS METHOD TO GET THE USER'S NICK NAME
     *
     * @return The user's nick name or <code>null</code> if the user did not enter a nick name.
     */
	
	
    String getNickName() {
        return (String) JOptionPane.showInputDialog(null, "Please enter nick name:", "Nick Name",
                                                    JOptionPane.PLAIN_MESSAGE, null, null, null);
    }
}
