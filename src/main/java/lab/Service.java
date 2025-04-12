package lab;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Service {
    private static final Scanner sc = new Scanner(System.in);

    private static int inputInt() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("---Please, enter here a number: ");
                sc.next();
            }
        }
    }

    public static int makeChoice() {
        System.out.println("This is a game to learn words in English =)");
        System.out.println("Select your option: ");
        System.out.println("--- (1) Add words");
        System.out.println("--- (2) Update words");
        System.out.println("--- (3) Learn words");
        System.out.println("--- (4) Quit");
        System.out.print("Make your choice: ");

        int choice = inputInt();

        if(choice >= 1 && choice <= 4) {
            return choice;
        }else{
            while(choice < 1 || choice > 4){
                System.out.print("Make choice between(1-4): ");
                choice = inputInt();
            }
            return choice;
        }
    }

    public static void addWord() {
        List<String> translations = new ArrayList<>();

        String breaker;
        String inBreaker;
        String adderDb;
        int id;
        int checker = 1;
        do {
            Word word = new Word();
            System.out.print("Enter the word: ");
            if(checker++ == 1){
                sc.nextLine();
            }
            word.setWord(sc.nextLine());

            System.out.println("Enter the translations: ");
            id = 1;
            do{
                System.out.print("--- "+id+") Transltaion: ");
                id++;
                translations.add(sc.nextLine());
                System.out.print("--- Is there any other translations?(Да/Нет): ");
                inBreaker = sc.nextLine();
            }while(!inBreaker.equalsIgnoreCase("нет"));

            word.setTranslations(translations);
            translations.clear();

            System.out.println("Result word is : "+word);

            do{
                System.out.print("---Should I add them to the DB?(Да/Нет): ");
                adderDb = sc.nextLine();
            }while(!(adderDb.equalsIgnoreCase("да") || adderDb.equalsIgnoreCase("нет")));

            if(adderDb.equalsIgnoreCase("да")) {
                DB.add(word);
                System.out.println("---m: "+word+" is added!");
            }else if(adderDb.equalsIgnoreCase("нет")) {
                System.out.println("---m: Not added");
            }

            System.out.println();

            System.out.print("Do you want to add one more new word?(Да/Нет): ");
            breaker = sc.nextLine();

        } while (!breaker.equalsIgnoreCase("нет"));
    }


    public static void updateWords(String word) {
        List<Word> words = DB.fetchWords(word);
        int id, choiceId;
        Word updWord = null;
        Set<Integer> idCheck;

        if(words == null || words.isEmpty()) {
            System.out.println("---m: There no " + word + " in DB!");
        }else{
            System.out.println("---List of words: ");
            idCheck = new HashSet<>();

            id = 1;
            for(Word el : words) {
                System.out.print("---| "+id+")Word: ");
                id++;
                System.out.println(el);
                idCheck.add(el.getId());
            }

            do{
                System.out.print("---Choose ID of word that you want to change from list above: ");
                choiceId = inputInt();
            }while(!idCheck.contains(choiceId));

            int leftId = 0, rightId = words.size() - 1,midId;
            while(leftId <= rightId) {
                midId = (leftId + rightId)/2;
                if(words.get(midId).getId() > choiceId) {
                    rightId = midId - 1;
                }else if(words.get(midId).getId() < choiceId) {
                    leftId = midId + 1;
                }else{
                    updWord = words.get(midId);
                    break;
                }
            }

            System.out.println();
            System.out.println("---Selected object: " + updWord);

            do {
                System.out.println("---| 1)Word");
                System.out.println("---| 2)Translation");
                System.out.println("---| 3)Both");
                System.out.print("---What should I change in this object?(1-3): ");
                choiceId = inputInt();
            }while(choiceId<1 || choiceId > 3);

            String changedWord;
            String inBreaker;
            boolean checker;
            String adderDb;
            int cId;
            switch (choiceId){
                case 1:
                    checker = true;
                    cId = 1;
                    do{

                        if(cId == 1) {
                            System.out.print("Enter the word: ");
                            sc.nextLine();
                            changedWord = sc.nextLine();
                        }else{
                            System.out.print("Enter the word: ");
                            changedWord = sc.nextLine();
                        }
                        cId++;
                        updWord.setWord(changedWord);
                        System.out.println("Result word is : "+updWord);
                        do{
                            System.out.print("---Should I add them to the DB?(Да/Нет): ");
                            adderDb = sc.nextLine();
                        }while(!(adderDb.equalsIgnoreCase("да") || adderDb.equalsIgnoreCase("нет")));

                        if(adderDb.equalsIgnoreCase("да")) {
                            checker = false;
                        }else if(adderDb.equalsIgnoreCase("нет")) {
                            System.out.println("---m: Not added");
                        }
                    }while(checker);

                    System.out.println();
                    System.out.println("---m: Changing completed!");
                    break;
                case 2:
                    checker = true;
                    cId = 1;
                    do{
                        System.out.println("Enter the translations of word "+updWord.getWord()+":");
                        id = 1;
                        if(!(updWord.getTranslations() == null || updWord.getTranslations().isEmpty())){
                            updWord.getTranslations().clear();
                        }else if(updWord.getTranslations() == null){
                            updWord.setTranslations(List.of());
                        }
                        if(cId++ == 1) {
                            sc.nextLine();
                        }
                        do{
                            System.out.print("--- "+id+") Transltaion: ");
                            id++;
                            updWord.getTranslations().add(sc.nextLine());
                            System.out.print("--- Is there any other translations?(Да/Нет): ");
                            inBreaker = sc.nextLine();
                        }while(!inBreaker.equalsIgnoreCase("нет"));

                        System.out.println("Result word is : "+updWord);
                        do{
                            System.out.print("---Should I add them to the DB?(Да/Нет): ");
                            adderDb = sc.nextLine();
                        }while(!(adderDb.equalsIgnoreCase("да") || adderDb.equalsIgnoreCase("нет")));

                        if(adderDb.equalsIgnoreCase("да")) {
                            checker = false;
                        }else if(adderDb.equalsIgnoreCase("нет")) {
                            System.out.println("---m: Not added");
                        }
                    }while(checker);

                    System.out.println();
                    System.out.println("---m: Changing completed!");
                    break;
                case 3:
                    System.out.print("Enter the word: ");
                    sc.nextLine();
                    changedWord = sc.nextLine();
                    updWord.setWord(changedWord);

                    checker = true;
                    do{
                        System.out.println("Enter the translations of word "+updWord.getWord()+":");
                        id = 1;
                        if(!(updWord.getTranslations() == null || updWord.getTranslations().isEmpty())){
                            updWord.getTranslations().clear();
                        }else if(updWord.getTranslations() == null){
                            updWord.setTranslations(List.of());
                        }
                        do{
                            System.out.print("--- "+id+") Transltaion: ");
                            id++;
                            updWord.getTranslations().add(sc.nextLine());
                            System.out.print("--- Is there any other translations?(Да/Нет): ");
                            inBreaker = sc.nextLine();
                        }while(!inBreaker.equalsIgnoreCase("нет"));

                        System.out.println("Result word is : "+updWord);
                        do{
                            System.out.print("---Should I add them to the DB?(Да/Нет): ");
                            adderDb = sc.nextLine();
                        }while(!(adderDb.equalsIgnoreCase("да") || adderDb.equalsIgnoreCase("нет")));

                        if(adderDb.equalsIgnoreCase("да")) {
                            checker = false;
                        }else if(adderDb.equalsIgnoreCase("нет")) {
                            System.out.println("---m: Not added");
                        }
                    }while(checker);

                    System.out.println();
                    System.out.println("---m: Changing completed!");
                    break;
            }
            DB.update(updWord);
        }
    }

    public static void learnWords() {
        int choiceS;
        System.out.println("---| 1)See word and guess its translation");
        System.out.println("---| 2)See translation and guess the word");
        do{
            System.out.print("---Choose the strategy to learn words(1/2): ");
            choiceS = inputInt();
        }while(!(choiceS == 1 || choiceS == 2));

        int idObj, counter, check;
        List<Word> words = DB.fetchWords();
        Word currentWord;
        String guess, checker;

        switch (choiceS) {
            case 1:
                guess = "";
                idObj = 0;
                check = 0;
                do{
                    counter = 5;
                    if(idObj > words.size() - 1){
                        idObj = 0;
                    }

                    currentWord = words.get(idObj);
                    idObj++;
                    System.out.println("---The word is: "+currentWord.getWord());
                    do{
                        if(!currentWord.getTranslations().contains(guess) && !guess.isEmpty()) {
                            System.out.println("---| Incorrect, try again!");
                        }else if(!guess.isEmpty()){
                            System.out.println("---| Yes, it is correct! Good job!");
                            break;
                        }
                        System.out.println("---| Number of remain attempts: "+counter);
                        System.out.println();
                        System.out.print("---Enter the translation of " + currentWord.getWord()+": ");

                        if(check == 0) {
                            sc.nextLine();
                        }

                        guess = sc.nextLine();
                        counter--;
                        if(counter == 0){
                            System.out.println("---You didnt guess the translation!");
                            System.out.println("Translations of "+currentWord.getWord()+" was: "+currentWord.getTranslations());
                            break;
                        }
                    }while(true);

                    System.out.println();
                    System.out.print("--- Do you want to continue(Да/Нет)?");
                    checker = sc.nextLine();
                    System.out.println();
                    guess = "";
                    check++;
                }while(!checker.equalsIgnoreCase("нет"));
                break;
            case 2:
                guess = "";
                idObj = 0;
                check = 0;
                do{
                    counter = 5;
                    if(idObj > words.size() - 1){
                        idObj = 0;
                    }
                    currentWord = words.get(idObj);
                    idObj++;
                    System.out.println("---The translations are: "+currentWord.getTranslations());
                    do{
                        if(!currentWord.getWord().equalsIgnoreCase(guess) && !guess.isEmpty()) {
                            System.out.println("---| Incorrect, try again!");
                        }else if(!guess.isEmpty()){
                            System.out.println("---| Yes, it is correct! Good job!");
                            break;
                        }
                        System.out.println("---| Number of remain attempts: "+counter);
                        System.out.println();
                        System.out.print("---Enter the translation of " + currentWord.getTranslations()+": ");

                        if(check == 0) {
                            sc.nextLine();
                        }

                        guess = sc.nextLine();
                        counter--;
                        if(counter == 0){
                            System.out.println("---You didnt guess the word!");
                            System.out.println("Translations of "+currentWord.getTranslations()+" was: "+currentWord.getWord());
                            break;
                        }
                    }while(true);

                    System.out.println();
                    System.out.print("--- Do you want to continue(Да/Нет)?");
                    checker = sc.nextLine();
                    System.out.println();
                    guess = "";
                    check++;
                }while(!checker.equalsIgnoreCase("нет"));
                break;
        }
    }

}
