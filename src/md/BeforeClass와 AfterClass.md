* @BeforeClass
- 보통은 테스트 수준의 초기화 (@Before)면 충분하지만, 테스트 클래스 수준의 초기화가 필요한 경우 사용
- 테스트를 처음 실행하기 전, 한번만 실행됨
* @AfterClass
- 테스트를 처음 실행한 후, 한번만 실행됨

예)
```` java
public class AssertMoreTest {
    @BeforeClass
    public static void initializeSomethingReallyExpensive() }
        // 실행 순서 - 1
    }
    @AfterClass
    public static void cleanUpSomethingReallyExpensive() }
        // 실행 순서 - 8
    }
    @Before
    public void createAccount() { 
        // 실행 순서 - 2 / 5
    }
    @After
    public void closeConnections() { 
        // 실행 순서 - 4 / 7
    }
    @Test
    public void depositIncreasesBalance() { 
        // 실행 순서 - 3
    }
    @Test
    public void hasPositiveBalance() { 
        // 실행 순서 - 6
    }
}
````
