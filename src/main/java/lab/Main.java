package lab;

import lab.service.ConsoleService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        String word;
        do{
            choice = ConsoleService.makeChoice();

            switch (choice) {
                case 1:
                    ConsoleService.addWord();
                    break;
                case 2:
                    System.out.print("Enter the word that you want to change: ");
                    word = sc.nextLine();
                    ConsoleService.updateWords(word);
                    break;
                case 3:
                    ConsoleService.learnWords();
                    break;
                default:
                    break;
            }

        }while(choice != 4);
    }
}