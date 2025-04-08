package lab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        String word;
        do{
            choice = Service.makeChoice();

            switch (choice) {
                case 1:
                    Service.addWord();
                    break;
                case 2:
                    System.out.print("Enter the word that you want to change: ");
                    word = sc.nextLine();
                    Service.updateWords(word);
                    break;
                case 3:
                    Service.learnWords();
                    break;
                default:
                    break;
            }

        }while(choice != 4);
    }
}