package task1;

public class Person {
    String category = ""; //молодые, пожилые и бизнесмены

    public Person(int categoryNum) {
        if (categoryNum == 0) {
           this.category = "молодые";
        }
        else if(categoryNum == 1) {
            this.category = "пожилые";
        }
        else if(categoryNum == 2) {
            this.category = "бизнесмены";
        }

    }
}
