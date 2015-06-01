package kickstarter.repository.facade.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectComment implements Serializable {

	private static final long serialVersionUID = 6511337370371120313L;
	private int projectID;
	private int userID;
	private int commentID;
	private String comment;

	public String getComment() {
		return comment;
	}

	@XmlElement
	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getProjectID() {
		return projectID;
	}

	@XmlAttribute
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getUserID() {
		return userID;
	}

	@XmlAttribute
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCommentID() {
		return commentID;
	}

	@XmlAttribute
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

}