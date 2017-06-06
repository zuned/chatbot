/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.domain;

/**
 * @author Nitin.Gupta
 *
 */
public class HostFileSystemDetail {

	private String fileSystemName, usagePercetage, directoryName, sysTypeName, typeName;
	private long totalSize, totalUsed, totalAvailable;

	/**
	 * @return the fileSystemName
	 */
	public String getFileSystemName() {
		return fileSystemName;
	}

	/**
	 * @param fileSystemName
	 *            the fileSystemName to set
	 */
	public void setFileSystemName(String fileSystemName) {
		this.fileSystemName = fileSystemName;
	}

	/**
	 * @return the usagePercetage
	 */
	public String getUsagePercetage() {
		return usagePercetage;
	}

	/**
	 * @param usagePercetage
	 *            the usagePercetage to set
	 */
	public void setUsagePercetage(String usagePercetage) {
		this.usagePercetage = usagePercetage;
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return directoryName;
	}

	/**
	 * @param directoryName
	 *            the directoryName to set
	 */
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	/**
	 * @return the sysTypeName
	 */
	public String getSysTypeName() {
		return sysTypeName;
	}

	/**
	 * @param sysTypeName
	 *            the sysTypeName to set
	 */
	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the totalSize
	 */
	public long getTotalSize() {
		return totalSize;
	}

	/**
	 * @param totalSize
	 *            the totalSize to set
	 */
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * @return the totalUsed
	 */
	public long getTotalUsed() {
		return totalUsed;
	}

	/**
	 * @param totalUsed
	 *            the totalUsed to set
	 */
	public void setTotalUsed(long totalUsed) {
		this.totalUsed = totalUsed;
	}

	/**
	 * @return the totalAvailable
	 */
	public long getTotalAvailable() {
		return totalAvailable;
	}

	/**
	 * @param totalAvailable
	 *            the totalAvailable to set
	 */
	public void setTotalAvailable(long totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
}
