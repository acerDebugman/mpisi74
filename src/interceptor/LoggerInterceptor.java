package interceptor;

public class LoggerInterceptor {
	public void beforeLog(){
		System.out.println("before logger~");
	}
	public void afterLog(){
		System.out.println("after logger~");
	}
	public void leaveApplicationInitLog(){
		System.out.println("leave application logger~");
	}
}
