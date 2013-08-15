package uk.ac.cam.dashboard.util;

public class Strings {
	
	// User
	public static final String USER_NOUSERNAME = "Annonymous";
	public static final String USER_NOSURNAME = "Annonymous";
	public static final String USER_NOEMAIL = "No email";
	public static final String USER_NOINST = "No institution";
	public static final String USER_NOSTATUS = "Student";
	public static final String USER_NOPHOTO = "none";

	// Deadlines
	public static final String DEADLINE_NOMESSAGE = "No message";
	public static final String DEADLINE_NOURL = "#";
	public static final String DEADLINE_DELETED = "Deadline deleted by owner";
	
	// Groups
	public static final String GROUP_AUTHEDIT = "You are not authorised to edit this group";
	
	// Notifications
	public static final String NOTIFICATION_SETGROUP = " added you to a group: ";
	public static final String NOTIFICATION_UPDATEGROUP = " updated a group you are in: ";
	public static final String NOTIFICATION_SETDEADLINE = " set you a deadline: ";
	public static final String NOTIFICATION_UPDATEDEADLINE = " updated a deadline: ";
	
	// Mail
	public static final String MAIL_SETDEADLINE_SUBJECT = " set you a deadline on CAM Supervisions";
	public static final String MAIL_SETDEADLINE_HEADER = "You have been set a new deadline on CAM Supervisions. The deadline details are below. To view the deadline, click the link to go to the CAM Supervisions app.";	
	public static final String MAIL_SETDEADLINE_FOOTER = "This is an automatically generated message. All replies will be directed to the setter of this deadline.";	
	
	// Authorisation exceptions
	public static final String AUTHEXCEPTION_GLOBAL = "Could not validate global API permissions.";
	public static final String AUTHEXCEPTION_GLOBAL_USER = "Could not validate API permissions for this user. Please include a valid crsid with your query.";
	public static final String AUTHEXCEPTION_USER = "Could not validate permissions for this user.";
	public static final String AUTHEXCEPTION_GENERAL = "Error validating permissions.";
	
}