/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.core.domain;

import java.util.HashSet;
import java.util.Set;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

/**
 * @author Nitin.Gupta
 *
 */
@Entity("core_host_health")
public class HostHealth extends BaseEntity {

	@Embedded
	private Set<HostFileSystemDetail> fileSystemDetails = new HashSet<HostFileSystemDetail>(0);

	@Embedded
	private HostMemoryDetail hostMemoryDetail = new HostMemoryDetail();

	/**
	 * @return the fileSystemDetails
	 */
	public Set<HostFileSystemDetail> getFileSystemDetails() {
		return fileSystemDetails;
	}

	/**
	 * @param fileSystemDetails
	 *            the fileSystemDetails to set
	 */
	public void setFileSystemDetails(Set<HostFileSystemDetail> fileSystemDetails) {
		this.fileSystemDetails = fileSystemDetails;
	}

	/**
	 * @return the hostMemoryDetail
	 */
	public HostMemoryDetail getHostMemoryDetail() {
		return hostMemoryDetail;
	}

	/**
	 * @param hostMemoryDetail
	 *            the hostMemoryDetail to set
	 */
	public void setHostMemoryDetail(HostMemoryDetail hostMemoryDetail) {
		this.hostMemoryDetail = hostMemoryDetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.hostMemoryDetail.toString();
	}
}
