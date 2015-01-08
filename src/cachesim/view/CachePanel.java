package cachesim.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * Shows the current cache state.
 */
class CachePanel extends JPanel {
    private static final int CELL_SIZE = 20;
    private static final int LABEL_POSITION_IN_ROW = 0;
    private static final int ADDRESS_POSITION_IN_ROW = 1;
    private static final int SET_POSITION_IN_ROW = 2;
    private static final int ROW_SIZE = 3;

    private JComponent[][] components;
    private boolean[] highlightedSets;
    private int blockSize;
    private int blockCount;

    /**
     * Creates an empty cache panel.
     */
    public CachePanel() {
        createBorder();
        add(new JLabel("Cache layout not specified."));
    }

    /**
     * Creates a new <code>CachePanel</code> that shows all sets in the cache. In a newly
     * created cache panel, no set is mapped to an address in main memory. .
     *
     * @param blockSize     The size of a block in the cache.
     * @param blockCount    The number of blocks in each set in the cache.
     * @param associativity The number of sets in the cache.
     */
    CachePanel(int blockSize, int blockCount, int associativity) {
        this.blockSize = blockSize;
        this.blockCount = blockCount;
        components = new JComponent[associativity][ROW_SIZE];
        highlightedSets = new boolean[associativity];
        createGui(associativity);
    }

    /**
     * Maps the cache set with the specified number to the specified address in main 
     * memory. This method does not check if the set is free, the model should do that. It is 
     * unspecified what happens if the set is not free. 
     * 
     * CALL THIS METHOD TO USE A SET!!
     * 
     * @param setNo   The number of the set that shall be used.
     * @param address The address in main memory to which a free set is mapped.
     */
    void useSet(int setNo, int address) {
        createSet(setNo, address);
        revalidate();
        repaint();
    }

    /**
     * Indicate that there was a cache miss. 
     * 
     * CALL THIS METHOD TO DISPLAY A MISS!!
     */
    void miss() {
        final Color bg = getBackground();
        setBackground(Color.RED);
        revalidate();
        final JPanel blinkPanel = this;
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        int blinkTime = 500;
                        try {
                            Thread.sleep(blinkTime);
                        } catch (InterruptedException ignore) {
                        }
                        blinkPanel.setBackground(bg);
                    }
                });
    }

    /**
     * Indicate that new data was loaded in the specified cache block. 
     * 
     * CALL THIS METHOD TO SHOW THAT A NEW VALUE WAS LOADED!! 
     *
     * @param setNo   The set where the new value was loaded.
     * @param blockNo The block where the new value was loaded.
     */
    void newValueInCache(int setNo, final int blockNo) {
        clearHighlightedSets();
        highlightedSets[setNo] = true;
        JViewport setViewport = 
                ((JScrollPane) components[setNo][SET_POSITION_IN_ROW]).getViewport();
        JTable newSetTable = new JTable(new CacheSet(blockCount, blockSize)) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                                             int row, int column) {
                JLabel label = (JLabel) super.prepareRenderer(renderer, row,
                                                              column);
                if (row == blockNo) {
                    label.setBackground(Color.YELLOW);
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        };
        prepareSetTable(newSetTable);
        setViewport.setView(newSetTable);
        repaint();
    }

    /**
     * Indicate the the specified word was read/written from/to the cache.
     * 
     * CALL THIS METHOD TO SHOW THAT A VALUE WAS FOUND!!
     *
     * @param setNo   The set that contains the word.
     * @param blockNo The block that contains the word
     * @param offset  The offset in the block to word.
     */
    void hit(int setNo, final int blockNo, final int offset) {
        clearHighlightedSets();
        highlightedSets[setNo] = true;
        JViewport setViewport = 
                ((JScrollPane) components[setNo][SET_POSITION_IN_ROW]).getViewport();
        JTable newSetTable = new JTable(new CacheSet(blockCount, blockSize)) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                                             int row, int column) {
                JLabel label = (JLabel) super.prepareRenderer(renderer, row,
                                                              column);
                if (row == blockNo && column == offset) {
                    label.setBackground(Color.GREEN);
                } else {
                    label.setBackground(Color.WHITE);
                }
                return label;
            }
        };
        prepareSetTable(newSetTable);
        setViewport.setView(newSetTable);
        repaint();
    }

    private void clearHighlightedSets() {
        int setNo = 0;
        for (boolean isHighlighted : highlightedSets) {
            if (isHighlighted) {
                JViewport setViewport = ((JScrollPane) components[setNo][SET_POSITION_IN_ROW]).
                        getViewport();
                JTable newSetTable = new JTable(new CacheSet(blockCount, blockSize));
                prepareSetTable(newSetTable);
                setViewport.setView(newSetTable);
                repaint();
            }
            setNo++;
        }
    }

    private void createGui(int associativity) {
        createBorder();
        setLayout(new GridBagLayout());

        for (int setNo = 0; setNo < associativity; setNo++) {
            createSet(setNo);
        }
    }

    private void createSet(int setNo, int address) {
        GridBagConstraints constr = createAddressPart(setNo);
        setComponent(setNo, ADDRESS_POSITION_IN_ROW, new JLabel(Integer.toHexString(address)),
                     constr);
        createSetPart(setNo, constr);
    }

    private void createSet(int setNo) {
        GridBagConstraints constr = createAddressPart(setNo);
        setComponent(setNo, ADDRESS_POSITION_IN_ROW, new JLabel("Not mapped to memory."), constr);
        createSetPart(setNo, constr);
    }

    private void createSetPart(int setNo, GridBagConstraints constr) {
        JTable cacheView = new JTable(new CacheSet(blockCount, blockSize));
        prepareSetTable(cacheView);
        addTableWithScrollbars(setNo, cacheView, constr);
    }

    private void prepareSetTable(JTable cacheView) {
        doNotShowColumnHeaders(cacheView);
        setCellSize(cacheView);
        fixedSizeTable(cacheView);
    }

    private GridBagConstraints createAddressPart(int setNo) {
        Insets insets = new Insets(2, 2, 2, 2);
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = insets;

        constr.anchor = GridBagConstraints.LINE_END;
        setComponent(setNo, LABEL_POSITION_IN_ROW, new JLabel("Set Address:"), constr);

        constr.anchor = GridBagConstraints.LINE_START;
        return constr;
    }

    private void setComponent(int row, int column, JComponent component, 
                                GridBagConstraints constr) {
        constr.gridx = column;
        constr.gridy = row;
        if (components[row][column] != null) {
            remove(components[row][column]);
        }
        components[row][column] = component;
        add(component, constr);
    }

    private void createBorder() {
        setBorder(new TitledBorder(new EtchedBorder(), "Cache Content"));
    }

    private void setCellSize(JTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMaxWidth(CELL_SIZE);
        }
        table.setRowHeight(CELL_SIZE);
    }

    private void doNotShowColumnHeaders(JTable table) {
        table.setTableHeader(null);
    }

    private void fixedSizeTable(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private void addTableWithScrollbars(int row, JTable table, GridBagConstraints constr) {
        int consumeAllSpace = 1;
        constr.anchor = GridBagConstraints.CENTER;
        constr.weightx = consumeAllSpace;
        constr.weighty = consumeAllSpace;
        constr.fill = GridBagConstraints.BOTH;
        setComponent(row, SET_POSITION_IN_ROW, new JScrollPane(table), constr);
    }

    /**
     * The cache content. All cells are always empty since the simulator does not handle actual
     * memory values.
     */
    private class CacheSet extends AbstractTableModel {
        private int rowCount;
        private int columnCount;
        private int address;

        private CacheSet(int rowCount, int columnCount, int address) {
            this.rowCount = rowCount;
            this.columnCount = columnCount;
            this.address = address;
        }

        private CacheSet(int rowCount, int columnCount) {
            this(rowCount, columnCount, 0);
        }

        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return " ";
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
    }
}
