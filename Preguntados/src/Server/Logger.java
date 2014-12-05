package Server;

public class Logger {
	
	public static void info(String message) {
		System.out.println("INFO: " + message);
		ServerScreen.printLog(message);
	}
	
	public static void warn(String message) {
		System.out.println("WARN: " + message);
		ServerScreen.printLog(message);
	}
}
