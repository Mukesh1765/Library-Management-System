# Library Management System

A comprehensive Java-based Library Management System that provides functionality for both students and librarians to manage books, users, and library operations efficiently.

## Features

### 🎓 Student Features
- **Account Management**: Login with student ID
- **Book Operations**: 
  - View available books
  - Borrow books (up to 14-day loan period)
  - Return books
  - View borrowed books and due dates
- **Extensions**: Request up to 2 extensions (7 days each) per book
- **Search**: Search books by category
- **Fines**: 
  - Check fine amounts ($0.50 per day overdue)
  - Pay fines online
  - Cannot borrow if fines exceed $10
- **Recommendations**: Get personalized book recommendations based on borrowing history
- **Notifications**: Receive overdue notices and new arrival alerts

### 👨‍💼 Librarian Features
- **Book Management**:
  - Add new books with categories
  - Remove books from inventory
  - Update book information (title, author, copies, category)
  - View all books and inventory status
- **User Management**:
  - Register new students and librarians
  - View all registered users
- **Search & Categories**:
  - Search books by title, author, or category
  - Manage book categories (add/remove)
- **Reports & Analytics**:
  - Most popular books report
  - Overdue books report
  - Fine collections summary
  - Book inventory status
- **Notifications**: Send overdue notices, new arrival alerts, and general announcements
- **Advanced Features**: Track borrowing patterns and manage high-demand books

## Technical Architecture

### Core Classes

#### User Management
- **`User`** (Abstract): Base class for all users
- **`Student`**: Extends User with borrowing capabilities and fine management
- **`Librarian`**: Extends User with administrative privileges

#### Book Management
- **`Book`**: Represents books with copy tracking and availability status
- **`BorrowedBook`**: Tracks borrowed books with due dates and extensions
- **`Library`**: Main system class managing all operations

#### Notification System
- **`Notification`**: Handles different types of notifications (overdue, new arrivals, general)

#### Exception Handling
- **`BookNotFoundException`**: When requested book doesn't exist
- **`BookNotAvailableException`**: When book is out of stock or already borrowed
- **`InvalidUserException`**: When user credentials are invalid

## Getting Started

### Prerequisites
- Java 8 or higher
- Java Development Kit (JDK)
- Command line interface or Java IDE

### Installation & Running

1. **Clone or Download** the project files
2. **Compile** the Java files:
   ```bash
   javac IOOM_ASSIGNMENTS/ASSIGNMENT6/LibraryManagementSystem.java
   ```
3. **Run** the application:
   ```bash
   java IOOM_ASSIGNMENTS.ASSIGNMENT6.LibraryManagementSystem
   ```

### Initial Setup

The system comes pre-loaded with:

**Sample Books:**
- The Great Gatsby by F. Scott Fitzgerald (ID: 101, 5 copies)
- To Kill a Mockingbird by Harper Lee (ID: 102, 3 copies)
- 1984 by George Orwell (ID: 103, 2 copies)

**Sample Users:**
- Student: Akash Puri (ID: S001, Email: john@example.com)
- Librarian: Puri Jagannadh (ID: L001, Email: jane@library.com)

## Usage Guide

### Student Workflow
1. **Login**: Select "Login as Student" and enter ID (e.g., S001)
2. **Browse**: View available books
3. **Borrow**: Select a book by ID (max 14-day loan)
4. **Manage**: Check due dates, request extensions, pay fines
5. **Return**: Return books when finished

### Librarian Workflow
1. **Login**: Select "Login as Librarian" and enter ID (e.g., L001)
2. **Manage Books**: Add, remove, or update book information
3. **User Management**: Register new users
4. **Reports**: Generate various analytical reports
5. **Notifications**: Send alerts to users

## Key Business Rules

### Borrowing Rules
- Students can borrow books for 14 days
- Maximum 2 extensions allowed (7 days each)
- Cannot borrow if fines exceed $10
- Cannot borrow the same book twice simultaneously

### Fine System
- $0.50 per day for overdue books
- Fines calculated automatically upon return
- Payment system integrated

### Book Management
- Each book can have multiple copies
- Availability tracked in real-time
- Categories help with organization and search

## Advanced Features

### Recommendation System
- Tracks student reading preferences by category
- Suggests popular books based on borrow count
- Highlights new arrivals

### Reporting Dashboard
- **Popular Books**: Ranked by borrow frequency
- **Overdue Analysis**: Detailed overdue tracking with fines
- **Inventory Status**: Stock levels and demand analysis
- **Financial Reports**: Fine collection summaries

### Notification System
- **Overdue Notices**: Automatic alerts for late returns
- **New Arrivals**: Notifications about recently added books
- **General Announcements**: System-wide messages

## Error Handling

The system includes comprehensive error handling for:
- Invalid user inputs
- Book not found scenarios
- Unavailable book situations
- User authentication failures
- Input validation errors

## Data Persistence

Currently, the system stores data in memory during runtime. For production use, consider integrating with:
- Database systems (MySQL, PostgreSQL)
- File-based storage

## Contributing

To contribute to this project:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is created for educational purposes as part of the IOOM (Introduction to Object-Oriented Modeling) coursework.

## Support

For questions or issues:
- Review the code documentation
- Check the error messages for guidance
- Refer to this README for feature explanations

---

**Author**: Mukesh Sai  
**Course**: Introduction to Object-Oriented Modeling  
**Version**: 1.0
