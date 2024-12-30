package com.codewithravi.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	@Query("Select R From Ride R where R.status=COMPLETED AND R.user.id=userId")
	public List<Ride> getCompletedRides(@Param("userId")Integer userId);

}
