package junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.naming.InsufficientResourcesException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import org.junit.*;
import org.junit.rules.*;

public class AssertTest {
    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
        private static final long serialVersionUID = 1L;
    }
    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }
        void deposit(int dollars) {
            balance += dollars;
        }
        void withdrow(int dollars) throws InsufficientResourcesException {
            if (balance < dollars) {
                throw new InsufficientResourcesException("balance only " + balance);
            }
            balance -= dollars;
        }
        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }
    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    // Test에서 account를 사용하기 위해 @Before에서 인스턴스 생성
    @Before
    public void createAccount() {
        account = new Account("an account name");
    }
    // 50 달러 입금 후 잔고가 0 이상인지 확인하는 테스트
    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }
    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        System.out.println("initialBalance : " + initialBalance);
        account.deposit(100);
        System.out.println("deposit After Balance : " + account.getBalance());
        // 현재 잔고가 초기 잔고보다 커야 함
        assertTrue(account.getBalance() > initialBalance);
        // 현재 잔고가 100인지 확인
        assertThat(account.getBalance(), equalTo(100));
    }
    @Test
    public void depositIncreasesBalance_hamcrestAssertTrue() {
        account.deposit(50);
        assertThat(account.getBalance() > 0, is(true));
    }
    @Test
    public void accountNameCheck() {
        // 실패 케이스
        // assertThat(account.getName(), startsWith("xyz")); // startsWith(Param 으로 시작하는 텍스트와 일치하는지)
        // 성공 케이스
        assertThat(account.getName(), startsWith("an"));
    }
    @Test
    @Ignore // 해당 테스트 무시
    public void comparesArraysFailing() {
        assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
    }
    @Test
    public void comparesCollectionsPassing() {
        assertThat(new String[] {"a"}, equalTo(new String[] {"a"}));
    }
    /*@Test
    public void testWithWorthlessAssertionComment() {
        account.deposit(50);
        assertThat("account balance is 100", account.getBalance(), equalTo(50));
    }*/
    @Test
    public void variousMatcherTests() {
        Account account = new Account("my big fat acct");
        assertThat(account.getName(), is(equalTo("my big fat acct")));

        assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct"))); // allOf() - 모두 맞아야함

        assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot"))); // anyOf() - 둘중에 하나만 맞으면 됨

        assertThat(account.getName(), not(equalTo("plunderings"))); // not

        assertThat(account.getName(), is(not(nullValue()))); // null값이 아닌 것
        assertThat(account.getName(), is(notNullValue()));   // null값이 아닌 것

        assertThat(account.getName(), isA(String.class)); // 클래스 타입 비교

        assertThat(account.getName(), equalTo("my big fat acct"));
    }
    @Test
    public void sameInstance() {
        Account a = new Account("a");
        Account aPrime = new Account("a");
        assertThat(a, not(org.hamcrest.CoreMatchers.sameInstance(aPrime)));
    }
    @Test
    public void moreMatcherTests() {
        Account account = new Account(null);
        assertThat(account.getName(), is(nullValue()));
    }
    @Test
    @SuppressWarnings("unchecked")
    public void items() {
        List<String> names = new ArrayList<>();
        names.add("Moe");
        names.add("Larry");
        names.add("Curly");
        assertThat(names, hasItem("Curly"));    // Curly element가 있는지 확인
        assertThat(names, hasItems("Curly", "Moe")); // Curly, Moe element가 있는지 확인
        assertThat(names, hasItem(endsWith("y"))); // 'y'로 끝나는 element가 있는지 확인
        assertThat(names, hasItems(endsWith("y"), startsWith("C"))); // 'y'로 끝나거나 'C'로 시작하는 element가 있는지 확인
        assertThat(names, not(everyItem(endsWith("y"))));
    }
}
