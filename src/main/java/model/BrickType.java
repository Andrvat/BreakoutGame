package model;

public enum BrickType {
    RED,
    ORANGE,
    GREEN,
    YELLOW;

    private boolean hasDestroyedAtLeastOnce = false;

    public void setDestroyedAtLeastOnce() {
        hasDestroyedAtLeastOnce = true;
    }

    public boolean hasNeverDestroyed() {
        return !hasDestroyedAtLeastOnce;
    }
}
