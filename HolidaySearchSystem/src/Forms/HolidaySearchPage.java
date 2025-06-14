package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import Classes.DatabaseConnection;
import Classes.Rooms;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HolidaySearchPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblHolidayType;
	private JLabel lblHolidayDestination;
	private JLabel lblHolidayStartDay;
	private JLabel lblHolidayEndDay;
	private JLabel lblChoosedType;
	private JLabel lblChoosedDestination;
	private JLabel lblChoosedStartDate;
	private JLabel lblChoosedEndDate;
	private JLabel lblChoosedNumber;
	DefaultTableModel model;
	List<Rooms> availableRooms;
	JButton btnBuyOption1;
	JButton btnBuyOption2;

	DatabaseConnection db = new DatabaseConnection();

	public String username;

	public void HolidayType(String type) {
		lblChoosedType.setText(type);
	}

	public void HolidayDestination(String destination) {
		lblChoosedDestination.setText(destination);
	}

	public void StartDate(DatePicker dateTimeEnd) {
		lblChoosedStartDate.setText(dateTimeEnd.toString());
	}

	public void EndDate(DatePicker dateTimeStart) {
		lblChoosedEndDate.setText(dateTimeStart.toString());
	}

	public void numberOfPeople(int people) {
		lblChoosedNumber.setText(String.valueOf(people));
	}

	public void loadHotelsToTableFromLabels() {
		String holidayTypeName = lblChoosedType.getText();
		String destinationName = lblChoosedDestination.getText();

		String query = "SELECT h.hotel_name, ht.type_name " + "FROM Hotels h "
				+ "JOIN Destinations d ON h.destination_id = d.destination_id "
				+ "JOIN HolidayTypes ht ON d.holiday_type_id = ht.holiday_type_id "
				+ "WHERE ht.type_name = ? AND d.destination_name = ?";

		try (Connection conn = db.getConnected(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, holidayTypeName);
			ps.setString(2, destinationName);

			ResultSet rs = ps.executeQuery();

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);

			while (rs.next()) {
				String hotelName = rs.getString("type_name");
				String typeName = rs.getString("hotel_name");
				model.addRow(new Object[] { hotelName, typeName });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HolidaySearchPage frame = new HolidaySearchPage();
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
	public HolidaySearchPage() {
		setTitle("Holiday Search Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1320, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(150, 29, 1005, 100);
		contentPane.add(panel);
		panel.setLayout(null);

		lblHolidayType = new JLabel("Holiday Type");
		lblHolidayType.setBounds(108, 10, 102, 21);
		panel.add(lblHolidayType);
		lblHolidayType.setFont(new Font("Segoe UI", Font.BOLD, 14));

		lblHolidayDestination = new JLabel("Holiday Destination");
		lblHolidayDestination.setBounds(240, 10, 135, 21);
		panel.add(lblHolidayDestination);
		lblHolidayDestination.setFont(new Font("Segoe UI", Font.BOLD, 14));

		lblHolidayStartDay = new JLabel("Holiday Start Date");
		lblHolidayStartDay.setBounds(415, 10, 143, 21);
		panel.add(lblHolidayStartDay);
		lblHolidayStartDay.setFont(new Font("Segoe UI", Font.BOLD, 14));

		lblHolidayEndDay = new JLabel("Holiday End Day");
		lblHolidayEndDay.setBounds(613, 10, 175, 21);
		panel.add(lblHolidayEndDay);
		lblHolidayEndDay.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblNumberOfPeople = new JLabel("Number of People");
		lblNumberOfPeople.setBounds(798, 10, 133, 21);
		panel.add(lblNumberOfPeople);
		lblNumberOfPeople.setFont(new Font("Segoe UI", Font.BOLD, 14));

		lblChoosedType = new JLabel("NULL");
		lblChoosedType.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblChoosedType.setBounds(108, 59, 142, 13);
		panel.add(lblChoosedType);

		lblChoosedDestination = new JLabel("NULL");
		lblChoosedDestination.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblChoosedDestination.setBounds(260, 59, 115, 13);
		panel.add(lblChoosedDestination);

		lblChoosedStartDate = new JLabel("NULL NULL NULL");
		lblChoosedStartDate.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblChoosedStartDate.setBounds(420, 59, 126, 13);
		panel.add(lblChoosedStartDate);

		lblChoosedEndDate = new JLabel("NULL NULL NULL");
		lblChoosedEndDate.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblChoosedEndDate.setBounds(607, 59, 126, 13);
		panel.add(lblChoosedEndDate);

		lblChoosedNumber = new JLabel("NULL");
		lblChoosedNumber.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblChoosedNumber.setBounds(823, 59, 45, 13);
		panel.add(lblChoosedNumber);

		JLabel lblAvailableRooms = new JLabel("Available Rooms |");
		lblAvailableRooms.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAvailableRooms.setBounds(37, 451, 151, 13);
		contentPane.add(lblAvailableRooms);

		JPanel pnlRoom1 = new JPanel();
		pnlRoom1.setBackground(SystemColor.inactiveCaptionBorder);
		pnlRoom1.setBounds(37, 497, 1259, 126);
		contentPane.add(pnlRoom1);
		pnlRoom1.setLayout(null);

		JLabel lblRoom1 = new JLabel("New label");
		lblRoom1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRoom1.setBounds(41, 51, 211, 40);
		pnlRoom1.add(lblRoom1);

		JLabel lblRoomInfo1 = new JLabel("New label");
		lblRoomInfo1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRoomInfo1.setBounds(244, 51, 435, 40);
		pnlRoom1.add(lblRoomInfo1);

		JLabel lblPrice1 = new JLabel("New label");
		lblPrice1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPrice1.setBounds(936, 51, 129, 40);
		pnlRoom1.add(lblPrice1);

		JLabel lblRoomCount1 = new JLabel("New label");
		lblRoomCount1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRoomCount1.setBounds(679, 51, 211, 40);
		pnlRoom1.add(lblRoomCount1);

		JLabel lblHotelName = new JLabel("New label");
		lblHotelName.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		lblHotelName.setBounds(171, 446, 263, 22);
		contentPane.add(lblHotelName);

		JPanel pnlRoom2 = new JPanel();
		pnlRoom2.setBackground(SystemColor.inactiveCaptionBorder);
		pnlRoom2.setBounds(37, 653, 1259, 126);
		contentPane.add(pnlRoom2);
		pnlRoom2.setLayout(null);

		JLabel lblRoom2 = new JLabel("New label");
		lblRoom2.setBounds(41, 51, 211, 40);
		lblRoom2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pnlRoom2.add(lblRoom2);

		JLabel lblRoomInfo2 = new JLabel("New label");
		lblRoomInfo2.setBounds(241, 51, 426, 40);
		lblRoomInfo2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pnlRoom2.add(lblRoomInfo2);

		JLabel lblPrice2 = new JLabel("New label");
		lblPrice2.setBounds(934, 52, 129, 40);
		lblPrice2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pnlRoom2.add(lblPrice2);

		JLabel lblRoomCount2 = new JLabel("New label");
		lblRoomCount2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRoomCount2.setBounds(682, 52, 211, 40);
		pnlRoom2.add(lblRoomCount2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
		scrollPane.setBounds(37, 178, 1244, 235);
		contentPane.add(scrollPane);

		model = new DefaultTableModel();
		model.addColumn("Hotel Destination");
		model.addColumn("Hotel Name");
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				setBounds(100, 100, 1320, 900);

				String selectedHotelName = table.getValueAt(selectedRow, 1).toString();
				lblHotelName.setText(selectedHotelName);
				int hotelId;
				try {
					hotelId = db.getHotelIdByName(selectedHotelName);
					int numberOfPeople = Integer.parseInt(lblChoosedNumber.getText());
					LocalDate startDate = LocalDate.parse(lblChoosedStartDate.getText());
					LocalDate endDate = LocalDate.parse(lblChoosedEndDate.getText());

					availableRooms = db.getRoomsByHotelIdAndAvailability(hotelId, startDate, endDate, numberOfPeople);

					db.fillRoomLabels(availableRooms, startDate, endDate, numberOfPeople, lblRoom1, lblRoomInfo1,
							lblRoomCount1, lblPrice1, lblRoom2, lblRoomInfo2, lblRoomCount2, lblPrice2, btnBuyOption1,
							btnBuyOption2);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(table);

		btnBuyOption1 = new JButton("Buy");
		btnBuyOption1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String hotel = lblHotelName.getText();
				String holidayDestination = lblChoosedDestination.getText();

				try {
					int holidayDestinationId = db.getHolidayDestinationIdByName(holidayDestination);
					int hotelId = db.getHotelIdByName(hotel);
					int roomId = db.getRoomIdByHotelIdAndRoomType(hotelId, lblRoom1.getText());

					LocalDate startDate = LocalDate.parse(lblChoosedStartDate.getText());
					LocalDate endDate = LocalDate.parse(lblChoosedEndDate.getText());
					int numberOfPeople = Integer.parseInt(lblChoosedNumber.getText());
					double price = Double.parseDouble(lblPrice1.getText().replace("€", "").trim().replace(",", "."));

					int choice = JOptionPane.showConfirmDialog(null, "Do you want to save reservation?", "Confirm",
							JOptionPane.YES_NO_OPTION);

					if (choice == JOptionPane.YES_OPTION) {

						boolean success = db.insertReservation(username, holidayDestinationId, hotelId, roomId,
								startDate, endDate, numberOfPeople, price);

						if (success) {
							for (Rooms room : availableRooms) {
								if (room.getMinAvailableQuantity() >= 1) {
									db.decreaseRoomAvailability(room.getRoomId(), startDate, endDate);
								}
							}

							JOptionPane.showMessageDialog(null, "Reservation saved!");
							UserPage user = new UserPage();
							user.labelUsername(username);
							user.setVisible(true);
							dispose();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Reservation not saved!");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnBuyOption1.setBounds(1126, 52, 107, 30);
		pnlRoom1.add(btnBuyOption1);
		btnBuyOption1.setOpaque(true);
		btnBuyOption1.setForeground(Color.WHITE);
		btnBuyOption1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBuyOption1.setBorderPainted(false);
		btnBuyOption1.setBackground(Color.RED);
		btnBuyOption1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnBuyOption2 = new JButton("Buy");
		btnBuyOption2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hotel = lblHotelName.getText();
				String holidayDestination = lblChoosedDestination.getText();

				try {
					int holidayDestinationId = db.getHolidayDestinationIdByName(holidayDestination);
					int hotelId = db.getHotelIdByName(hotel);
					int roomId = db.getRoomIdByHotelIdAndRoomType(hotelId, lblRoom2.getText());

					LocalDate startDate = LocalDate.parse(lblChoosedStartDate.getText());
					LocalDate endDate = LocalDate.parse(lblChoosedEndDate.getText());
					int numberOfPeople = Integer.parseInt(lblChoosedNumber.getText());
					double price = Double.parseDouble(lblPrice2.getText().replace("€", "").trim().replace(",", "."));

					int choice = JOptionPane.showConfirmDialog(null, "Do you want to save reservation?", "Confirm",
							JOptionPane.YES_NO_OPTION);

					if (choice == JOptionPane.YES_OPTION) {

						boolean success = db.insertReservation(username, holidayDestinationId, hotelId, roomId,
								startDate, endDate, numberOfPeople, price);

						if (success) {
							for (Rooms room : availableRooms) {
								if (room.getMinAvailableQuantity() >= 1) {
									db.decreaseRoomAvailability(room.getRoomId(), startDate, endDate);
								}
							}

							JOptionPane.showMessageDialog(null, "Reservation saved!");
							UserPage user = new UserPage();
							user.labelUsername(username);
							user.setVisible(true);
							dispose();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Reservation not saved!");
						}
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnBuyOption2.setBounds(1125, 52, 107, 30);
		btnBuyOption2.setOpaque(true);
		btnBuyOption2.setForeground(Color.WHITE);
		btnBuyOption2.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBuyOption2.setBorderPainted(false);
		btnBuyOption2.setBackground(Color.RED);
		pnlRoom2.add(btnBuyOption2);
		btnBuyOption2.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserPage user = new UserPage();
						user.labelUsername(username);
						user.setVisible(true);
						dispose();
					}
				});
			}
		});
		btnBack.setOpaque(true);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(37, 804, 108, 30);
		contentPane.add(btnBack);

	}
}
