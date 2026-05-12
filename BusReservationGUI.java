import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class BusReservationGUI extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel subtitleLabel;
    JLabel nameLabel;
    JLabel seatLabel;
    JLabel dateLabel;

    JTextField nameField;
    JTextField seatField;
    JTextField dateField;

    JButton bookButton;
    JButton displayButton;
    JButton clearButton;

    JTextArea displayArea;

    JPanel mainPanel;
    JPanel formPanel;
    JPanel buttonPanel;

    ArrayList<Reservation> reservations;

    public BusReservationGUI() {

        reservations = new ArrayList<>();

        setTitle("Modern Bus Reservation System");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(18, 18, 32));

        titleLabel = new JLabel("🚌 BUS RESERVATION SYSTEM");
        titleLabel.setBounds(180, 20, 500, 50);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0, 255, 255));

        subtitleLabel = new JLabel("Fast • Secure • Smart Booking");
        subtitleLabel.setBounds(280, 65, 300, 30);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.LIGHT_GRAY);

        formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(50, 120, 730, 200);
        formPanel.setBackground(new Color(28, 28, 45));
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(0, 255, 255), 2, true),
                new EmptyBorder(10, 10, 10, 10)));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 15);

        nameLabel = new JLabel("Passenger Name");
        nameLabel.setBounds(40, 30, 150, 30);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(labelFont);

        seatLabel = new JLabel("Seat Number");
        seatLabel.setBounds(40, 80, 150, 30);
        seatLabel.setForeground(Color.WHITE);
        seatLabel.setFont(labelFont);

        dateLabel = new JLabel("Travel Date");
        dateLabel.setBounds(40, 130, 150, 30);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(labelFont);

        nameField = createStyledTextField();
        nameField.setBounds(220, 30, 420, 35);

        seatField = createStyledTextField();
        seatField.setBounds(220, 80, 420, 35);

        dateField = createStyledTextField();
        dateField.setBounds(220, 130, 420, 35);

        formPanel.add(nameLabel);
        formPanel.add(seatLabel);
        formPanel.add(dateLabel);

        formPanel.add(nameField);
        formPanel.add(seatField);
        formPanel.add(dateField);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
        buttonPanel.setBounds(120, 340, 600, 70);
        buttonPanel.setBackground(new Color(18, 18, 32));

        bookButton = createStyledButton("Book Seat", new Color(0, 200, 83));
        displayButton = createStyledButton("Display Bookings", new Color(33, 150, 243));
        clearButton = createStyledButton("Clear", new Color(244, 67, 54));

        buttonPanel.add(bookButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(28, 28, 45));
        displayArea.setForeground(Color.WHITE);
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        displayArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 430, 730, 140);
        scrollPane.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));

        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);

        add(mainPanel);

        bookButton.addActionListener(this);
        displayButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    private JTextField createStyledTextField() {

        JTextField field = new JTextField();

        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBackground(new Color(40, 40, 60));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.CYAN);

        field.setBorder(new CompoundBorder(
                new LineBorder(new Color(0, 255, 255), 1, true),
                new EmptyBorder(5, 10, 5, 10)));

        return field;
    }

    private JButton createStyledButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setPreferredSize(new Dimension(180, 45));

        return button;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookButton) {

            try {

                String name = nameField.getText();
                int seat = Integer.parseInt(seatField.getText());
                String date = dateField.getText();

                if (name.isEmpty() || date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);

                    return;
                }

                Reservation reservation =
                        new Reservation(name, seat, date);

                reservations.add(reservation);

                JOptionPane.showMessageDialog(
                        this,
                        "✅ Seat Booked Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                nameField.setText("");
                seatField.setText("");
                dateField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Invalid Seat Number",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == displayButton) {

            displayArea.setText("");

            if (reservations.isEmpty()) {

                displayArea.setText("No Bookings Available");

            } else {

                for (Reservation r : reservations) {

                    displayArea.append(r.toString() + "\n\n");
                }
            }
        }

        if (e.getSource() == clearButton) {

            nameField.setText("");
            seatField.setText("");
            dateField.setText("");
            displayArea.setText("");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new BusReservationGUI();
        });
    }
}