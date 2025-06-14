📌 Project Overview
The Hotel Reservation System is a comprehensive Java Swing application consisting of 7 interactive pages designed to streamline hotel bookings and manage user information and reservations efficiently.

🚀 Features & Pages
**🏠 Home Page**
Initial entry point of the system.

Users can begin the reservation process by clicking the Login button.

![Home Page](https://github.com/user-attachments/assets/7bb73ff1-8c76-43fa-9f93-e06c21eb0a08)

**🔐 Login Page**
Allows existing users to access their accounts.

Provides navigation to the Register page for new users.

![Login Page](https://github.com/user-attachments/assets/b8484adf-19be-4055-8bd8-d6d514bb5434)

Special Features:
Input validation: incorrect username/password or empty fields show warning messages.

Successful login redirects to the user dashboard.

**📝 Register Page**
Quick and secure account creation for new users.

Redirect option to the login page for existing users.

Special Features:
Gender selection via Button Group.

Stores user data in SQL database using INSERT statements.

Uses passwordField for secure input.

Displays errors for:

Password mismatch

Duplicate username or email

![Register Page](https://github.com/user-attachments/assets/d391d3c5-4345-46ef-9371-f2e74db131eb)

**👤 User Page**
Central page for users to:

Search for hotels

View reservations

Update personal information

Special Features:
Dynamic expansion of the hotel search section upon clicking "Start Your Travel".

Holiday types and destinations pulled from SQL and are interlinked to avoid invalid selections.

Date pickers restrict:

Past date selections

End dates before start dates

Warnings and reset for unavailable rooms.

![User Page](https://github.com/user-attachments/assets/e37638ab-b10e-4cc3-aaea-ba2068b581f8)

**🌴 Holiday Search Page**
Displays filtered results based on:

Holiday type

Region

Dates

Number of guests

Enables reservation or navigation back to user page.

Special Features:
Hotel name click in JTable shows room details dynamically.



"Buy" button:

Creates reservation

Reduces room count in the database

Disabled if room is "Not Available"

![Holiday Search Page](https://github.com/user-attachments/assets/ab3a6b6d-e125-440e-9a09-7e837f35adf7)

**📅 My Reservations Page**
View upcoming and past reservations.

Cancel upcoming reservations if needed.

Special Features:
Cancelling increases room availability in the database.

![My Reservations Page](https://github.com/user-attachments/assets/135cd671-dcd8-4395-a2c7-a8f70aebf580)

**🔧 Update Page**
Update personal details (name, surname, gender, password).

Username and email cannot be changed.

Special Features:
Validates password match.

Changes are reflected when user returns to the page.


![Update Page](https://github.com/user-attachments/assets/1df5e57d-62fd-43bc-abfa-b54263d7478f)

**🛠 Technologies and Tools**
GUI Components (Java Swing)
JLabel

JPanel

JTextField

JPasswordField

JButton

JComboBox

JTable

JRadioButton

ButtonGroup

DateTimePicker

Backend
Java

SQL Database for:

User data management

Room availability

Reservation tracking





📚 License
This project was developed for educational purposes and may be reused or modified with credit.
