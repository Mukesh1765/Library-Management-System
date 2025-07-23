package IOOM_ASSIGNMENTS.ASSIGNMENT6;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 101, 5));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 102, 3));
        library.addBook(new Book("1984", "George Orwell", 103, 2));
        
        library.registerUser(new Student("Akash Puri", "S001", "john@example.com"));
        library.registerUser(new Librarian("Puri Jagannadh", "L001", "jane@library.com"));
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            try {
                System.out.println("\n===== Library Management System =====");
                System.out.println("1. Login as Student");
                System.out.println("2. Login as Librarian");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        studentMenu(scanner, library);
                        break;
                    case 2:
                        librarianMenu(scanner, library);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Thank you for using the Library Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void studentMenu(Scanner scanner, Library library) {
        System.out.print("Enter your ID: ");
        String userId = scanner.nextLine();
        
        try {
            User user = library.getUserById(userId);
            if (!(user instanceof Student)) {
                throw new InvalidUserException("This ID does not belong to a student.");
            }
            
            Student student = (Student) user;
            boolean studentLoggedIn = true;
            
            while (studentLoggedIn) {
                System.out.println("\n===== Student Menu =====");
                System.out.println("Welcome, " + student.getName() + "!");
                System.out.println("1. View Available Books");
                System.out.println("2. Borrow a Book");
                System.out.println("3. Return a Book");
                System.out.println("4. View My Borrowed Books");
                System.out.println("5. Check Due Dates");
                System.out.println("6. Request Book Extension");
                System.out.println("7. Search Book by Category");
                System.out.println("8. Check Fine Amount");
                System.out.println("9. Pay Fine");
                System.out.println("10. Book Recommendation");
                System.out.println("11. Logout");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        library.displayAvailableBooks();
                        break;
                    case 2:
                        System.out.print("Enter Book ID to borrow: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine(); 
                        library.borrowBook(student, bookId);
                        break;
                    case 3:
                        System.out.print("Enter Book ID to return: ");
                        int returnBookId = scanner.nextInt();
                        scanner.nextLine(); 
                        library.returnBook(student, returnBookId);
                        break;
                    case 4:
                        student.displayBorrowedBooks();
                        break;
                    case 5:
                        student.checkDueDates();
                        break;
                    case 6:
                        System.out.print("Enter Book ID for extension: ");
                        int extendBookId = scanner.nextInt();
                        scanner.nextLine();
                        library.requestExtension(student, extendBookId);
                        break;
                    case 7:
                        System.out.print("Enter category to search: ");
                        String category = scanner.nextLine();
                        library.searchBookByCategory(category);
                        break;
                    case 8:
                        System.out.println("Your current fine is: $" + student.calculateFine());
                        break;
                    case 9:
                        System.out.print("Enter amount to pay: $");
                        double paymentAmount = scanner.nextDouble();
                        scanner.nextLine();
                        student.payFine(paymentAmount);
                        break;
                    case 10:
                        library.recommendBooks(student);
                        break;
                    case 11:
                        studentLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InvalidUserException | BookNotFoundException | BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void librarianMenu(Scanner scanner, Library library) {
        System.out.print("Enter your ID: ");
        String userId = scanner.nextLine();
        
        try {
            User user = library.getUserById(userId);
            if (!(user instanceof Librarian)) {
                throw new InvalidUserException("This ID does not belong to a librarian.");
            }
            
            Librarian librarian = (Librarian) user;
            boolean librarianLoggedIn = true;
            
            while (librarianLoggedIn) {
                System.out.println("\n===== Librarian Menu =====");
                System.out.println("Welcome, " + librarian.getName() + "!");
                System.out.println("1. Add New Book");
                System.out.println("2. Remove Book");
                System.out.println("3. View All Books");
                System.out.println("4. Register New User");
                System.out.println("5. View All Users");
                System.out.println("6. Search Book by Title");
                System.out.println("7. Search Book by Author");
                System.out.println("8. Generate Reports");
                System.out.println("9. Manage Book Categories");
                System.out.println("10. View Overdue Books");
                System.out.println("11. Send Notifications");
                System.out.println("12. Update Book Information");
                System.out.println("13. Logout");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Book ID: ");
                        int bookId = scanner.nextInt();
                        System.out.print("Enter Number of Copies: ");
                        int copies = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Category: ");
                        String category = scanner.nextLine();
                        
                        library.addBook(new Book(title, author, bookId, copies, category));
                        System.out.println("Book added successfully!");
                        break;
                    case 2:
                        System.out.print("Enter Book ID to remove: ");
                        int removeBookId = scanner.nextInt();
                        scanner.nextLine();
                        library.removeBook(removeBookId);
                        break;
                    case 3:
                        library.displayAllBooks();
                        break;
                    case 4:
                        System.out.println("Select user type:");
                        System.out.println("1. Student");
                        System.out.println("2. Librarian");
                        int userType = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        
                        if (userType == 1) {
                            library.registerUser(new Student(name, id, email));
                        } else if (userType == 2) {
                            library.registerUser(new Librarian(name, id, email));
                        } else {
                            System.out.println("Invalid user type!");
                        }
                        break;
                    case 5:
                        library.displayAllUsers();
                        break;
                    case 6:
                        System.out.print("Enter book title to search: ");
                        String searchTitle = scanner.nextLine();
                        library.searchBookByTitle(searchTitle);
                        break;
                    case 7:
                        System.out.print("Enter author name to search: ");
                        String searchAuthor = scanner.nextLine();
                        library.searchBookByAuthor(searchAuthor);
                        break;
                    case 8:
                        ((Librarian)user).generateReports(library);
                        break;
                    case 9:
                        manageCategoriesMenu(scanner, library);
                        break;
                    case 10:
                        library.displayOverdueBooks();
                        break;
                    case 11:
                        System.out.println("Select notification type:");
                        System.out.println("1. Overdue Notices");
                        System.out.println("2. New Arrivals");
                        System.out.println("3. General Announcement");
                        int notificationType = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Enter message: ");
                        String message = scanner.nextLine();
                        ((Librarian)user).sendNotifications(library, notificationType, message);
                        break;
                    case 12:
                        System.out.print("Enter Book ID to update: ");
                        int updateBookId = scanner.nextInt();
                        scanner.nextLine();
                        updateBookMenu(scanner, library, updateBookId);
                        break;
                    case 13:
                        librarianLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InvalidUserException | BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void manageCategoriesMenu(Scanner scanner, Library library) {
        boolean managing = true;
        
        while (managing) {
            System.out.println("\n===== Manage Categories =====");
            System.out.println("1. Add New Category");
            System.out.println("2. Remove Category");
            System.out.println("3. View All Categories");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new category name: ");
                    String newCategory = scanner.nextLine();
                    library.addCategory(newCategory);
                    break;
                case 2:
                    System.out.print("Enter category to remove: ");
                    String removeCategory = scanner.nextLine();
                    library.removeCategory(removeCategory);
                    break;
                case 3:
                    library.displayAllCategories();
                    break;
                case 4:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void updateBookMenu(Scanner scanner, Library library, int bookId) {
        try {
            Book book = library.getBookById(bookId);
            
            System.out.println("\n===== Update Book =====");
            System.out.println("Current Book Details: " + book);
            System.out.println("1. Update Title");
            System.out.println("2. Update Author");
            System.out.println("3. Update Number of Copies");
            System.out.println("4. Update Category");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    book.setTitle(newTitle);
                    System.out.println("Title updated successfully!");
                    break;
                case 2:
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    book.setAuthor(newAuthor);
                    System.out.println("Author updated successfully!");
                    break;
                case 3:
                    System.out.print("Enter new number of copies: ");
                    int newCopies = scanner.nextInt();
                    scanner.nextLine();
                    book.updateCopies(newCopies);
                    System.out.println("Copies updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    book.setCategory(newCategory);
                    System.out.println("Category updated successfully!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

abstract class User {
    private String name;
    private String userId;
    private String email;
    private List<Notification> notifications;
    
    public User(String name, String userId, String email) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.notifications = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void addNotification(Notification notification) {
        notifications.add(notification);
        System.out.println("Notification sent to " + name + ": " + notification.getMessage());
    }
    
    public void viewNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("No notifications");
            return;
        }
        
        System.out.println("\nNotifications for " + name + ":");
        for (Notification notification : notifications) {
            System.out.println(notification);
        }
    }
    
    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Email: " + email;
    }
}

class Student extends User {
    private List<BorrowedBook> borrowedBooks;
    private double fineAmount;
    private List<String> interestCategories;
    
    public Student(String name, String userId, String email) {
        super(name, userId, email);
        this.borrowedBooks = new ArrayList<>();
        this.fineAmount = 0.0;
        this.interestCategories = new ArrayList<>();
    }
    
    public void borrowBook(Book book) {
        Calendar calendar = Calendar.getInstance();
        Date borrowDate = calendar.getTime();
        
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date dueDate = calendar.getTime();
        
        BorrowedBook borrowedBook = new BorrowedBook(book, borrowDate, dueDate);
        borrowedBooks.add(borrowedBook);
        if (book.getCategory() != null && !book.getCategory().isEmpty() && 
            !interestCategories.contains(book.getCategory())) {
            interestCategories.add(book.getCategory());
        }
    }
    
    public boolean returnBook(Book book) {
        BorrowedBook bookToRemove = null;
        for (BorrowedBook borrowed : borrowedBooks) {
            if (borrowed.getBook().getBookId() == book.getBookId()) {
                bookToRemove = borrowed;
                break;
            }
        }
        
        if (bookToRemove != null) {
            double fine = calculateFineForBook(bookToRemove);
            if (fine > 0) {
                fineAmount += fine;
                System.out.println("Book is overdue! Fine added: $" + fine);
                System.out.println("Total fine amount: $" + fineAmount);
            }
            
            borrowedBooks.remove(bookToRemove);
            return true;
        }
        
        return false;
    }
    
    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books.");
            return;
        }
        
        System.out.println("\nYour Borrowed Books:");
        for (BorrowedBook book : borrowedBooks) {
            System.out.println(book);
        }
    }
    
    public void checkDueDates() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books.");
            return;
        }
        
        System.out.println("\nDue Dates for Your Books:");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (BorrowedBook book : borrowedBooks) {
            System.out.println(book.getBook().getTitle() + " - Due: " + sdf.format(book.getDueDate()));
        }
    }
    
    public double calculateFine() {
        double currentFine = fineAmount;
        
        for (BorrowedBook book : borrowedBooks) {
            currentFine += calculateFineForBook(book);
        }
        
        return currentFine;
    }
    
    private double calculateFineForBook(BorrowedBook book) {
        Date currentDate = Calendar.getInstance().getTime();
        Date dueDate = book.getDueDate();
        
        if (currentDate.after(dueDate)) {
            long diffInMillies = Math.abs(currentDate.getTime() - dueDate.getTime());
            long daysOverdue = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            
            return daysOverdue * 0.5;
        }
        
        return 0.0;
    }
    
    public void payFine(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        
        if (amount > fineAmount) {
            System.out.println("Payment amount exceeds fine. Paying full fine of $" + fineAmount);
            fineAmount = 0;
        } else {
            fineAmount -= amount;
            System.out.println("Payment of $" + amount + " processed. Remaining fine: $" + fineAmount);
        }
    }
    
    public List<String> getInterestCategories() {
        return interestCategories;
    }
    
    public void addInterestCategory(String category) {
        if (!interestCategories.contains(category)) {
            interestCategories.add(category);
            System.out.println("Added " + category + " to your interests!");
        }
    }
    
    @Override
    public String toString() {
        return "Student: " + super.toString() + ", Books Borrowed: " + borrowedBooks.size() + 
               ", Fine Amount: $" + fineAmount;
    }
}

class Librarian extends User {
    public Librarian(String name, String userId, String email) {
        super(name, userId, email);
    }
    
    public void generateReports(Library library) {
        System.out.println("\n===== Library Reports =====");
        System.out.println("1. Most Popular Books");
        System.out.println("2. Users with Overdue Books");
        System.out.println("3. Fine Collections Summary");
        System.out.println("4. Book Inventory Status");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select report to generate: ");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                System.out.println("\nMost Popular Books Report:");
                library.generatePopularBooksReport();
                break;
            case 2:
                System.out.println("\nUsers with Overdue Books Report:");
                library.generateOverdueReport();
                break;
            case 3:
                System.out.println("\nFine Collections Summary Report:");
                library.generateFineReport();
                break;
            case 4:
                System.out.println("\nBook Inventory Status Report:");
                library.generateInventoryReport();
                break;
            default:
                System.out.println("Invalid report selection.");
        }
        scanner.close();
    }
    
    public void sendNotifications(Library library, int notificationType, String message) {
        switch (notificationType) {
            case 1:
                library.sendOverdueNotifications(message);
                break;
            case 2:
                library.sendNewArrivalsNotifications(message);
                break;
            case 3:
                library.sendGeneralNotification(message);
                break;
            default:
                System.out.println("Invalid notification type.");
        }
    }
    
    @Override
    public String toString() {
        return "Librarian: " + super.toString();
    }
}

class Book {
    private String title;
    private String author;
    private int bookId;
    private boolean isAvailable;
    private int numberOfCopies;
    private int availableCopies;
    private String category;
    private Date addedDate;
    private int borrowCount;
    
    public Book(String title, String author, int bookId, int numberOfCopies) {
        this(title, author, bookId, numberOfCopies, "General");
    }
    
    public Book(String title, String author, int bookId, int numberOfCopies, String category) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.numberOfCopies = numberOfCopies;
        this.availableCopies = numberOfCopies;
        this.isAvailable = numberOfCopies > 0;
        this.category = category;
        this.addedDate = Calendar.getInstance().getTime();
        this.borrowCount = 0;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public int getNumberOfCopies() {
        return numberOfCopies;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Date getAddedDate() {
        return addedDate;
    }
    
    public int getBorrowCount() {
        return borrowCount;
    }
    
    public void incrementBorrowCount() {
        borrowCount++;
    }
    
    public void borrowCopy() throws BookNotAvailableException {
        if (availableCopies > 0) {
            availableCopies--;
            if (availableCopies == 0) {
                isAvailable = false;
            }
            borrowCount++;
        } else {
            throw new BookNotAvailableException("No copies available for " + title);
        }
    }
    
    public void returnCopy() {
        availableCopies++;
        isAvailable = true;
    }
    
    public void updateCopies(int newTotalCopies) {
        int difference = newTotalCopies - numberOfCopies;
        numberOfCopies = newTotalCopies;
        availableCopies += difference;
        if (availableCopies < 0) availableCopies = 0;
        isAvailable = availableCopies > 0;
    }
    
    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + 
               ", Category: " + category + ", Available Copies: " + availableCopies + "/" + numberOfCopies;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Book book = (Book) obj;
        return bookId == book.bookId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}

class BorrowedBook {
    private Book book;
    private Date borrowDate;
    private Date dueDate;
    private int extensionCount;
    
    public BorrowedBook(Book book, Date borrowDate, Date dueDate) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.extensionCount = 0;
    }
    
    public Book getBook() {
        return book;
    }
    
    public Date getBorrowDate() {
        return borrowDate;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    
    public int getExtensionCount() {
        return extensionCount;
    }
    
    public boolean extendDueDate() {
        if (extensionCount < 2) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dueDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            dueDate = calendar.getTime();
            extensionCount++;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return book.toString() + ", Borrowed on: " + sdf.format(borrowDate) + ", Due by: " + sdf.format(dueDate) + ", Extensions used: " + extensionCount + "/2";
    }
}

class Notification {
    private String message;
    private Date date;
    private NotificationType type;
    
    public enum NotificationType {
        OVERDUE,
        NEW_ARRIVAL,
        GENERAL
    }
    
    public Notification(String message, NotificationType type) {
        this.message = message;
        this.date = Calendar.getInstance().getTime();
        this.type = type;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Date getDate() {
        return date;
    }
    
    public NotificationType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "[" + sdf.format(date) + "] " + type + ": " + message;
    }
}

class Library {
    private Map<Integer, Book> books;
    private Map<String, User> users;
    private Set<String> categories;
    private List<Book> recentlyAddedBooks;
    
    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.categories = new HashSet<>();
        this.categories.add("General");
        this.recentlyAddedBooks = new ArrayList<>();
    }
    
    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        if (book.getCategory() != null && !book.getCategory().isEmpty()) {
            categories.add(book.getCategory());
        }
        recentlyAddedBooks.add(book);
        if (recentlyAddedBooks.size() > 10) {
            recentlyAddedBooks.remove(0);
        }
    }
    
    public void removeBook(int bookId) throws BookNotFoundException {
        if (!books.containsKey(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        books.remove(bookId);
        System.out.println("Book removed successfully!");
    }
    
    public void registerUser(User user) {
        users.put(user.getUserId(), user);
        System.out.println("User registered successfully!");
    }
    
    public User getUserById(String userId) throws InvalidUserException {
        User user = users.get(userId);
        if (user == null) {
            throw new InvalidUserException("User with ID " + userId + " not found.");
        }
        return user;
    }
    
    public Book getBookById(int bookId) throws BookNotFoundException {
        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        return book;
    }
    
    public void borrowBook(Student student, int bookId) throws BookNotFoundException, BookNotAvailableException {
        Book book = getBookById(bookId);
        
        for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
            if (borrowedBook.getBook().getBookId() == bookId) {
                throw new BookNotAvailableException("You have already borrowed this book.");
            }
        }
        
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is not available for borrowing.");
        }
        
        if (student.calculateFine() > 10.0) {
            throw new BookNotAvailableException("You have unpaid fines over $10. Please pay your fines before borrowing.");
        }
        
        book.borrowCopy();
        student.borrowBook(book);
        
        Calendar calendar = Calendar.getInstance();
        Date borrowDate = calendar.getTime();
        
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date dueDate = calendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Book borrowed successfully: " + book.getTitle());
        System.out.println("Borrow date: " + sdf.format(borrowDate));
        System.out.println("Due date: " + sdf.format(dueDate));
    }
    
    public void returnBook(Student student, int bookId) throws BookNotFoundException {
        Book bookToReturn = null;
        
        for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
            if (borrowedBook.getBook().getBookId() == bookId) {
                bookToReturn = borrowedBook.getBook();
                break;
            }
        }
        
        if (bookToReturn == null) {
            throw new BookNotFoundException("You have not borrowed this book.");
        }
        
        Book originalBook = getBookById(bookId);
        originalBook.returnCopy();
        student.returnBook(bookToReturn);
        System.out.println("Book returned successfully: " + bookToReturn.getTitle());
    }
    
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        
        System.out.println("\nAll Books in Library:");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }
    
    public void displayAvailableBooks() {
        boolean found = false;
        System.out.println("\nAvailable Books:");
        
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No books are currently available.");
        }
    }
    
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        
        System.out.println("\nAll Registered Users:");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }
    
    public void searchBookByTitle(String title) {
        boolean found = false;
        System.out.println("\nSearch Results for Title: " + title);
        
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No books found with title: " + title);
        }
    }
    
    public void searchBookByAuthor(String author) {
        boolean found = false;
        System.out.println("\nSearch Results for Author: " + author);
        
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No books found by author: " + author);
        }
    }
    
    public void searchBookByCategory(String category) {
        boolean found = false;
        System.out.println("\nSearch Results for Category: " + category);
        
        for (Book book : books.values()) {
            if (book.getCategory().toLowerCase().contains(category.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No books found in category: " + category);
        }
    }
    
    public void addCategory(String category) {
        if (categories.add(category)) {
            System.out.println("Category '" + category + "' added successfully!");
        } else {
            System.out.println("Category already exists!");
        }
    }
    
    public void removeCategory(String category) {
        if (category.equalsIgnoreCase("General")) {
            System.out.println("Cannot remove the General category!");
            return;
        }
        
        if (categories.remove(category)) {
            for (Book book : books.values()) {
                if (book.getCategory().equalsIgnoreCase(category)) {
                    book.setCategory("General");
                }
            }
            System.out.println("Category '" + category + "' removed successfully!");
        } else {
            System.out.println("Category not found!");
        }
    }
    
    public void displayAllCategories() {
        System.out.println("\nBook Categories:");
        for (String category : categories) {
            System.out.println("- " + category);
        }
    }
    
    public void requestExtension(Student student, int bookId) {
        try {
            BorrowedBook bookToExtend = null;
            for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
                if (borrowedBook.getBook().getBookId() == bookId) {
                    bookToExtend = borrowedBook;
                    break;
                }
            }
            
            if (bookToExtend == null) {
                System.out.println("You have not borrowed this book.");
                return;
            }
            
            if (bookToExtend.extendDueDate()) {
                System.out.println("Due date extended successfully for: " + bookToExtend.getBook().getTitle());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("New due date: " + sdf.format(bookToExtend.getDueDate()));
            } else {
                System.out.println("Extension not allowed. Maximum extensions (2) already used.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void displayOverdueBooks() {
        boolean found = false;
        System.out.println("\nOverdue Books:");
        
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (User user : users.values()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
                    if (currentDate.after(borrowedBook.getDueDate())) {
                        System.out.println("User: " + student.getName() + " (ID: " + student.getUserId() + ")");
                        System.out.println("Book: " + borrowedBook.getBook().getTitle());
                        System.out.println("Due Date: " + sdf.format(borrowedBook.getDueDate()));
                        System.out.println("Days Overdue: " + 
                                TimeUnit.DAYS.convert(
                                    Math.abs(currentDate.getTime() - borrowedBook.getDueDate().getTime()),
                                    TimeUnit.MILLISECONDS));
                        System.out.println("--------------------------");
                        found = true;
                    }
                }
            }
        }
        
        if (!found) {
            System.out.println("No overdue books found.");
        }
    }
    
    public void recommendBooks(Student student) {
        System.out.println("\nBook Recommendations for " + student.getName() + ":");
        
        List<String> interests = student.getInterestCategories();
        if (!interests.isEmpty()) {
            System.out.println("\nBased on your interests:");
            boolean foundInterestMatch = false;
            
            for (Book book : books.values()) {
                if (interests.contains(book.getCategory())) {
                    boolean alreadyBorrowed = false;
                    for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
                        if (borrowedBook.getBook().getBookId() == book.getBookId()) {
                            alreadyBorrowed = true;
                            break;
                        }
                    }
                    
                    if (!alreadyBorrowed && book.isAvailable()) {
                        System.out.println("- " + book.getTitle() + " by " + book.getAuthor() +  " (Category: " + book.getCategory() + ")");
                        foundInterestMatch = true;
                    }
                }
            }
            
            if (!foundInterestMatch) {
                System.out.println("No matching books found in your interest categories.");
            }
        }

        System.out.println("\nPopular books you might enjoy:");
        
        List<Book> popularBooks = new ArrayList<>(books.values());
        popularBooks.sort((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()));
        
        int recommendCount = 0;
        for (Book book : popularBooks) {
            boolean alreadyBorrowed = false;
            for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
                if (borrowedBook.getBook().getBookId() == book.getBookId()) {
                    alreadyBorrowed = true;
                    break;
                }
            }
            
            if (!alreadyBorrowed && book.isAvailable() && book.getBorrowCount() > 0) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() +  " (Borrowed " + book.getBorrowCount() + " times)");
                recommendCount++;
            }
            
            if (recommendCount >= 5) break;
        }
        
        if (recommendCount == 0) {
            System.out.println("No popular books available to recommend at this time.");
        }
        
        if (!recentlyAddedBooks.isEmpty()) {
            System.out.println("\nNew arrivals you might be interested in:");
            for (int i = recentlyAddedBooks.size() - 1; i >= Math.max(0, recentlyAddedBooks.size() - 3); i--) {
                Book book = recentlyAddedBooks.get(i);
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
    
    public void generatePopularBooksReport() {
        List<Book> popularBooks = new ArrayList<>(books.values());
        popularBooks.sort((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()));
        
        System.out.println("\nPopular Books Report:");
        System.out.println("---------------------");
        System.out.println("Rank | Book Title                     | Author             | Times Borrowed");
        System.out.println("------------------------------------------------------------------");
        
        int rank = 1;
        for (Book book : popularBooks) {
            if (book.getBorrowCount() > 0) {
                String title = book.getTitle();
                if (title.length() > 30) {
                    title = title.substring(0, 27) + "...";
                } else {
                    title = String.format("%-30s", title);
                }
                
                String author = book.getAuthor();
                if (author.length() > 18) {
                    author = author.substring(0, 15) + "...";
                } else {
                    author = String.format("%-18s", author);
                }
                
                System.out.printf("%-4d | %s | %s | %d\n", rank, title, author, book.getBorrowCount());
                rank++;
            }
            
            if (rank > 10) break;
        }
        
        if (rank == 1) {
            System.out.println("No books have been borrowed yet.");
        }
    }
    
    public void generateOverdueReport() {
        boolean found = false;
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        System.out.println("\nOverdue Books Report:");
        System.out.println("--------------------");
        System.out.println("Student ID | Student Name       | Book Title                  | Due Date   | Days Overdue | Fine");
        System.out.println("----------------------------------------------------------------------------------------");
        
        for (User user : users.values()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
                    if (currentDate.after(borrowedBook.getDueDate())) {
                        found = true;
                        long daysOverdue = TimeUnit.DAYS.convert(
                            Math.abs(currentDate.getTime() - borrowedBook.getDueDate().getTime()),
                            TimeUnit.MILLISECONDS);
                        
                        double fine = daysOverdue * 0.5;
                        
                        String studentName = student.getName();
                        if (studentName.length() > 18) {
                            studentName = studentName.substring(0, 15) + "...";
                        } else {
                            studentName = String.format("%-18s", studentName);
                        }
                        
                        String bookTitle = borrowedBook.getBook().getTitle();
                        if (bookTitle.length() > 25) {
                            bookTitle = bookTitle.substring(0, 22) + "...";
                        } else {
                            bookTitle = String.format("%-25s", bookTitle);
                        }
                        
                        String formattedDueDate = sdf.format(borrowedBook.getDueDate());
                        
                        System.out.printf("%-10s | %s | %s | %-10s | %-11d | $%.2f\n", 
                                        student.getUserId(), studentName, bookTitle, 
                                        formattedDueDate, daysOverdue, fine);
                    }
                }
            }
        }
        
        if (!found) {
            System.out.println("No overdue books found.");
        }
    }
    
    public void generateFineReport() {
        boolean found = false;
        double totalFines = 0.0;
        
        System.out.println("\nFine Collections Report:");
        System.out.println("---------------------");
        System.out.println("Student ID | Student Name       | Fine Amount");
        System.out.println("----------------------------------------");
        
        for (User user : users.values()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                double fine = student.calculateFine();
                
                if (fine > 0) {
                    found = true;
                    totalFines += fine;
                    
                    String studentName = student.getName();
                    if (studentName.length() > 18) {
                        studentName = studentName.substring(0, 15) + "...";
                    } else {
                        studentName = String.format("%-18s", studentName);
                    }
                    
                    System.out.printf("%-10s | %s | $%.2f\n", student.getUserId(), studentName, fine);
                }
            }
        }
        
        if (!found) {
            System.out.println("No outstanding fines found.");
        } else {
            System.out.println("----------------------------------------");
            System.out.printf("Total Outstanding Fines: $%.2f\n", totalFines);
        }
    }
    
    public void generateInventoryReport() {
        int totalBooks = 0;
        int totalAvailable = 0;
        int totalBorrowed = 0;
        Map<String, Integer> categoryCount = new HashMap<>();
        
        for (Book book : books.values()) {
            totalBooks += book.getNumberOfCopies();
            totalAvailable += book.getAvailableCopies();
            totalBorrowed += (book.getNumberOfCopies() - book.getAvailableCopies());
            
            String category = book.getCategory();
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + book.getNumberOfCopies());
        }
        
        System.out.println("\nBook Inventory Report:");
        System.out.println("--------------------");
        System.out.println("Total Books: " + totalBooks);
        System.out.println("Available Books: " + totalAvailable);
        System.out.println("Borrowed Books: " + totalBorrowed);
        
        if (!categoryCount.isEmpty()) {
            System.out.println("\nBooks by Category:");
            System.out.println("----------------");
            for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
                System.out.printf("%-15s: %d\n", entry.getKey(), entry.getValue());
            }
        }
        
        List<Book> highDemandBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAvailableCopies() == 0 && book.getBorrowCount() > 3) {
                highDemandBooks.add(book);
            }
        }
        
        if (!highDemandBooks.isEmpty()) {
            System.out.println("\nHigh Demand Books (Consider adding more copies):");
            System.out.println("----------------------------------------------");
            for (Book book : highDemandBooks) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (Borrowed " + book.getBorrowCount() + " times)");
            }
        }
    }
    
    public void sendOverdueNotifications(String additionalMessage) {
        boolean sent = false;
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (User user : users.values()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                List<String> overdueBooks = new ArrayList<>();
                Map<String, String> bookDueDates = new HashMap<>();
                
                for (BorrowedBook borrowedBook : student.getBorrowedBooks()) {
                    if (currentDate.after(borrowedBook.getDueDate())) {
                        String bookTitle = borrowedBook.getBook().getTitle();
                        overdueBooks.add(bookTitle);
                        bookDueDates.put(bookTitle, sdf.format(borrowedBook.getDueDate()));
                    }
                }
                
                if (!overdueBooks.isEmpty()) {
                    StringBuilder message = new StringBuilder("OVERDUE NOTICE: You have overdue book(s):\n");
                    for (int i = 0; i < overdueBooks.size(); i++) {
                        String bookTitle = overdueBooks.get(i);
                        message.append("- ").append(bookTitle)
                               .append(" (Due: ").append(bookDueDates.get(bookTitle)).append(")");
                        if (i < overdueBooks.size() - 1) {
                            message.append("\n");
                        }
                    }
                    
                    if (additionalMessage != null && !additionalMessage.isEmpty()) {
                        message.append("\n\n").append(additionalMessage);
                    }
                    
                    student.addNotification(new Notification(message.toString(), Notification.NotificationType.OVERDUE));
                    sent = true;
                }
            }
        }
        
        if (sent) {
            System.out.println("Overdue notifications sent successfully!");
        } else {
            System.out.println("No overdue books found. No notifications sent.");
        }
    }
    
    public void sendNewArrivalsNotifications(String message) {
        if (recentlyAddedBooks.isEmpty()) {
            System.out.println("No recent books to notify about.");
            return;
        }
        
        StringBuilder notification = new StringBuilder("NEW ARRIVALS: ");
        for (int i = recentlyAddedBooks.size() - 1; i >= Math.max(0, recentlyAddedBooks.size() - 3); i--) {
            Book book = recentlyAddedBooks.get(i);
            notification.append(book.getTitle()).append(" by ").append(book.getAuthor());
            if (i > Math.max(0, recentlyAddedBooks.size() - 3)) {
                notification.append(", ");
            }
        }
        
        if (message != null && !message.isEmpty()) {
            notification.append("\n").append(message);
        }
        
        for (User user : users.values()) {
            user.addNotification(new Notification(notification.toString(), Notification.NotificationType.NEW_ARRIVAL));
        }
        
        System.out.println("New arrivals notifications sent to all users!");
    }
    
    public void sendGeneralNotification(String message) {
        if (message == null || message.isEmpty()) {
            System.out.println("Empty message. Notification not sent.");
            return;
        }
        
        for (User user : users.values()) {
            user.addNotification(new Notification(message, Notification.NotificationType.GENERAL));
        }
        
        System.out.println("General notification sent to all users!");
    }
}

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}

class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}