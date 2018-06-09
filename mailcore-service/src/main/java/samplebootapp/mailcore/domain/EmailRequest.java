package samplebootapp.mailcore.domain;

import java.io.Serializable;

public class EmailRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String sendThruKafka;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSendThruKafka() {
		return sendThruKafka;
	}

	public void setSendThruKafka(String sendThruKafka) {
		this.sendThruKafka = sendThruKafka;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmailRequest) {
			if (name.equalsIgnoreCase(((EmailRequest) obj).getName())
					&& email.equalsIgnoreCase(((EmailRequest) obj).getEmail()))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return getClass().getName() + "name: " + name + " ; email: " + email;
	}

}
