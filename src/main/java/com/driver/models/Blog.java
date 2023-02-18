
package com.driver.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog")
public class Blog{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    @CreationTimestamp
    private Date pubDate;

    // for creating a foreign key of the user in the blog class
    @ManyToOne
    @JoinColumn
    private User user;

    // For bidirectional mapping of the blog and image
    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<Image>();


    public Blog(int id, String title, String content, Date pubDate, User user, List<Image> imageList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pubDate = pubDate;
        this.user = user;
        this.imageList = imageList;
    }

    public Blog(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
