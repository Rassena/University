public class main {

    public static void race(){
        Race_Condition race = new Race_Condition();
        race.run();
    }

    public static void dead() {
        DeadLock dead = new DeadLock();
        dead.run();
    }

    public static void live() {
        LiveLock live = new LiveLock();
        live.run();
    }

    public static void main(String[] args) {
        race();
        //live();
        //dead();

    }



}
