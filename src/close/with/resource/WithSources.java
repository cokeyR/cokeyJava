//package close.with.resource;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//
//public class WithSources {
//
//	public static void main(String[] args) {
//		
//		//try块会自动关闭资源
//		try(BufferedWriter writer=new BufferedWriter(new FileWriter(new File("writer.txt")))) {
//			writer.write("ahah");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}
