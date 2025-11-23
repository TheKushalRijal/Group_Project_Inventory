import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InventoryDashboardUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Inventory Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 800);
            frame.setLayout(new BorderLayout());

            // LEFT SIDEBAR
            JPanel sidebar = new JPanel();
            sidebar.setBackground(new Color(20, 30, 50));
            sidebar.setPreferredSize(new Dimension(220, 800));
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

            JLabel logo = new JLabel("  INFLOW PRO");
            logo.setForeground(Color.WHITE);
            logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
            logo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
            sidebar.add(logo);

            String[] menuItems = {
                "Dashboard", "Products", "Orders", "Suppliers",
                "Locations", "Reports", "Help", "Settings"
            };

            for (String item : menuItems) {
                JButton btn = new JButton(item);
                btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                btn.setMaximumSize(new Dimension(200, 45));
                btn.setBackground(new Color(35, 45, 65));
                btn.setForeground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                sidebar.add(btn);
            }

            frame.add(sidebar, BorderLayout.WEST);

            // TOP NAVIGATION
            JPanel topNav = new JPanel(new BorderLayout());
            topNav.setBackground(new Color(30, 40, 60));
            topNav.setPreferredSize(new Dimension(1300, 60));

            JPanel navTabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
            navTabs.setOpaque(false);

            String[] tabs = {"Overview", "Analytics", "Alerts"};
            for (String t : tabs) {
                JLabel tab = new JLabel(t);
                tab.setForeground(Color.WHITE);
                tab.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                navTabs.add(tab);
            }

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            searchPanel.setOpaque(false);
            JTextField search = new JTextField(20);
            searchPanel.add(search);

            topNav.add(navTabs, BorderLayout.WEST);
            topNav.add(searchPanel, BorderLayout.EAST);

            frame.add(topNav, BorderLayout.NORTH);

            // MAIN CONTENT
            JPanel content = new JPanel(new BorderLayout());
            content.setBackground(new Color(25, 35, 50));

            // CARDS
            JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
            cardsPanel.setBackground(new Color(25, 35, 50));
            cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            cardsPanel.add(createCard("TOTAL STOCK", "1,245,890"));
            cardsPanel.add(createCard("LOW STOCK ITEMS", "45"));
            cardsPanel.add(createCard("PENDING ORDERS", "120"));

            content.add(cardsPanel, BorderLayout.NORTH);

            // TABLE FROM JSON
            String[] cols = {"DATE", "PRODUCT", "TYPE", "QUANTITY", "STATUS"};

            List<RecordItem> items = DataLoader.loadRecords("data.json");

            Object[][] rowData = new Object[items.size()][5];
            for (int i = 0; i < items.size(); i++) {
                RecordItem r = items.get(i);
                rowData[i] = new Object[]{
                    r.getDate(), r.getProduct(), r.getType(),
                    r.getQuantity(), r.getStatus()
                };
            }

            JTable table = new JTable(rowData, cols);
            JScrollPane tableScroll = new JScrollPane(table);

            content.add(tableScroll, BorderLayout.CENTER);

            frame.add(content, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    private static JPanel createCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(40, 50, 70));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }
}
