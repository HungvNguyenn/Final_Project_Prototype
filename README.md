# Final_Project_Prototype
## Project Title

## Team Members
Joseph Scott and Hung Nguyen

## Project Description
We have created a program for a simple Library Management System. Users can type in the name of a book, and if that book exists in our database the information of the book will be displayed (title, number of pages, description, whether it is an ebook or physical book).<br><br>
If the book is an eBook, the user can click the "Read" button, which will pull up a text file that is meant to represent waht would be the contents of the book for the user to read.<br><br>
Currently, the readable files do not contain a full book to read, but contain a single line as a placeholder for each book.<br><br>
If the book is a physical book, there is a "Check Out" button that is meant to simulate the user checking out a book from a library before reading it.

## How to Run
1. This project utilizes JavaFX for the front-end. When opening the project folder, this may require a user to set up JavaFX on their end.
2. After JavaFX is properly set up, the user must run the file Main.java in order to run the program.
3. When Main.java runs, a JavaFX window will pop up displaying a search bar, prompting the user to type in the name of a book.
4. Currently, there are not many books in our database so the user has to already know what movies exist before searching for a book. The user must also type in the name of the book exactly, or the program will say "Book not found".
5. After searching for an existing book, the user will see a new window displaying a "Back" button, the books information, and either a "Read" button or a "Check Out" button.
6. Pressing the back button reopens the window with the search bar.
7. If the book is listed as an eBook, the button will say "Read". If the book is listed as a physical book, the button will say "Check Out".
8. When the "Check Out" button is clicked, a window with a message saying "Book checked out" will be displayed along with a "Read" button. This is meant to simulate checking out a pysical book library book before reading it, since we cannot implement real physical books into the program.
9. When the user clicks the "Read" button, a new window will appear, containing the contents of the book file. We do not have any full books in the files, so we use single sentence placeholders to show that the "Read" button works and displays the contents of a .txt file.
10. When the user is done reading the book, they can click on the "X" in the top right corner to close the window and return to the window with the book's information
11. From there, the user can click the "Read" button to reopen the .txt file, or they can press the "Back" button to return to the search bar.
12. If the user clicks the "X" in the top right corner to close the window, the program will terminate.

## Features Implemented
* JavaFX frontend
* Functioning search bar with a button
* Button to open a .txt file
* Button to simulate checking out a book before reading
* Back button to return to search bar
* Working database that can be updated from the program (BookManager.java)

## Future Work
* Login feature
* Viewing books that have been checked out
* Return books
* Viewing available books without searching

## Known Issues
