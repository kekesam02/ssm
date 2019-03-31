package com.rumo.pojo;

import java.util.Date;

public class Blog {
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private Integer userId;

    private Integer status;

    private String img;

    private String tag;

    private Integer kcolumn;

    private String musicLink;

    private String staticLink;

    private String videolink;

    private String appLink;

    private Integer hits;

    private Integer categoryId;

    private Integer width;

    private Integer height;

    private Integer isComment;

    private String isOpen;

    private Integer blogId;

    private Integer courseId;

    private String description;

    public Blog(Integer id, String name, Date createTime, Date updateTime, Integer isDelete, Integer userId, Integer status, String img, String tag, Integer kcolumn, String musicLink, String staticLink, String videolink, String appLink, Integer hits, Integer categoryId, Integer width, Integer height, Integer isComment, String isOpen, Integer blogId, Integer courseId, String description) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.userId = userId;
        this.status = status;
        this.img = img;
        this.tag = tag;
        this.kcolumn = kcolumn;
        this.musicLink = musicLink;
        this.staticLink = staticLink;
        this.videolink = videolink;
        this.appLink = appLink;
        this.hits = hits;
        this.categoryId = categoryId;
        this.width = width;
        this.height = height;
        this.isComment = isComment;
        this.isOpen = isOpen;
        this.blogId = blogId;
        this.courseId = courseId;
        this.description = description;
    }
    
    
    public Blog(Integer id, String name, Date createTime, Date updateTime, Integer isDelete, Integer userId, Integer status, String img, String tag, Integer kcolumn, String musicLink, String staticLink, String videolink, String appLink, Integer hits, Integer categoryId, Integer width, Integer height, Integer isComment, String isOpen, Integer blogId, Integer courseId) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.userId = userId;
        this.status = status;
        this.img = img;
        this.tag = tag;
        this.kcolumn = kcolumn;
        this.musicLink = musicLink;
        this.staticLink = staticLink;
        this.videolink = videolink;
        this.appLink = appLink;
        this.hits = hits;
        this.categoryId = categoryId;
        this.width = width;
        this.height = height;
        this.isComment = isComment;
        this.isOpen = isOpen;
        this.blogId = blogId;
        this.courseId = courseId;
    }

    public Blog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getKcolumn() {
        return kcolumn;
    }

    public void setKcolumn(Integer kcolumn) {
        this.kcolumn = kcolumn;
    }

    public String getMusicLink() {
        return musicLink;
    }

    public void setMusicLink(String musicLink) {
        this.musicLink = musicLink == null ? null : musicLink.trim();
    }

    public String getStaticLink() {
        return staticLink;
    }

    public void setStaticLink(String staticLink) {
        this.staticLink = staticLink == null ? null : staticLink.trim();
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink == null ? null : videolink.trim();
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink == null ? null : appLink.trim();
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}