Academic Manager
< Overview >

The Academic App is a Java-based console application designed for educational institutions. 
It supports two types of users: students and teachers. 
Students can manage their course sections and view their grades, while teachers can assign grades and manage assignments.

This application features user authentication, role-specific functionalities, and interactive command-line menus. 
It is built using object-oriented programming (OOP) principles, with separate classes for users, courses, sections, grades, and assignments.

- Features : 
> Student Features :
  Enroll in Sections – Students can add themselves to available course sections.
  Drop Sections – Students can remove themselves from a section they previously enrolled in.
  View Grades – Students can check their grades for completed assignments or exams.
  Student functionality remains unchanged from the original implementation.

> Teacher Features :
  Manage Student Grades
  Add, edit, or delete student grades for specific courses and sections.
  View a list of all assigned grades.
  Manage Assignments
  Create new assignments for students.
  Edit assignment details or due dates.
  Remove incorrect assignments.

> General Features :
  User Authentication – Users log in with a unique ID and password stored in a CSV file.
  Interactive Console Menus – Step-by-step prompts allow users to navigate the app easily.
  Data Persistence – User data and schedules are stored in external files for future access.

- Project Files :
The application consists of the following Java files:
> Main.java – The entry point of the application
> User.java, Student.java, Teacher.java – Defines user roles
> Course.java, Section.java – Manages course and section data
> Grade.java, Assignment.java – Handles grading and assignments
> Date.java – Provides date parsing and formatting
> UserManager.java – Handles user authentication and data retrieval

- Usage :
> Logging In
> Upon launching the application, users must enter their User ID and Password to log in.
  These credentials are stored in a CSV file and verified during authentication.

> Student Actions :
  1. Manage Sections :
     Add a section by entering the course name and section name.
     Drop a section if no longer needed.
     View a list of enrolled sections.
  2. View Grades
     Display all grades assigned to the student.
     Teacher Actions
> Teacher Actions :
  1. Manage Student Grades :
     Assign grades to students by selecting a course, section, and entering a grade value.
     Modify existing grades.
     Delete incorrect or outdated grades.
     View a list of all student grades.
  2. Manage Assignments :
     Create new assignments for specific courses.
     Edit or update assignment details and deadlines.
     Remove completed or irrelevant assignments.

- Code Structure :

Main Application (Main.java)

- Handles :

> User authentication – Checks login credentials.

> Role selection – Determines if the user is a student or teacher.

> Menu navigation – Provides interactive options based on user role.


- User and Role Classes :

> User.java – Abstract base class for all users.

> Student.java – Extends User with student-specific functionalities (e.g., section enrollment).

> Teacher.java – Extends User with teacher-specific functionalities (e.g., grading, assignments).


- Data Management Classes :

> Course.java – Stores course details.

> Section.java – Manages individual course sections.

> Grade.java – Represents a student’s performance in a course.

> Assignment.java – Stores assignment information.

> Date.java – Provides a simple date-handling utility.

> UserManager.java – Reads and verifies user credentials from a CSV file.


* This README provides clear instructions and details for users and developers. *
