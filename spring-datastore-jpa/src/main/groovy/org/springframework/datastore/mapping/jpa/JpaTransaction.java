package org.springframework.datastore.mapping.jpa;

import org.springframework.datastore.mapping.transactions.Transaction;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;

public class JpaTransaction implements Transaction {

	private TransactionStatus transaction;
	private JpaTransactionManager transactionManager;

	public JpaTransaction(JpaTransactionManager transactionManager, TransactionStatus transaction) {
		this.transaction = transaction;
		this.transactionManager = transactionManager;
	}

	@Override
	public void commit() {
		transactionManager.commit(transaction);
	}

	@Override
	public void rollback() {
		transactionManager.rollback(transaction);
	}

	@Override
	public Object getNativeTransaction() {
		return transaction;
	}

	@Override
	public boolean isActive() {
		return !transaction.isCompleted();		
	}

	@Override
	public void setTimeout(int timeout) {
		transactionManager.setDefaultTimeout(timeout);
	}

}
