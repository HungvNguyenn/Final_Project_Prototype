# Final_Project_Prototype
## Project Title
ReadNest

## Team Members
Joseph Scott and Hung Nguyen

## Project Description
We have created a program for a simple Library Management System. Users can log in or register an account, then they enter the main menu where the list of books to choose from is displayed and they can type in the name of a book in the search bar. If that book exists in our database, the information of the book will be displayed (title, number of pages, description, whether it is an ebook or physical book).<br><br>
If the book is an eBook, the user can click the "Read" button, which will pull up a text file that contains the book contents.<br><br>
In place of entire books in the .txt files for users to read, we are using either summaries of each book, or Wikipedia descriptions for some of the books that are from a series.<br><br>
If the book is a physical book, there is a "Check Out" button that is meant to simulate the user checking out a book from a library before reading it. 
After clicking check out, the user can either click the "Return" button to return the book and go back to the book info scene, or they can click the "Read" button, which will then display the book contents. <br><br>
### Note:
The database is currently being hosted locally, so in order to run the program, the database has to be imported using the file called "library_dump.sql"


## How To setup MySQL Workbench Database
1. Open MySQL Workbench
2. Create a schema in the localhost name librarymanagementsystem, then on the top left there is a Server tab hover over it and find Data import
3. Select Import from self-Contain File and then find the library_dump.sql, then Default Target Schema select librarymanagementsystem and then start import in the bottom
4. In intelliJ go to project structure, then module, then dependencies, click on the '+' and find the path to the mysql-connector-j-9.3.0.jar file and select that, click apply and then ok.
5. in the DBUtil.java file, change the URL ip if it not localhost, the username and password, the image below show the ip address (in our case it is localhost) and the username (in our case root)

![image](https://github.com/user-attachments/assets/4f2adc6b-6397-463d-a117-b2571775e527)

## How to Run
1. This project utilizes JavaFX for the front-end. When opening the project folder, this may require a user to set up JavaFX on their end as well as the MySQL database.
2. After JavaFX and MySQL are properly set up, the user must run the file Main.java in order to run the program.
3. When Main.java runs, a JavaFX window will pop up asking the user to log in, register an account, or exit.
4. When clicking on the registration button it will pop up a new scene for the user to enter a username and password and then register an account using the "Register" button or back to go back to login menu.
5. The user can log in by entering their registered username and password and then clicking the "Login" button. 
4. After logging in, the use will see a list of books present in the database. The user can search for a book by typing in a book name and then clicking the "Search" button. The user must also type in the name of one of these books book exactly as shown, or the program will say "Book not found".
5. After searching for an existing book, the user will see a new window displaying a "Back" button, the books information, and either a "Read" button or a "Check Out" button depending on whether the book is an Ebook or Physical Book.
6. Pressing the back button reopens the window with the search bar.
7. If the book is listed as an eBook, the button will say "Read". If the book is listed as a physical book, the button will say "Check Out".
8. When the "Check Out" button is clicked, a window with a message saying "Book checked out" will be displayed along with a "Return" and "Read" button. This is meant to simulate checking out a physical book library book before reading it, since we cannot implement real physical books into the program.
9. Clicking the "Return" button will take the user back to the scene displaying the book information, simulating returning a physical book after checking it out and reading it.
10. When the user clicks the "Read" button, a new window will appear, containing the contents of the book file. We do not have any full books in the files, so we use single sentence placeholders to show that the "Read" button works and displays the contents of a .txt file.
11. When the user is done reading the book, they can click on the "Back" button in the top left corner to return to the scene with the book's information.
13. From there, the user can click the "Read" button to reopen the .txt file if the books is an Ebook, they can click the "Check Out" button to check the book out again if it is a Physical Book, or they can press the "Back" button to return to the search bar.
14. If the user clicks on the "Signout" button in the top right corner, the user will be signed out and will return to the Login scene.
15. The user can then click the "Exit" button in the top right corner to terminate the program.


## Features Implemented
* JavaFX frontend
* Registering an account
* Not letting user register an account if the account already exists
* Login function that takes in username and password
* Functioning search bar with a button
* Button to open a .txt file
* Button to simulate checking out a book before reading
* Button to simulate returning a book after checking it out
* Back button to return to previous page
* Sign out button to go back to login page
* Exit button to terminate program
* Working database that can be updated from the program (BookManager.java)

## Future Work
* Encrypting passwords
* Viewing books that have been checked out
* More interactive check out/return books
* Full books rather than summaries
* Database hosted on a server rather than locally

## Known Issues
* Limited database size
* User has to import database
* Database hosted locally rather than on a server
* Book files stored locally 
* Passwords not encrypted

## Screenshots
Login Window: <br><br>
![Login Window](https://github.com/user-attachments/assets/9a81588a-f71a-414d-97a2-d62d91470180) <br><br>
Register Window: <br><br>
![Register Window](https://github.com/user-attachments/assets/33a51ed7-b438-43da-807e-42ede882e17c) <br><br>
Search Bar: <br><br>
![Book Info](https://github.com/user-attachments/assets/ef88fb85-f845-44a3-981c-d430b30d49eb)<br><br>
Book Info: <br><br>
![Book Info](https://github.com/user-attachments/assets/c5663b03-46ac-4fd1-a623-7e3d71826292)<br><br>
Book Content: <br><br>
![Book Contents](https://github.com/user-attachments/assets/88d48ab8-df8e-444f-bba9-33fd1086fdf8)<br><br>
Checked Out: <br><br>
![Checked Out](https://github.com/user-attachments/assets/5d6c4b0f-1078-48b9-a92a-f9527611e088)<br><br>

## Video Link
[ReadNest Demo](https://drive.google.com/file/d/1xk6BrmW9gPzdWaKs8z9s6xHsbY7auOoa/view?usp=sharing)

## Presentation Slides
[Presentation Slides](https://docs.google.com/presentation/d/1PyiXEKkdMlJtVLLLZpmlGUGzutaxkC08/edit?usp=sharing&ouid=107644666725365871856&rtpof=true&sd=true)
