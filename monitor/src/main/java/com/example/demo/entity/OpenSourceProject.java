package com.example.demo.entity;

import java.util.Date;

public class OpenSourceProject {
    private Integer id;
    private String projectName;
    private String type;
    private String projectUrl;
    private String projectLicense;
    private String modelUrl;
    private String modelLicense;
    private String description;
    private String relatedProjects;
    private String projectDescription;
    private String projectLimitations;
    private String deploymentStatus;
    private String operationStatus;
    private String effectDescription;
    private String remarks;
    private String uploadFiles;
    private Date createdTime;
    private String createdBy;
    private Date updatedTime;
    private String updatedBy;
    private Integer isDelete;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProjectLicense() {
        return projectLicense;
    }

    public void setProjectLicense(String projectLicense) {
        this.projectLicense = projectLicense;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public String getModelLicense() {
        return modelLicense;
    }

    public void setModelLicense(String modelLicense) {
        this.modelLicense = modelLicense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelatedProjects() {
        return relatedProjects;
    }

    public void setRelatedProjects(String relatedProjects) {
        this.relatedProjects = relatedProjects;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectLimitations() {
        return projectLimitations;
    }

    public void setProjectLimitations(String projectLimitations) {
        this.projectLimitations = projectLimitations;
    }

    public String getDeploymentStatus() {
        return deploymentStatus;
    }

    public void setDeploymentStatus(String deploymentStatus) {
        this.deploymentStatus = deploymentStatus;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public String getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(String uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
