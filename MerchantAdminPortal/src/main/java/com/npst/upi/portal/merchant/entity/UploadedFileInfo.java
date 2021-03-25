package com.npst.upi.portal.merchant.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Entity
@Table(name="UPLOADED_FILE_INFO")
public class UploadedFileInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "service_type", nullable = false)
	private int serviceType;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "success_count")
	private long successCount;

	@Column(name = "failure_count")
	private long failureCount;

	@Column(name = "total_count")
	private long totalCount;

	@Column(name = "line_pointer")
	private long linePointer;

	@Column(name = "file_location", nullable = false)
	private String fileLocation;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	@Column(name = "file_size")
	private String fileSize;

	@Column(name = "content_type")
	private String contentType;
	
	private String remarks;
	
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}

	public long getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(long failureCount) {
		this.failureCount = failureCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getLinePointer() {
		return linePointer;
	}

	public void setLinePointer(long linePointer) {
		this.linePointer = linePointer;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
