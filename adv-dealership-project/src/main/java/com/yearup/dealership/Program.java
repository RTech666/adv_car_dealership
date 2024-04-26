/* 
Program.java
This Java file just calls to the UserInterface to start the program.
*/

package com.yearup.dealership;

public class Program {
    public static void main(String[] args) {
        // Create the variable.
        UserInterface userInterface;

        // Initalize the variable.
        userInterface = new UserInterface();

        // Call the display method.
        userInterface.display();
    }
}
