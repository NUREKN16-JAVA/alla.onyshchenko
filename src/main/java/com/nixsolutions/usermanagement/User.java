package com.nixsolutions.usermanagement;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstname;
	private String lastname;
	private Date dateOfBithd;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateOfBithd() {
		return dateOfBithd;
	}
	public void setDateOfBithd(Date dateOfBithd) {
		this.dateOfBithd = dateOfBithd;
	}
	public Object getFullName() {
		return getLastname() + ", " + getFirstname();
	}
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentDate = calendar.get(Calendar.DATE);

        calendar.setTime(dateOfBithd);
        final int birthYear = calendar.get(Calendar.YEAR);
        final int birthMonth = calendar.get(Calendar.MONTH);
        final int birthDate = calendar.get(Calendar.DATE);

        int age = currentYear - birthYear;
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDate < birthDate)) {
            --age;
        }
        return age;
	}
	
}
