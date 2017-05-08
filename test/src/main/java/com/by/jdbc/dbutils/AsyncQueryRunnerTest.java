package by.jdbc.dbutils;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbutils.AbstractQueryRunner;

public class AsyncQueryRunnerTest {
	public static void main(String[] args) throws SQLException {
		//创建一个线程池
		ExecutorService executorService = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100000), new ThreadPoolExecutor.CallerRunsPolicy());
		//使用指定的线程池和QueryRunner构建AsyncQueryRunner
		AbstractQueryRunner asyncRun = new org.apache.commons.dbutils.AsyncQueryRunner(executorService);
		//模拟一次长时间的数据库更新操作
		Future<Integer> updateCallable = ((org.apache.commons.dbutils.AsyncQueryRunner) asyncRun).update("UPDATE User SET password=?","456");
		//模拟耗时较长的数据库查询操作,请注意返回数据的格式是List<User>
		//Future<List<User>> queryCallable = asyncRun.query("select * from User", new BeanListHandler<User>(User.class));
		try{
		  Integer result = updateCallable.get();
		  //限制该数据库操作的超时时间为5000毫秒
//			Integer result = updateCallable.get(5000, TimeUnit.SECONDS);
		  System.out.println("数据库操作结果:\t" + result);
//			List<User> userList = queryCallable.get();
//			if(userList != null){
//				System.out.println(userList);
//			}
		}catch (Exception e) {
		  //  handle exception
		  e.printStackTrace();
		}
		System.out.println("测试结束");
	}
	public static void test(){
//		ExecutorCompletionService<Integer> executor =
//			    new ExecutorCompletionService<Integer>( Executors.newCachedThreadPool() );
//			AsyncQueryRunner asyncRun = new AsyncQueryRunner( ConnectionManager.dataSource,executor );
//
//			try
//			{
//			    // Create a Callable for the update call
//			    Callable<Integer> callable = asyncRun.update( "UPDATE Person SET height=? WHERE name=?",
//			                                                  2.05, "John Doe" );
//			    // Submit the Callable to the executor
//			    executor.submit( callable );
//			} catch(SQLException sqle) {
//			    // Handle it
//			}
//
//			// Sometime later (or in another thread)
//			try
//			{
//			   // Get the result of the update
//			   Integer updates = executor.take().get();
//			} catch(InterruptedException ie) {
//			    // Handle it
//			}
	}

}
