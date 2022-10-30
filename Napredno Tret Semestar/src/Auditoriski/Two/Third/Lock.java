package Auditoriski.Two.Third;

public class Lock {
    public static void main(String[] args) {
        Combination validLock = new Combination(777);
        System.out.println("TESTING isOpen()....");
        System.out.println(validLock.isOpen());

        System.out.println("TESTING open()....");
        System.out.println(validLock.open(666));
        System.out.println(validLock.open(777));

        System.out.println("TESTING lock()....");
        validLock.lock();
        System.out.println(validLock.isOpen());

        System.out.println("TESTING changeCombination()....");
        System.out.println(validLock.changeCombination(666,767));
        System.out.println(validLock.changeCombination(777,999));

        Combination invalidLock = new Combination(777999);
        System.out.println("TESTING isOpen()....");
        System.out.println(invalidLock.isOpen());

        System.out.println("TESTING open()....");
        System.out.println(invalidLock.open(777999));
        System.out.println(invalidLock.open(777));
        System.out.println(invalidLock.open(100));

        System.out.println("TESTING lock()....");
        invalidLock.lock();
        System.out.println(invalidLock.isOpen());

        System.out.println("TESTING changeCombination()....");
        System.out.println(invalidLock.changeCombination(777999,767));
        System.out.println(invalidLock.changeCombination(100,777));
    }
}
