package task1;

import static java.lang.Thread.sleep;

public class Window{
    Thread thread;
    String nameWindow;

    public Window(String name) {
        this.nameWindow = name;
        createThread();
    }

    void createThread() {
        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println(nameWindow + " начало задачу");
                    sleep(4000);
                    System.out.println(nameWindow + " закончило задачу");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    public boolean processClient() {
        if (this.thread.getState() == Thread.State.TERMINATED) {
            createThread();
        }
        if (this.thread.getState()==Thread.State.TIMED_WAITING
                || this.thread.getState()== Thread.State.RUNNABLE) {
            return false;
        }

        this.thread.start();
        return true;
    }

}
