package com.peiwc.billing.process;

/**
 * Created by carlos.acosta on 15/06/2017.
 */
public enum ProcessState {

	/**
	 * sets the process as finished
	 */
	FINISHED("FINISHED"),
	/**
	 * sets the process as pending start
	 */
	PENDING_START("PENDING_START"),
	/**
	 * sets the process as running
	 */
	RUNNING("RUNNING"),
	/**
	 * sets the process as already run
	 */
	ALREADY_RUN("ALREADY_RUN"),
	/**
	 * sets the process to io error, when an exception writing occurs.
	 */
	IO_ERROR("IO_ERROR");

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
