package com.peiwc.billing.process;

/**
 * Created by carlos.acosta on 15/06/2017.
 */
public enum ProcessState {

    FINISHED("FINISHED"), PENDING_START("PENDING_START"), RUNNING("RUNNING");

    private final String state;

    private ProcessState(final String state) {
        this.state = state;
    }

    /**
     * gets the name of current state.
     *
     * @return name of current state.
     */
    public String getName() {
        return this.state;
    }


}
