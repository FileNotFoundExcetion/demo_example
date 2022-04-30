package com.example.demo;

import com.example.demo.config.SpringContextUtil;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.Count;
import com.example.demo.service.Test01;
import com.example.demo.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryState;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	@Resource
	UserMapper userMapper;

	@Resource
	UserServiceImpl userServiceImpl;

	@Test
	void test() {
		User user=new User();
		user.setAge(70);
		user.setEmail("www.@test.com");
		user.setName("xiaobai123");
		user.setCreateTime(LocalDateTime.now());
		userMapper.insert(user);
		System.out.println(user);
	}

	@Test
	public void test01(){
		userServiceImpl.test();
	}


	@Test
	public void testName(){
		System.out.println(SpringContextUtil.containsBean("cpm"));
	//	Count bean =(Count) SpringContextUtil.getBean("cpm.switch.basicBusinessDataManag");
	//	System.out.println(bean.count());
	}

	@Resource
	ObjectProvider<Count> objectProvider;

	@Test
	public void TestBasic(){
		Count ifAvailable = objectProvider.getIfAvailable();
		System.out.println(ifAvailable);
	}


	@Test
	public void applyToEither() {
		 CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}        return "0.001";
		}).thenAccept(System.out::println);

	}

	@Test
	public void testCombine() {
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {		try {
			Thread.sleep(3000);
			System.out.println("cf1 is doning");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // 返回结果
			return "hello";
		});
		CompletableFuture<String> result = cf1.thenCombine(CompletableFuture.supplyAsync(() -> {		try {
			Thread.sleep(500);
			System.out.println("cf2 is doning");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			return "world";
		}), (x, y) -> x + y);//合并2个操作结果
		String join = result.join();
		System.out.println(join);
	}

	@Test
	public void thenRun() {
		CompletableFuture.supplyAsync(() -> "hello").thenRun(() -> System.out.println("hello world"));
	}

	@Test
	public void testCompose() throws InterruptedException {
		         CompletableFuture.supplyAsync(()->
						 {
							 try {
								 Thread.sleep(30000);
							 } catch (InterruptedException e) {
								 e.printStackTrace();
							 }
							 return  "hello";
						 })
				      .thenCompose(result -> CompletableFuture.runAsync(() -> {
					  System.out.println(result);
					  System.out.println("result");
				}));
             Thread.currentThread().join();
	}
	@Resource
	RetryTemplate retryTemplate1;

    @Test
	public void oo() throws InterruptedException {
		long  startTime = System. currentTimeMillis ();
		/*IntStream. range ( 0 ,  10 ).forEach(index -> {*/
			try  {
				Thread. sleep ( 100 );
				RetryState state =  new DefaultRetryState( "circuit" ,  false );
				String result =  retryTemplate1 .execute(context -> {
					log .info( "retry {} times" , context.getRetryCount());
					if  (System. currentTimeMillis () -  startTime  >  1300  && System. currentTimeMillis () -  startTime  <  1500 ) {
						return  "success" ;
					}
					throw new  RuntimeException( "timeout" );
				},  new  RecoveryCallback<String>() {
					@Override
					public  String recover(RetryContext context)  throws  Exception {
						return  "default" ;
					}
				}, state);
				log .info( "result: {}" , result);
			}  catch  (Exception e) {
				log .error( "error: {}" , e.getMessage());
			}
		/*});*/
		Thread.currentThread().join();
	}
     @Resource
	 Test01 test01;

	@Test
	public void test001(){
		test01.test();
	}

	@WithMockUser(username = "bob",password = "123456")
	@Test
	public void Test01(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
	}
}
