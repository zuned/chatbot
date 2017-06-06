/**
 * All rights reserved. hCentive Hackathon.
 */
package com.hcentive.hackathon.botmonitor.sigar.impl;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NfsFileSystem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.cmd.SigarCommandBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hcentive.hackathon.botmonitor.sigar.HostHealthManager;
import com.hcentive.hackathon.core.domain.HostFileSystemDetail;
import com.hcentive.hackathon.core.domain.HostHealth;

/**
 * @author Nitin.Gupta
 *
 */
@Component("hostHealthManager")
public class HostHealthManagerImpl extends SigarCommandBase implements HostHealthManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(HostHealthManagerImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hcentive.hackathon.botmonitor.sigar.HostHealthManager#getInformation(
	 * )
	 */
	@Override
	public HostHealth getInformation() throws SigarException {

		HostHealth hostHealth = new HostHealth();

		getMemoryInformation(hostHealth);
		getFileSystemInformation(hostHealth);
		return hostHealth;
	}

	protected void getMemoryInformation(HostHealth hostHealth) throws SigarException {
		Mem mem = this.sigar.getMem();
		Swap swap = this.sigar.getSwap();

		hostHealth.getHostMemoryDetail().setTotalMemory(format(mem.getTotal()));
		hostHealth.getHostMemoryDetail().setUsedMemory(format(mem.getUsed()));
		hostHealth.getHostMemoryDetail().setFreeMemory(format(mem.getFree()));

		hostHealth.getHostMemoryDetail().setFreeMemory(format(mem.getActualUsed()));
		hostHealth.getHostMemoryDetail().setFreeMemory(format(mem.getActualFree()));
		hostHealth.getHostMemoryDetail().setFreeMemory(format(swap.getTotal()));
		hostHealth.getHostMemoryDetail().setFreeMemory(format(swap.getUsed()));
		hostHealth.getHostMemoryDetail().setFreeMemory(format(swap.getFree()));

	}

	protected void getFileSystemInformation(HostHealth hostHealth) throws SigarException {
		FileSystem[] fslist = this.proxy.getFileSystemList();
		FileSystem sys;
		long used, avail, total, pct;

		for (int i = 0; i < fslist.length; i++) {
			sys = fslist[i];

			try {
				FileSystemUsage usage;
				if (sys instanceof NfsFileSystem) {
					NfsFileSystem nfs = (NfsFileSystem) sys;
					if (!nfs.ping()) {
						continue;
					}
				}

				usage = this.sigar.getFileSystemUsage(sys.getDirName());

				/*
				 * if (this.opt_i) { used = usage.getFiles() -
				 * usage.getFreeFiles(); avail = usage.getFreeFiles(); total =
				 * usage.getFiles(); if (total == 0) { pct = 0; } else { long
				 * u100 = used * 100; pct = u100 / total + ((u100 % total != 0)
				 * ? 1 : 0); } } else {
				 */
				used = usage.getTotal() - usage.getFree();
				avail = usage.getAvail();
				total = usage.getTotal();

				pct = (long) (usage.getUsePercent() * 100);
				// }
			} catch (SigarException e) {
				// e.g. on win32 D:\ fails with "Device not ready"
				// if there is no cd in the drive.
				used = avail = total = pct = 0;
			}

			String usePct;
			if (pct == 0) {
				usePct = "-";
			} else {
				usePct = pct + "%";
			}

			HostFileSystemDetail fileSystemDetail = new HostFileSystemDetail();

			fileSystemDetail.setDirectoryName(sys.getDirName());
			fileSystemDetail.setFileSystemName(sys.getDevName());
			fileSystemDetail.setSysTypeName(sys.getTypeName());
			fileSystemDetail.setTotalAvailable(format(avail));
			fileSystemDetail.setTotalSize(format(total));
			fileSystemDetail.setTotalUsed(format(used));
			fileSystemDetail.setUsagePercetage(usePct);
			fileSystemDetail.setSysTypeName(sys.getSysTypeName());

			hostHealth.getFileSystemDetails().add(fileSystemDetail);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperic.sigar.cmd.SigarCommandBase#output(java.lang.String[])
	 */
	@Override
	public void output(String[] arg0) throws SigarException {
	}

	private static Long format(long value) {
		return new Long(value / 1024);
	}
}
