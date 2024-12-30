package com.codewithravi.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithravi.entity.License;

public interface LicenseRepository extends JpaRepository<License, Integer>{

}
