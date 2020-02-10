- @Before 어노테이션(메소드)을 사용하면, 테스트 내에 중복된 초기화 코드를 줄일 수 있으며, 유지보수성을 높일 수 있다.
- @Before 메소드는 @Test 메소드 보다 앞서 실행됨
    -> 예) 테스트 전 변수 초기화, 임의 파일 삭제 등
- 여러 개의 @Before 메소드가 있는 경우, 실행 순서를 보장하지 않음
    -> 따라서, 순서가 보장되어야 하는 경우 @Before로 결합하여 순서대로 실행되도록 해야 함
    -> @Before createAccount
       @Before resetAccountLogs
       @Test depositIncreasesBalance

- @After 메소드는 클래스에 있는 각 테스트를 수행한 뒤 실행되며, 테스트가 실패하더라도 실행됨(finally와 비슷)
    -> 예) DB Connection Close()
    -> @Before createAccount - 1
       @Test depositIncreasesBalance - 2
       @After closeConnections - 3
       @Before createAccount - 1
       @Test hasPositiveBalance - 2
       @After closeConnections - 3