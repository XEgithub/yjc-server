package com.dk.data.dto;

/**
 * @ClassName ProjectDto
 * @Description
 * @Author zyf
 * @Date 2018/12/7 16:42
 **/
public class ProjectDto {

    private String uuid;

    private String[] uuids;

    private String type;

    public ProjectDto() {

    }

    public ProjectDto(String uuid, String type) {
        this.uuid = uuid;
        this.type = type;
    }

    public String[] getUuids() {
        return uuids;
    }

    public void setUuids(String[] uuids) {
        this.uuids = uuids;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
