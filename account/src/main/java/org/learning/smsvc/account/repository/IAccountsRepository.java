package org.learning.smsvc.account.repository;

import org.learning.smsvc.account.entity.Accounts;
import org.learning.smsvc.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(long customerId);

    @Modifying //tell jpa that this query will modify the data in the table.
    @Transactional //tell jpa to execute the query inside a transaction, since this is not a simple read.
    void deleteByCustomerId(Long customerId);
}
