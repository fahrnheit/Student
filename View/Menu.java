import java.util.ArrayList;
import java.util.Scanner;

import control.School;
import control.Validation;

public class Menu {
    private String title;
    private ArrayList<String> options = new ArrayList<String>();

    public Menu()
    {
        this.title = "----Student Managment System----";
        this.options.add("Display Student Informations");
        this.options.add("Sort and Print Student Information By Name");
        this.options.add("Count Nb of Student Same City");
        this.options.add("Update or Delete Student");
        this.options.add("Report");
        this.options.add("Create new Student");
        this.options.add("Exit");
    }
    
    public void displayMenu()
    {
        System.out.println("\n" + title);
        int count = 1;
        for (String option : options) {
            System.out.println(count + ". " + option);
            count++;
        }
        System.out.println();
    }

    public void Run()
    {
        Scanner sc = new Scanner(System.in);
        School school = new School();
        int choice;
        while(true)
        {
            displayMenu();
            choice = Validation.getAndCheckIntBorder(sc, "Enter your choice: ", 7);
            switch(choice)
            {
                case 1:
                    school.displayInfo();
                    break;
                case 2:
                    school.sortStudent();
                    break;
                case 3:
                    school.countCity();
                    break;
                case 4:
                    school.Update_Delete();
                    break;
                case 5:
                    school.report();
                    break;
                case 6:
                    school.addNew();
                    break;
                case 7:
                    return;
            }
        }
    }
}
