package task1;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MFC {
    public static void main(String[] args) {
        int youngPersCount = 0; int oldPersCount = 0; int businessPersCount = 0;
        int youngPersLeave = 0; int oldPersLeave = 0; int businessPersLeave = 0;

        System.out.println("Введите количество человек в очередь");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Window window1 = new Window("Окно 1");
        Window window2 = new Window("Окно 2");
        Window window3 = new Window("Окно 3");

        Random random = new Random();
        Person person;
        for(int i = 0; i < count; i++) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            person = new Person(random.nextInt(3));
            if (person.category == "") {
                System.out.println("Неверно введена категория");
            }
            else {
                System.out.println("Человек из категории " + person.category + " добавлен в очередь");
                switch (person.category) {
                    case ("молодые"): youngPersCount++; break;
                    case ("пожилые"): oldPersCount++; break;
                    case ("бизнесмены"): businessPersCount++; break;
                }


                if (window1.processClient()) {
                    System.out.println("Вошли в первое окно");
                    continue;
                }
                if (person.category == "пожилые" && window2.processClient()) {
                    System.out.println("Вошли во второе окно");
                    continue;
                }
                if (person.category == "бизнесмены" && window3.processClient()) {
                    System.out.println("Вошли в третье окно");
                    continue;
                }
                System.out.println("все окна заняты. Злой клиент ушёл домой");
                switch (person.category) {
                    case ("молодые"): youngPersLeave++; break;
                    case ("пожилые"): oldPersLeave++; break;
                    case ("бизнесмены"): businessPersLeave++; break;
                }


            }
        }
        System.out.println("Всего клиентов:");
        System.out.println("Молодые: " + youngPersCount);
        System.out.println("Пожилые: " + oldPersCount);
        System.out.println("Бизнесмены: " + businessPersCount);


        System.out.println("Процент ушедших клиентов:");
        System.out.println("Молодые: " + (double)youngPersLeave / youngPersCount * 100 + "%");
        System.out.println("Пожилые: " + (double)oldPersLeave / oldPersCount * 100 + "%");
        System.out.println("Бизнесмены: " + (double)businessPersLeave / businessPersCount * 100 + "%");

    }
}
