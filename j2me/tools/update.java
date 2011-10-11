import java.io.*;
import java.util.Vector;
public class update {
	public static String[] sLines=new String[1000];
	public static boolean bBlank=true;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		if(true){
//			String sFile="java update  u    MANIFEST.MF MIDlet-Name";
//			String[] rs=sFile.split(" ");
//			Vector v=new Vector();
//			int index=0;
//			for(int i=0;i<rs.length;i++){
//				if(rs[i].trim().length()>0)v.add(rs[i]);
//			}
//			Object[] out=v.toArray();
//			for(int i=0;i<out.length;i++){
//				System.out.println(out[i]);
//			}			
//			return;
//		}
		String bFromFile=null;
		for(int i=0;i<args.length;i++){
			if(args[i].indexOf("@")>0){
				bFromFile=args[i];
			}
			if(args[i].indexOf("@")>0){
				bBlank=false;
			}
		}
		if(bFromFile!=null)
			cmdFromFile(bFromFile);
		else
			excuteCmd(args);
//		System.out.println(args.length);
//		cmdFromFile("jad.txt");
	}
	public static void appendItem(String sFile,String key,String value){
		try {
			PrintWriter out = new PrintWriter(new FileWriter(sFile,true),true);
			out.println(key+":"+value);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateItem(String sFile,String key,String value){
		try{
		LineNumberReader lnr=new LineNumberReader(new FileReader(sFile));
		String s=lnr.readLine();
		int iCount=0;
		while(s!=null){
			if(s.trim().startsWith(key)==true){
				s=key+":"+value;
			}
			sLines[iCount++]=s;
			s=lnr.readLine();
		}
		PrintWriter out=new PrintWriter(new FileWriter(sFile,false),true);
		for(int i=0;i<iCount;i++){
			out.println(sLines[i]);
		}		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void deleteItem(String sFile,String key){
		try{
		LineNumberReader lnr=new LineNumberReader(new FileReader(sFile));
		String s=lnr.readLine();
		int iCount=0;
		while(s!=null){
			if(s.trim().startsWith(key)==false){
				sLines[iCount++]=s;
			}
			s=lnr.readLine();
		}
		PrintWriter out=new PrintWriter(new FileWriter(sFile,false),true);
		for(int i=0;i<iCount;i++){
			out.println(sLines[i]);
		}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void cmdFromFile(String sFile){
		Vector sVec=new Vector();	
		 try{
			LineNumberReader lnr=new LineNumberReader(new FileReader(sFile));
			String s=lnr.readLine();
			while(s!=null){
				sVec.add(s);
				System.out.println(s);
				s=lnr.readLine();
			}
			for(int i=0;i<sVec.size();i++){
				excuteCmd(((String)sVec.elementAt(i)).split("\\|"));
			}
		 }catch(Exception e){
				e.printStackTrace();
		 }
	}
	public static void excuteCmd(String[] sCmd){
		
		for(int i=0;i<sCmd.length;i++){
			System.out.println(sCmd[i]);
			sCmd[i]=sCmd[i].trim();//remove blank
			
		}
		
		String sFile="";//args[1];
		String sFile2="";//get size
		char cmd=0;//args[0].charAt(0);
		String key="";
		String value="";//args[2];
		switch(sCmd.length){
			case 3:{
				cmd=sCmd[0].charAt(0);
				if(cmd=='a'){
					value=sCmd[2];
				}
				else if(cmd=='d'){
					key=sCmd[2];
				}
				else{
					drawHelp();
				}
			}
				break;
			case 4:{
				cmd=sCmd[0].charAt(0);
				if(cmd=='a'){
					key=sCmd[2];
					value=sCmd[3];
				}
				else if(cmd=='u'){
					key=sCmd[2];
					value=sCmd[3];
				}
				else if(cmd=='s'){
					sFile2=sCmd[2];
					key=sCmd[3];
				}
				else{
					drawHelp();
				}
			}
				break;
			default:
				drawHelp();
		}
		sFile=sCmd[1];
		
		if(cmd=='s')
			value=String.valueOf(new File(sFile2).length());
		
		if(bBlank)
			value=" "+value.trim();
		else
			value=value.trim();
		
		try {
			switch(cmd){
				case 'a':
					appendItem(sFile,key,value);
					break;
				case 'u':
					updateItem(sFile,key,value);
					break;
				case 'd':
					deleteItem(sFile,key);
					break;
				case 's':{
					updateItem(sFile,key,value);
				}
					break;
				default:
					drawHelp();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public static void drawHelp(){
		System.out.println("usage1:update option file1 [file2] key [value] ");
		System.out.println("  [option] --> a/u/d/s ");
		System.out.println("    a --> append item to file1! ");
		System.out.println("    u --> modify one item of the file1! ");
		System.out.println("    d --> delete one item of the file1! ");
		System.out.println("    s --> get size of file2 and use the size to modify one item of file1! ");
		
		System.out.println("usage1:update @file");
		System.out.println("      :file is txt file that contains usage1's Parameters");		
		System.exit(0);
	}
}
