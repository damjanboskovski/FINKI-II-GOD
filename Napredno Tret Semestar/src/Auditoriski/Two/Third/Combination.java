package Auditoriski.Two.Third;

public class Combination {
    private int combination;
    private boolean isOpen;
    private static final int minCombination = 100;
    private static final int maxCombination = 999;
    private static final int defaultCombination = 100;

    public Combination(int combination) {
        if (isValid(combination)) {
            this.combination = combination;
        } else this.combination = defaultCombination;
        this.isOpen = false;
    }

    private boolean isValid(int combination) {
        return combination >= minCombination && combination <= maxCombination;
    }

    public boolean open(int combination) {
        return this.isOpen = (this.combination == combination);
    }

    public void lock() {
        this.isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean changeCombination(int oldComb, int newComb) {
        if (open(oldComb) && isValid(newComb)) {
            this.combination = newComb;
            this.lock();
            return true;
        }
        return false;
    }
}
