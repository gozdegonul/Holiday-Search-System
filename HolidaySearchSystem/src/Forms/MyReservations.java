package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.DatabaseConnection;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyReservations extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;

	DatabaseConnection db = new DatabaseConnection();

	String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyReservations frame = new MyReservations();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyReservations() {
		setTitle("My Reservations");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(230, 10, 546, 70);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 112, 992, 355);
		contentPane.add(scrollPane);

		model = new DefaultTableModel();
		model.addColumn("Reservation No");
		model.addColumn("Hotel Name");
		model.addColumn("Room Type");
		model.addColumn("Holiday Start Date");
		model.addColumn("Holiday End Date");
		model.addColumn("Number of People");
		model.addColumn("Price");

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JButton btnDeleteReservations = new JButton("Delete Reservation(s)");
		btnDeleteReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(null, "Please select reservations that you want to delete.");
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete reservation(s)", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION)
					return;

				for (int i = 0; i < selectedRows.length; i++) {
					int reservationId = Integer.parseInt(table.getValueAt(selectedRows[i], 0).toString());
					db.deleteReservationAndIncreaseRoomAvailability(reservationId);
				}

				db.loadUpcomingReservations(username, table);

			}
		});
		btnDeleteReservations.setOpaque(true);
		btnDeleteReservations.setForeground(Color.WHITE);
		btnDeleteReservations.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDeleteReservations.setBorderPainted(false);
		btnDeleteReservations.setBackground(Color.RED);
		btnDeleteReservations.setBounds(805, 490, 211, 30);
		contentPane.add(btnDeleteReservations);

		JButton btnUpcomingReservations = new JButton("Upcoming Reservations");
		btnUpcomingReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.loadUpcomingReservations(username, table);
				btnDeleteReservations.setEnabled(true);
			}
		});
		btnUpcomingReservations.setBounds(30, 20, 211, 30);
		panel.add(btnUpcomingReservations);
		btnUpcomingReservations.setOpaque(true);
		btnUpcomingReservations.setForeground(Color.WHITE);
		btnUpcomingReservations.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnUpcomingReservations.setBorderPainted(false);
		btnUpcomingReservations.setBackground(Color.blue);

		JButton btnPastReservations = new JButton("Past Reservations");
		btnPastReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.loadPastReservations(username, table);
				btnDeleteReservations.setEnabled(false);
			}
		});
		btnPastReservations.setOpaque(true);
		btnPastReservations.setForeground(Color.WHITE);
		btnPastReservations.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnPastReservations.setBorderPainted(false);
		btnPastReservations.setBackground(Color.blue);
		btnPastReservations.setBounds(309, 20, 211, 30);
		panel.add(btnPastReservations);

		db.loadUpcomingReservations(username, table);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPage user = new UserPage();
				user.labelUsername(username);
				user.setVisible(true);
				dispose();
			}
		});
		btnBack.setOpaque(true);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(10, 539, 108, 30);
		contentPane.add(btnBack);
	}
}
