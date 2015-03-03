# Scenario
This sample illustrates the issue when many concurrent transactions are modifiying Account balance. Account can have many Card entities bound. Transactions are related to Order and last in time. Each Thread executes as follows:
1) client requests '/order/{hashId}' for first available Order by given card hash id
2) client starts new tx for given order - '/tx/{orderId}/start'
3) client completes tx - '/tx/{txId}/stop/{amount}' where the tx amount is subtracted from Account balance.
# Entity Locking
Account and Order entities are versioned with @javax.persistence.Version. On 3) step Account entity is locked with pessimistic write lock:
```
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account findOne(Integer integer);
}
```
#Testing
Concurrent access is tested with JMeter (http://jmeter.apache.org/) src/main/resources/StressTest.jmx. NB: Extra libs http://jmeter-plugins.org/downloads/file/JMeterPlugins-ExtrasLibs-1.2.0.zip should installed to JMeter home due to usage of http://jmeter-plugins.org/wiki/JSONPathExtractor/. With these specific settings on an average laptop you can get around 10% of errors for TxEnd request:
```
{
  "timestamp": 1425407408204,
  "status": 500,
  "error": "Internal Server Error",
  "exception": "org.springframework.orm.ObjectOptimisticLockingFailureException",
  "message": "Object of class [sample.data.jpa.domain.Account] with identifier [1]: optimistic locking failed; nested exception is org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [sample.data.jpa.domain.Account#1]",
  "path": "/tx/1443/stop/46.4"
}
```
