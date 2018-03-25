package com.framework.ui.selenium.realtimeReporting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestStatus {
    @JsonProperty("testClass")
    private String testClass;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("executionTime")
    private String executionTime;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExecutionDate(String executionTime) {
        this.executionTime = executionTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    @Override
    public String toString() {
        return "TestStatus{" +
                "testClass='" + testClass + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", executionTime='" + executionTime + '\'' +
                '}';
    }
}
