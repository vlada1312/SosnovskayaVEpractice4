package task2;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) {
        ArrayDeque<Patient> patients = new ArrayDeque<Patient>();
        for(int i = 1; i < 20; i++) {
            Patient patient = new Patient(i);
            patients.addLast(patient);
        }
        PatientsQueue queue = new PatientsQueue(patients);
        WaitingRoom waitingRoom = new WaitingRoom();

        Semaphore sem1 = new Semaphore(1); // 1 разрешение
        Semaphore sem2 = new Semaphore(1); // 1 разрешение


        therapist doc = new therapist(sem1, sem2, "Терапевт", queue, waitingRoom);
        doc.start();


        MRT mrt = new MRT(sem1, "МРТ", waitingRoom);
        mrt.start();

        int maxQueueSize = queue.queue.size();
        for(int i = 21; i < 500; i++){
            queue.queue.addLast(new Patient(i));
            System.out.print("Пациент добавлен в очередь. Текущая длина очереди: " + queue.queue.size());
            if (queue.queue.size() > maxQueueSize) {
                maxQueueSize = queue.queue.size();
            }
            System.out.println(". Максимальная длина очереди на данный момент: " + maxQueueSize);
            try {
                sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Максимальная длина очереди на день: " + maxQueueSize);
    }
}
