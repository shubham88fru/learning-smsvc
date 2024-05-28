package org.learning.smsvc.account.repository;

import org.learning.smsvc.account.entity.Accounts;
import org.learning.smsvc.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts, Long> {
}
