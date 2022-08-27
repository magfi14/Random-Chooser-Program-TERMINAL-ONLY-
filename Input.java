package RandomChooserProject;

import java.util.Scanner;

public class Input {
    private String nameOfList;
    private PickerList list;
    private boolean repeat;
    private boolean passedInitialPrompt;

    public Input() {

        this.list = new PickerList();
        this.repeat = true;
        this.passedInitialPrompt = false;

    }

    public Input(PickerList list) {

        this.list = list;
        this.repeat = true;
        this.passedInitialPrompt = false;

    }

    private boolean stopCriteriaMet(String response) {

        String [] criteria = new String[] {"no", "stop"};
        int x = 0;
        boolean stopCriteriaMet = false;
        while (x < criteria.length) {
            if (criteria[x].equalsIgnoreCase(response)) {
                stopCriteriaMet = true;
                break;
            }
            x++;
        }
        return stopCriteriaMet;

    }

    private boolean passedCheck(String response) {

        return this.list.checkList(response) >= 0;

    }

    public void prompt() {
        Scanner input = new Scanner(System.in);
        if (!this.passedInitialPrompt) {
            this.passedInitialPrompt = true;
            System.out.print("Name of List: ");
            this.nameOfList = input.nextLine();
        }
        System.out.print("Stop, pick, append or delete from the list: " + this.nameOfList + "? ");
        String response = input.nextLine().toLowerCase();
        switch(response) {
            case "append": case "add":
                this.repeat = true;
                this.appendMultiple();
                break;
            case "delete": case "remove":
                this.repeat = true;
                switch(this.list.hasElements()) {
                    case 1:
                        this.deleteMultiple();
                        break;
                    case 0: default:
                        System.out.println("Sorry, no elements in the list!");
                        break;
                }
                break;
            case "pick":
                this.pick();
                break;
            case "stop": default:
                System.out.println(this.toString());
                break;
        }

    }

    public void pick() {

        RandomChooser chooser;
        System.out.print("Create a new Random Chooser. Impact: ");
        Scanner input = new Scanner(System.in);
        int impact = input.nextInt();
        chooser = new RandomChooser(this.nameOfList, impact, this);
        System.out.println("Winning choice: " + chooser);

    }

    public void append() {

        System.out.print("Append a new item to list: " + this.nameOfList + ". Item: ");
        Scanner input = new Scanner(System.in);
        String response = input.nextLine().toLowerCase();
        if (!this.stopCriteriaMet(response)) {
            if (!this.passedCheck(response) && !this.list.matches(response)) {
                this.list.append(response);
                System.out.println("Items in List " + this.nameOfList + ": " + this.toString());
            } else {
                System.err.println("Sorry, this item is already in the list. Duplicates are not allowed.");
            }
            this.repeat = true;
        } else {
            this.repeat = false;
        }

    }

    public void appendMultiple() {

        while (this.repeat) {
            this.append();
            if (!this.repeat) {
                this.prompt();
                break;
            }
        }

    }

    public void delete() {

        System.out.println("Delete an item from list: " + this.nameOfList + ". Item:");
        Scanner input = new Scanner(System.in);
        String response = input.next().toLowerCase();
        if (!this.stopCriteriaMet(response) && this.passedCheck(response)) {
            this.list.delete(response);
            System.out.println("Items in List " + this.nameOfList + ": " + this.toString());
            this.repeat = true;
        } else {
            this.repeat = false;
        }

    }
    
    public void deleteMultiple() {

        while (this.repeat) {
            this.delete();
            if (!this.repeat) { 
                this.prompt();
                break;
            }
        }

    }

    public PickerList getList() {

        return this.list;

    }

    public int sizeOfList() {

        return this.getList().size();

    }

    public String toString() {

        return this.list.toString();

    }

}
