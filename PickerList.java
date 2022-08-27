package RandomChooserProject;

import java.util.ArrayList;

public class PickerList {

    private ArrayList<String> list;

    public PickerList() {

        this.list = new ArrayList<>();

    }

    public PickerList(ArrayList<String> list) {

        this.list = list;

    }

    public void append(String item) {

        this.list.add(item);

    }

    public void delete(String item) {

        this.list.remove(item);

    }

    public int size() {

        return this.list.size();
        
    }

    public int hasElements() {

        int status = 0;
        if (this.size() > 0) status = 1;
        return status;

    }

    public String find(int x) {

        return this.list.get(x);
        
    }

    public int checkList(String item) {

        boolean backFound = false, frontFound = false, midFound = false;
        int front = 0, len = this.list.size();
        int back = len - 1, foundIndex = 0;
        int mid = back / 2;
        if (len > 2) {
            switch (len % 2) {
                case 1:
                    while (back > mid) {
                        if (item.equals(this.list.get(back))) {
                            backFound = true;
                            foundIndex = back;
                            break;
                        }
                        back--;
                    }
                    if (!(backFound)) {
                        while (front < mid) {
                            if (item.equals(this.list.get(front))) {
                                frontFound = true;
                                foundIndex = front;
                                break;
                            }
                            front++;
                        }
                    }
                    if (!(backFound) && !(frontFound)) {
                        if (item.equals(this.list.get(mid))) {
                            midFound = true;
                            foundIndex = mid;
                        }
                    }
                    break;
                case 0:
                    while (back > mid) {
                        if (item.equals(this.list.get(back))) {
                            backFound = true;
                            foundIndex = back;
                            break;
                        }
                        back--;
                    }
                    if (!(backFound)) {
                        while (front < mid) {
                            if (item.equals(this.list.get(front))) {
                                frontFound = true;
                                foundIndex = front;
                                break;
                            }
                            front++;
                        }
                    }
                    break;
            }
        } else {
            while (front < back) {
                if (item.equals(this.list.get(front))) {
                    frontFound = true;
                    foundIndex = front;
                    break;
                }
                front++;
            }
        }

        if (!(frontFound) && !(midFound) && !(backFound)) {
            foundIndex = -1;
        }

        return foundIndex;

    }

    public boolean matches(String currentResponse) {

        int back = this.size() - 1;
        boolean decision = false;
        if (this.size() > 0 && this.find(back).equalsIgnoreCase(currentResponse)) decision = true;
        return decision;

    }

    public String toString() {

        return this.list.toString();

    }

}