# Final_Project_Prototype
## Project Title
ReadNest

## Team Members
Joseph Scott and Hung Nguyen

## Project Description
We have created a program for a simple Library Management System. Users can log in or register an account, then they enter the main menu where they type in the name of a book in the search, and if that book exists in our database the information of the book will be displayed (title, number of pages, description, whether it is an ebook or physical book).<br><br>
If the book is an eBook, the user can click the "Read" button, which will pull up a text file that is meant to represent waht would be the contents of the book for the user to read.<br><br>
Currently, the readable files do not contain a full book to read, but contain a single line as a placeholder for each book.<br><br>
If the book is a physical book, there is a "Check Out" button that is meant to simulate the user checking out a book from a library before reading it.

## How to Run
1. This project utilizes JavaFX for the front-end. When opening the project folder, this may require a user to set up JavaFX on their end.
2. After JavaFX is properly set up, the user must run the file Main.java in order to run the program.
3. When Main.java runs, a JavaFX window will pop up asking the user to log in or register an account
4. When clicking on the registration button it will pop up a new scene for the user to enter their username and password and then register (currently unavailable) using the register button or back to go back to log in menu
5. The user can log in by entering their username and password and then clicking the login button (there is currently only the admin login **username:** admin and **password:** password)
4. Currently, there are not many books in our database so the user has to know already what books exist before searching for a book. The user must also type in the name of the book exactly, or the program will say "Book not found".
5. After searching for an existing book, the user will see a new window displaying a "Back" button, the books information, and either a "Read" button or a "Check Out" button.
6. Pressing the back button reopens the window with the search bar.
7. If the book is listed as an eBook, the button will say "Read". If the book is listed as a physical book, the button will say "Check Out".
8. When the "Check Out" button is clicked, a window with a message saying "Book checked out" will be displayed along with a "Read" button. This is meant to simulate checking out a physical book library book before reading it, since we cannot implement real physical books into the program.
9. When the user clicks the "Read" button, a new window will appear, containing the contents of the book file. We do not have any full books in the files, so we use single sentence placeholders to show that the "Read" button works and displays the contents of a .txt file.
10. When the user is done reading the book, they can click on the "X" in the top right corner to close the window and return to the window with the book's information
11. From there, the user can click the "Read" button to reopen the .txt file, or they can press the "Back" button to return to the search bar.
12. If the user clicks the "X" in the top right corner to close the window, the program will terminate.

## Features Implemented
* JavaFX frontend
* a log in function that take in username and password
* Functioning search bar with a button
* Button to open a .txt file
* Button to simulate checking out a book before reading
* Back button to return to search bar
* Working database that can be updated from the program (BookManager.java)

## Future Work
* Registering account
* a more flush out log in where there different user login information
* Viewing books that have been checked out
* Return books
* Viewing available books without searching

## Known Issues
* Limited database size
* Books not listed for user
* User login not storing in database
* Register not set up yet

## Screenshots
Login Window: <br><br>
![Loghin Window](https://github.com/user-attachments/assets/676669f7-35ee-4792-86c8-dde831be4b74) <br><br>
Register Window: <br><br>
![Register Window](https://github.com/user-attachments/assets/4764b0b6-c080-4c8f-9421-71e110ccf729) <br><br>
Search Bar: <br><br>
![Search Bar](https://github.com/user-attachments/assets/1b1bd272-dc8a-449b-8f3f-799f49d914cc)<br><br>
Book Info: <br><br>
![Book Info](https://github.com/user-attachments/assets/1775f544-ab67-4f82-962a-ea7becd2f228)<br><br>
Book Content: <br><br>
![Book Content](https://github.com/user-attachments/assets/f138eac0-6398-4498-8147-634509a7f443)<br><br>
Checked Out: <br><br>
![Checked Out](https://github.com/user-attachments/assets/23e58d99-37ae-4086-9453-7557557b7227)<br><br>

## Video Link
[ReadNest Demo](https://drive.google.com/file/d/1HifOhbD4VNErRJWTX9nLWCkmZcG9tcwG/view?usp=sharing)
