package com.course;

import java.util.ArrayList;

/**
	author: Vicky pl
	email: vicky542011@gmail.com
	mobile: 7828789845
 **/
/**
 * @author Anonymox
 *
 */
public class Course {

	private String id;
	private String courseId;
	private String courseName;
	
	private ArrayList<CourseFaculty> faculty;
	private ArrayList<CourseStudent> student;
	private ArrayList<CourseVideos> videos;
	private ArrayList<CourseFiles> files;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public ArrayList<CourseFaculty> getFaculty() {
		return faculty;
	}
	public void setFaculty(ArrayList<CourseFaculty> faculty) {
		this.faculty = faculty;
	}
	public ArrayList<CourseStudent> getStudent() {
		return student;
	}
	public void setStudent(ArrayList<CourseStudent> student) {
		this.student = student;
	}
	public ArrayList<CourseVideos> getVideos() {
		return videos;
	}
	public void setVideos(ArrayList<CourseVideos> videos) {
		this.videos = videos;
	}
	public ArrayList<CourseFiles> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<CourseFiles> files) {
		this.files = files;
	}
	
}