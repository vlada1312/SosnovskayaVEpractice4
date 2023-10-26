package task2;

import java.util.Random;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class therapist extends Thread implements Runnable {
    Semaphore semQueue;
    Semaphore semWait;
    String name;
    PatientsQueue queue;
    WaitingRoom waitingRoom;

    public therapist(Semaphore semQueue, Semaphore semWait, String name, PatientsQueue queue, WaitingRoom waitingRoom) {
        this.semQueue = semQueue;
        this.semWait = semWait;
        this.name = name;
        this.queue = queue;
        this.waitingRoom = waitingRoom;


    }

    public void run() {
        while (true) {
            try {
                semQueue.acquire();
                Patient patient = queue.queue.pop();
                System.out.println("Пациент с айди " + patient.id + " у терапевта");
                semQueue.release();
                try {
                    sleep(1000);
                    System.out.println("Пациент с айди " + patient.id + " закончил приём у терапевта");
                    semWait.acquire();
                    waitingRoom.patient = patient;
                    semWait.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            } catch(InterruptedException e){System.out.println(e.getMessage());}
            try {
                sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
