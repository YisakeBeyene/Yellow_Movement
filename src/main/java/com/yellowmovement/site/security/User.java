package com.yellowmovement.site.security;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long userId;
	
	public User( String name,String email,int enable,String password,String sex) {
		this.name = name;
		this.email = email;
		this.enabled = enable;
		this.password = password;
		this.sex = sex;
	}
	public User() {

	}
	@NotBlank(message = "You must have a Name.")
	private String name;

	@Column(unique = true)
	@NotBlank(message = "Email is Required.")
	private String email;
	
	@NotBlank(message = "Password is Required.")
	private String password;

	@NotBlank
	private String sex;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
			joinColumns= {@JoinColumn(name="user_id")},
			inverseJoinColumns= {@JoinColumn(name="role_id")})
	private Set<Role> roles;

	@Column(name = "enabled")
	private int enabled;

	private Date joiningDate;

	public String dateString(){
		SimpleDateFormat format = new SimpleDateFormat("h:mm a - EEE, MMM d");
		return format.format(this.joiningDate);
	}
	private String profilePic;

	@PrePersist
	void joiningDate() {
		this.joiningDate = new Date();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = roles
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toSet());

		return authorities;
	}


	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}



