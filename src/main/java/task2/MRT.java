package task2;


import java.util.Random;
import java.util.concurrent.Semaphore;


public class MRT extends Thread implements Runnable {

    Semaphore semWait;
    String name;
    WaitingRoom waitingRoom;
    public MRT(Semaphore semWait, String name, WaitingRoom waitingRoom) {
        this.semWait = semWait;
        this.name = name;
        this.waitingRoom = waitingRoom;
    }

    public void run() {
        while (true) {
            try {
                semWait.acquire();
                Patient patient = waitingRoom.patient;
                if (patient != null) {
                    System.out.println("Пациент с айди " + patient.id + " на МРТ");
                    try {
                        sleep(1000);
                        System.out.println("Пациент с айди " + patient.id + " закончил МРТ");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                semWait.release();
            } catch(InterruptedException e){System.out.println(e.getMessage());}
            try {
                sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
