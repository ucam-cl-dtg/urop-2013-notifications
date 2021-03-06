package uk.ac.cam.dashboard.models;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.collect.ImmutableMap;

@Entity
@Table(name = "API")
public class Api implements Mappable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="apiIdSeq") 
	@SequenceGenerator(name="apiIdSeq",sequenceName="API_SEQ", allocationSize=1)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="USER_CRSID")
	private User user;
	
	private String key;
	
	// Does not require a user
	private boolean globalPermissions = false;
	
	public Api() {
		String generatedKey = this.generateNewApiKey();
		this.setKey(generatedKey);
	}
	
	private String generateNewApiKey() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(200, random).toString(32);
	}

	public int getId() { return id; }
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user;}
	
	public boolean isGlobalPermissions() { return globalPermissions; }
	public void setGlobalPermissions(boolean globalPermissions) { this.globalPermissions = globalPermissions; }
	
	@Override
	public Map<String, ?> toMap(){
		return ImmutableMap.of("id", id, "key", key);
	}
	
}


