package com.dipinder.RecipeList.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="recipes")
public class Recipe{
//@Transient if you wants to read and don't want to add to db

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotNull
@Size(min = 5, max = 200)
@NotBlank(message = "Cannot be Blank")
@NotNull(message = "Cannot be Null")
private String name;

@NotBlank(message="Steps cannot be blank")
@Lob
private String steps;

@NotBlank
@Column(columnDefinition = "TEXT")
private String ingredient;

@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="user_id")
private User creator;

@NotNull
@Transient
private Long userId;



public Date getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}
public Date getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}
//Created And Updated At
@Column(updatable=false)
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date createdAt;

@DateTimeFormat(pattern="yyyy-MM-dd")
private Date updatedAt;

public Recipe() {
}
//this Construction is Optional for Controller but needed for RestController
public Recipe(String name,User creator) {
    this.name = name;
    this.creator = creator;
}
public Recipe(String name,User creator,Long userID,String steps,String ingredient) {
    this.name = name;
    this.creator = creator;
    this.userId = userID;
    this.steps = steps;
    this.ingredient = ingredient;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getSteps() {
	return steps;
}
public void setSteps(String steps) {
	this.steps = steps;
}

public String getIngredient() {
	return ingredient;
}
public void setIngredient(String ingredient) {
	this.ingredient = ingredient;
}
public User getCreator() {
	return creator;
}
public void setCreator(User creator) {
	this.creator = creator;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
// other getters and setters removed for brevity
@PrePersist
protected void onCreate(){
    this.createdAt = new Date();
}
@PreUpdate
protected void onUpdate(){
    this.updatedAt = new Date();
}

}