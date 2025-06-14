
# 🏨 Hotel Reservation System

It provides users with a fast and user-friendly hotel reservation experience through an interactive and rich graphical interface.

---

## 📌 Project Overview

The **Hotel Reservation System** is a comprehensive Java Swing application consisting of **7 interactive pages** designed to:

- Streamline hotel bookings  
- Manage user data and reservations  
- Offer a smooth and error-handled experience  

---

## 🚀 Features & Pages

### 🏠 Home Page

- Entry point of the system  
- Users can start their reservation process by clicking the **Login** button  

![Home Page](https://github.com/user-attachments/assets/7bb73ff1-8c76-43fa-9f93-e06c21eb0a08)

---

### 🔐 Login Page

- Allows existing users to log in  
- Redirects new users to the **Register Page**

**Special Features:**
- Validates inputs (empty fields or wrong credentials)  
- Displays appropriate warning messages  
- Successful login redirects to the user dashboard

![Login Page](https://github.com/user-attachments/assets/b8484adf-19be-4055-8bd8-d6d514bb5434)

---

### 📝 Register Page

- Quick and secure account creation for new users  
- Option to return to the login page  

**Special Features:**
- Gender selection using `ButtonGroup`  
- Data stored in SQL via `INSERT` commands  
- Secure password input with `passwordField`  
- Error handling for:
  - Password mismatch  
  - Already registered usernames or emails  

![Register Page](https://github.com/user-attachments/assets/d391d3c5-4345-46ef-9371-f2e74db131eb)

---

### 👤 User Page

- Central hub for users to:
  - Search for hotels  
  - View reservations  
  - Update personal information  

**Special Features:**
- Dynamic hotel search section expands upon clicking **Start Your Travel**  
- Holiday types and destinations are pulled from the SQL database and linked  
- `DateTimePicker` usage ensures:
  - No past dates  
  - End date is not before start date  
- Warnings appear if no room is available (criteria reset)

![User Page](https://github.com/user-attachments/assets/e37638ab-b10e-4cc3-aaea-ba2068b581f8)

---

### 🌴 Holiday Search Page

- Displays results based on:
  - Holiday type  
  - Region  
  - Dates  
  - Number of people  

- Options to reserve or return to the user page  

**Special Features:**
- Clicking hotel names in `JTable` shows detailed room info  
- **Buy** button:
  - Creates reservation  
  - Reduces room availability in SQL  
  - Disabled if no rooms are available  

![Holiday Search Page](https://github.com/user-attachments/assets/ab3a6b6d-e125-440e-9a09-7e837f35adf7)

---

### 📅 My Reservations Page

- View **upcoming** and **past** reservations  
- Cancel **upcoming** reservations if needed  

**Special Features:**
- Canceling a reservation increases room availability in the database  

![My Reservations Page](https://github.com/user-attachments/assets/135cd671-dcd8-4395-a2c7-a8f70aebf580)

---

### 🔧 Update Page

- Allows users to update:
  - Name  
  - Surname  
  - Gender  
  - Password  

- **Username and email** are **non-editable**

**Special Features:**
- Displays warnings for password mismatches  
- Updates are reflected immediately  

![Update Page](https://github.com/user-attachments/assets/1df5e57d-62fd-43bc-abfa-b54263d7478f)

---

## 🛠 Technologies and Tools

### GUI – Java Swing Components:

- `JLabel`  
- `JPanel`  
- `JTextField`  
- `JPasswordField`  
- `JButton`  
- `JComboBox`  
- `JTable`  
- `JRadioButton`  
- `ButtonGroup`  
- `DateTimePicker`  

### Backend:

- **Java**  
- **SQL Database** used for:
  - User data storage  
  - Room tracking  
  - Reservation management  

---

## 📚 License

This project was developed for educational purposes.  
You may reuse, modify, or distribute it **with credit** to the original author(s).
