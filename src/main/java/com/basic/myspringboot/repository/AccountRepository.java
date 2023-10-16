package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // select * from accounts where username = 'spring'

    //No property 'userName' found for type 'Account' Did you mean ''username''
    //이름이 중요함! 대문자나 소문자 신경써서 적어야됨
    Optional<Account> findByUsername(String username);

}
