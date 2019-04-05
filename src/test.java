import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class test {

	public static void main(String[] args) throws IOException {
		 	File file = new File("C://Users//admin//Pictures/1.jpg");
		 	if(!file.exists()){ 
		 		//先得到文件的上级目录，并创建上级目录，在创建文件
		 		file.getParentFile().mkdir(); 
		 		try { 
		 			//创建文件
		 			file.createNewFile(); 
		 			} catch (IOException e) {
		 				e.printStackTrace(); 
		 				}
		 		}
		 		
		 	
		 	byte[]a=image2byte("C://Users//admin//Pictures//Camera Roll//1242397_102231006_2.jpg");
	        System.out.println(a);
	        byte2image(a,file); 
	        
	         
	}
	
	//图片到byte数组
	  public static byte[] image2byte(String path){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	  }
	  
	  
	  //byte数组到图片
	  public static void byte2image(byte[] data,File f){
	    try{
	    FileImageOutputStream imageOutput = new FileImageOutputStream(f);
	    imageOutput.write(data, 0, data.length);
	    imageOutput.close();
	    System.out.println("Make Picture success,Please find image in " + f);
	    } catch(Exception ex) {
	      System.out.println("Exception: " + ex);
	      ex.printStackTrace();
	    }
	  }
	  //byte数组到16进制字符串
	  public String byte2string(byte[] data){
	    if(data==null||data.length<=1) return "0x";
	    if(data.length>200000) return "0x";
	    StringBuffer sb = new StringBuffer();
	    int buf[] = new int[data.length];
	    //byte数组转化成十进制
	    for(int k=0;k<data.length;k++){
	      buf[k] = data[k]<0?(data[k]+256):(data[k]);
	    }
	    //十进制转化成十六进制
	    for(int k=0;k<buf.length;k++){
	      if(buf[k]<16) sb.append("0"+Integer.toHexString(buf[k]));
	      else sb.append(Integer.toHexString(buf[k]));
	    }
	    return "0x"+sb.toString().toUpperCase();
	  }

}
