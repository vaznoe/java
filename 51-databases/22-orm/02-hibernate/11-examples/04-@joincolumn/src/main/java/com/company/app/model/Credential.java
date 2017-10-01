package com.company.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
describe credential;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| CREDENTIAL_ID | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| USER_ID       | bigint(20)   | NO   | UNI | NULL    |                |
| USERNAME      | varchar(50)  | NO   |     | NULL    |                |
| PASSWORD      | varchar(100) | NO   |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+
 */

@Data
@Entity
@Table(name="credential")
public class Credential {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CREDENTIAL_ID")
	public Long credentialId;

	@OneToOne(cascade=CascadeType.ALL)
	// name="USER_ID" - `credential` table fk `USER_ID`
	// referencedColumnName = "USER_ID" - `finances_user` table pk USER_ID
	@JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
	public User user;
	
	@Column(name="USERNAME")
	private String username;

	@Column(name="PASSWORD")
	private String password;

}
