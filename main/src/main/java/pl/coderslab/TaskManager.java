package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;

    public static void main(String[] args)

    {
        String option= optionsList();

        switch (option) {
            case "add":
                addTask();
                break;
            case "remove":
                removeTask();
                break;
            case "list":
                listTasks();
                break;
            case "exit":
                saveTasksToFile("tasks.csv");
                System.out.println(ConsoleColors.RED + "Finish");
                break;
            default:
                System.out.println("Please select a correct option.");
        }

    }

    public static String optionsList() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        System.out.println(ConsoleColors.RESET +"add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        scanner.close();
        return option;
    }

    public static void addTask() {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Please add task description: ");
        String description = scanner.nextLine();

        System.out.println("Please add task due date: ");
        String dueDate=scanner.nextLine();
        System.out.println("Is your task is important: true/false");
        String isImportant = scanner.nextLine();

        if (tasks == null) {
            tasks = new String[1][3];
        } else {
            tasks = Arrays.copyOf(tasks, tasks.length + 1);
        }
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = dueDate;
        tasks[tasks.length - 1][2] = String.valueOf(isImportant);

    }

    public static void removeTask() {
    }

    public static void listTasks() {
        if(tasks == null || tasks.length ==0) {
            System.out.println("No tasks is available");
        } else {
            System.out.println("Tasks: ");
            for (int i = 0; i< tasks.length; i++){
                System.out.println((i+1) + ". Description: " + tasks[i][0] +
                        ", " + "Due Date: " + tasks[i][1] + ", Important: " +
                        tasks[i][2]);
            }
        }
    }

    public static void saveTasksToFile(String fileName) {
    }




    public static String[][] loadDataToTab(String fileName) {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.out.println("File not exist.");
            System.exit(0);
        }

        String[][] tab = null;
        try {
            List<String> strings = Files.readAllLines(dir);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }


}