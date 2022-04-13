package ninetynineballons.graphics;

public enum BalloonState {
    /**
     * fly up
     */
    FLY,
    /**
     * hold in the air
     */
    HOLD,
    /**
     * the balloon has been just popped
     */
    POPPED,
    /**
     * the balloon is popped and no longer visible
     */
    INVISIBLE;

    public boolean isPopped() {
        return this == POPPED || this == INVISIBLE;
    }
}
