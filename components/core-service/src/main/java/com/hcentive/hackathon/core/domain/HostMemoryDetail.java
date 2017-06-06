/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.domain;

/**
 * @author Nitin.Gupta
 *
 */
public class HostMemoryDetail {

	private long freeMemory, totalMemory, usedMemory, actualUsedMemory, actualFreeMemory, swapTotalMemory,
			swapFreeMemory, swapUsedMemory;

	/**
	 * @return the freeMemory
	 */
	public long getFreeMemory() {
		return freeMemory;
	}

	/**
	 * @param freeMemory
	 *            the freeMemory to set
	 */
	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	/**
	 * @return the totalMemory
	 */
	public long getTotalMemory() {
		return totalMemory;
	}

	/**
	 * @param totalMemory
	 *            the totalMemory to set
	 */
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	/**
	 * @return the usedMemory
	 */
	public long getUsedMemory() {
		return usedMemory;
	}

	/**
	 * @param usedMemory
	 *            the usedMemory to set
	 */
	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	/**
	 * @return the actualUsedMemory
	 */
	public long getActualUsedMemory() {
		return actualUsedMemory;
	}

	/**
	 * @param actualUsedMemory
	 *            the actualUsedMemory to set
	 */
	public void setActualUsedMemory(long actualUsedMemory) {
		this.actualUsedMemory = actualUsedMemory;
	}

	/**
	 * @return the actualFreeMemory
	 */
	public long getActualFreeMemory() {
		return actualFreeMemory;
	}

	/**
	 * @param actualFreeMemory
	 *            the actualFreeMemory to set
	 */
	public void setActualFreeMemory(long actualFreeMemory) {
		this.actualFreeMemory = actualFreeMemory;
	}

	/**
	 * @return the swapTotalMemory
	 */
	public long getSwapTotalMemory() {
		return swapTotalMemory;
	}

	/**
	 * @param swapTotalMemory
	 *            the swapTotalMemory to set
	 */
	public void setSwapTotalMemory(long swapTotalMemory) {
		this.swapTotalMemory = swapTotalMemory;
	}

	/**
	 * @return the swapFreeMemory
	 */
	public long getSwapFreeMemory() {
		return swapFreeMemory;
	}

	/**
	 * @param swapFreeMemory
	 *            the swapFreeMemory to set
	 */
	public void setSwapFreeMemory(long swapFreeMemory) {
		this.swapFreeMemory = swapFreeMemory;
	}

	/**
	 * @return the swapUsedMemory
	 */
	public long getSwapUsedMemory() {
		return swapUsedMemory;
	}

	/**
	 * @param swapUsedMemory
	 *            the swapUsedMemory to set
	 */
	public void setSwapUsedMemory(long swapUsedMemory) {
		this.swapUsedMemory = swapUsedMemory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Object[] header = new Object[] { "total", "used", "free" };

		Object[] memRow = new Object[] { this.totalMemory, this.usedMemory, this.freeMemory };

		Object[] actualRow = new Object[] { this.actualUsedMemory, this.actualFreeMemory };

		Object[] swapRow = new Object[] { this.swapTotalMemory, this.swapUsedMemory, this.swapFreeMemory };

		String _header = String.format("%18s %10s %10s", header);
		String _memory = String.format("Mem:    %10d %10d %10d", memRow);
		String _actual = null;

		// e.g. linux
		if ((this.usedMemory != this.actualUsedMemory) || (this.freeMemory != this.actualFreeMemory)) {
			_actual = String.format("-/+ buffers/cache: " + "%10d %10d", actualRow);
		}

		String _swap = String.format("Swap:   %10d %10d %10d", swapRow);

		// String _ram = String.format("RAM: %10ls", new Object[] { mem.getRam()
		// + "MB" });

		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		sb.append(_header).append(newLine).append(_memory).append(newLine);

		if (_actual != null) {
			sb.append(_actual).append(newLine);
		}

		sb.append(_swap);
		return sb.toString();
	}
}
