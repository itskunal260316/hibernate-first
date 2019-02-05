import org.junit.Test;

import com.lti.dao.AccountDao;
import com.lti.dao.GenericDao;
import com.lti.entity.Account;
import com.lti.entity.AccountTransaction;
import com.lti.service.AccountService;

import antlr.collections.List;

public class AccountTest {

	@Test
	public void openAccount() {
		AccountService a = new AccountService();
		a.openAccount("Rushab", "savings", 25000);
	}

	@Test

	public void withdraw() {
		AccountService a = new AccountService();
		a.withdraw(22, 100);
	}

	@Test

	public void deposit() {
		AccountService a = new AccountService();
		a.deposit(22, 100);
	}

	@Test
	public void transfer() {
		AccountService a = new AccountService();
		a.transfer(22, 21, 1100);
	}

	@Test
	public void balance() {
		AccountService a = new AccountService();
		a.balance(22);
	}

	@Test
	public void fetchMiniStatement() {
		AccountService a = new AccountService();
		a.ministatement(22);
	}
	
	@Test
	public void fetchAccounts() {
		AccountDao dao=new AccountDao();
		
		java.util.List<Account> ac = dao.fetchAccounts("savings", 1100);
		for(Account a: ac)
		{
		System.out.println( a.getAcno() + a.getName());
	}
	}
	
	@Test
	public void fetchByName() {
		AccountDao dao=new AccountDao();
		java.util.List<AccountTransaction> ac = dao.fetchByName("Mrunal");
		for(AccountTransaction a:ac) {
			System.out.println(a.getAccount() +" " +  a.getAmount()  +" "+ a.getType());
		}
	} 

}
