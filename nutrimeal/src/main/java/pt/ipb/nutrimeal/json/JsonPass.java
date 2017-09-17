package pt.ipb.nutrimeal.json;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonPass  {
	
	String email, oldPass, newPass;
	
	public JsonPass() {
	}
	
	public JsonPass(String email){
		this.email = email;
	}

	
	public JsonPass(String email, String oldPass, String newPass) {
		this.email = email;
		this.oldPass = oldPass;
		this.newPass = newPass;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	
}
