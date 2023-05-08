package control;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import model.*;

public class School {
    private ArrayList<Student> list = new ArrayList<Student>();

    public School()
    {
        Address address01 = new Address("France", "Lyon", "Guillotière", "6 Rue Placé");
        BizStudent student01 = new BizStudent("1", "Antoine Daniel", address01, 6, 4);
        Address address02 = new Address("France", "Lyon", "Guillotière", "12 rue Nation");
        BizStudent student02 = new BizStudent("2", "Patrik Jolie", address02, 10, 0);
        Address address03 = new Address("Vietnam", "Da Nang", "Old center", "Nguyen Street");
        BizStudent student03 = new BizStudent("3", "Alexandre Poste", address03, 0, 0);
        Address address04 = new Address("VietNam", "Da Nang", "Old Center", "Tan tra Street");
        BizStudent student04 = new BizStudent("4", "Maxelle Néo", address04, 10, 7);
        Address address05 = new Address("VietNam", "Da Nang", "Old Center", "Mai Di Street");
        BizStudent student05 = new BizStudent("5", "Eric Macron", address05, 10, 10);
        ITStudent student06 = new ITStudent("6", "Pierre Fichier", new Address("VietNam", "Da Nang", "Old Center", "Mai Di Street"), 9, 15);
        ITStudent student07 = new ITStudent("7", "Clement Hugati", new Address("VietNam", "Da Nang", "Old Center", "K49 Binh Street"), 10, 7);
        ITStudent student08 = new ITStudent("8", "Max Yrugu", new Address("VietNam", "Da Nang", "Old Center", "Le Ba Street"), 11, 12);
        ITStudent student09 = new ITStudent("9", "Frank Solère", new Address("US", "NYC", "Old Center", "Banh mi street"), 20, 18);
        ITStudent student10 = new ITStudent("10", "Mike Dianiel", new Address("France", "Lille", "Old Center", "Rue du MDG"), 3, 19);

        list.add(student01);
        list.add(student02);
        list.add(student03);
        list.add(student04);
        list.add(student05);
        list.add(student06);
        list.add(student07);
        list.add(student08);
        list.add(student09);
        list.add(student10);
    }

    public ArrayList<Student> getList() {
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }

    
    public void addNew()
    {
        Scanner sc = new Scanner(System.in);
        String studentType = Validation.getValue(sc, "Enter student type (IT / BIZ): ");
        while(!studentType.equals("IT") && !studentType.equals("BIZ"))
        {
            studentType = Validation.getValue(sc, "Enter IT or BIZ for student type: ");
        }
        boolean test = true;
        String id = "";
        while(test)
        {
            id = Validation.getValue(sc, "enter ID: ");
            boolean tr = false;
            for (Student student : list) 
            {
                if(student.getId().equals(id))
                {
                    System.out.println("ID already taken!");
                    tr = true;
                }
            }
            if(!tr){
                test = false;
            }
        }
        String fullName = Validation.getValue(sc, "Enter full name: ");
        String country = Validation.getValue(sc, "Enter country: ");
        test = true;
        String city = "";
        int count;
        while(test)
        {
            count = 0;
            city = Validation.getValue(sc, "Enter city: ");
            for (Student student : list) 
            {
                if(student.getAddress().getCity().equals(city))
                {
                    if(studentType.equals("IT") && student instanceof ITStudent)
                    {
                        count++;
                    }
                    if(studentType.equals("BIZ") && student instanceof BizStudent)
                    {
                        count++;
                    }
                }
            }
            if(count < 3){
                test = false;
            }
            else{
                System.out.println("There are already 3 students in this faculty in Da Nang city");
            }
        }
        String district = Validation.getValue(sc, "Enter district: ");
        String street = Validation.getValue(sc, "Enter street: ");
        Address address = new Address(country, city, district, street);
        switch(studentType)
        {
            case "IT":
                int java = Validation.getAndCheckInt(sc, "Enter java score: ");
                int css = Validation.getAndCheckInt(sc, "Enter css score: ");
                list.add(new ITStudent(id, fullName, address, java, css));
                break;

            case "BIZ":
                int acc = Validation.getAndCheckInt(sc, "Enter acc score: ");
                int mkt = Validation.getAndCheckInt(sc, "Enter mkt score: ");
                list.add(new BizStudent(id, fullName, address, acc, mkt));
                break;
        }
    }

    public void displayInfo()
    {
        System.out.println("-------Student Informations-------");
        System.out.println("Type\t\tId\tName\t\tCountry\t\tCity\t\tDistrict\tStreet\t\tjava/acc\tcss/mkt\n");
        for (Student student : list) 
        {
            System.out.println(student.toString());
        }
    }

    public void sortStudent()
    {
        int size = list.size();
        for(int i = 0; i < size - 1; i++)   
        {  
            for (int j = i + 1; j < size; j++)   
            {   
                if(list.get(i).getFullName().compareTo(list.get(j).getFullName()) > 0)   
                {   
                    Student temp = list.get(i);  
                    list.set(i, list.get(j));  
                    list.set(j, temp);  
                }   
            }
        }

        displayInfo();
    }

    public void countCity()
    {
        Hashtable<String, ArrayList<Student>> hash = new Hashtable<String, ArrayList<Student>>();
        for (Student student : list) 
        {
            if(hash.containsKey(student.getAddress().getCity()))
            {
                ArrayList<Student> students = hash.get(student.getAddress().getCity());
                students.add(student);
                hash.replace(student.getAddress().getCity(), students);
            }
            else
            {
                ArrayList<Student> students = new ArrayList<Student>();
                students.add(student);
                hash.put(student.getAddress().getCity(), students);
            }
        }

        hash.forEach((k, v) -> System.out.println("city: " + k + ", number of students: " + v.size() + 
            v + "\n"));
    }

    public void report()
    {
        System.out.println("List of students who validate their semester: \n"); 
        System.out.println("-------Student Informations-------");
        System.out.println("Type\t\tId\tName\t\tCountry\t\tCity\t\tDistrict\tStreet\t\tjava/acc\tcss/mkt\n");
        for (Student student : list) 
        {
            if(student.calculateAvg() >= 5)
            {
                System.out.println(student);
            }
        }
    }

    public void Update_Delete()
    {
        Scanner sc = new Scanner(System.in);
        boolean test = true;
        String id = "";
        Student stud = null;
        while(test)
        {
            id = Validation.getValue(sc, "enter ID to find student: ");
            boolean tr = false;
            for (Student student : list) 
            {
                if(student.getId().equals(id))
                {
                    System.out.println("Student found:\n" + student);
                    stud = student;
                    tr = true;
                    test = false;
                }
            }
            if(!tr)
            {
                System.out.println("No student found...");
            }
        }

        String choice = Validation.getAndCheckValue(sc, "\nDo you want to update (U) or delete (D) student ?", "U|D|u|d");

        switch(choice)
        {
            case "D":
            case "d":
                list.remove(stud);
                break;

            case "U":
            case "u":
                String info = "";
                if(stud instanceof ITStudent)
                {
                    info = Validation.getAndCheckValue(sc, "What do you want to modify ?\nfull name / country / city / district / street / java score / css score",
                    "full name|country|city|district|street|java score|css score");
                } 
                else
                {
                    info = Validation.getAndCheckValue(sc, "What do you want to modify ?\nfull name / country / city / district / street / java score / css score",
                    "full name|country|city|district|street|acc score|mkt score");
                }
                String newInfo = "";
                double newInt = 0;
                switch(info)
                {
                    case "full name":
                        newInfo = Validation.getValue(sc, "Enter new full name");
                        stud.setFullName(newInfo);
                        break;

                    case "country":
                        newInfo = Validation.getValue(sc, "Enter new country");
                        stud.getAddress().setCountry(newInfo);
                        break;

                    case "city":
                        int count;
                        test = true;
                        while(test)
                        {
                            count = 0;
                            newInfo = Validation.getValue(sc, "Enter new city: ");
                            for (Student student : list) 
                            {
                                if(student.getAddress().getCity().equals(newInfo))
                                {
                                    count++;
                                }
                            }
                            if(count < 6){
                                test = false;
                            }
                            else{
                                System.out.println("No more than 6 people in Da Nang city");
                            }
                        }
                        stud.getAddress().setCity(newInfo);
                        break;

                    case "district":
                        newInfo = Validation.getValue(sc, "Enter new district");
                        stud.getAddress().setDistrict(newInfo);
                        break;

                    case "street":
                        newInfo = Validation.getValue(sc, "Enter new street");
                        stud.getAddress().setStreet(newInfo);
                        break;

                    case "java score":
                        newInt = Validation.getAndCheckScore(sc, "Enter new java score");
                        ((ITStudent)stud).setJava(newInt);
                        break;
                    
                    case "css score":
                        newInt = Validation.getAndCheckScore(sc, "Enter new css score");
                        ((ITStudent)stud).setCss(newInt);
                        break;
                    
                    case "acc score":
                        newInt = Validation.getAndCheckScore(sc, "Enter new acc score");
                        ((BizStudent)stud).setAcc(newInt);
                        break;

                    case "mkt score":
                        newInt = Validation.getAndCheckScore(sc, "Enter new mkt score");
                        ((BizStudent)stud).setMkt(newInt);
                        break;
                }
                                          
        }
    }
}
