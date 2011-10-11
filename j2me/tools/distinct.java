import java.io.*;

public class distinct {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

//        if (true) {
//            System.out.println(args.length);
//            for (int i = 0; i < args.length; i++) {
//                System.out.println(args[i]);
//            }
//            return;
//        }
        if (args.length != 2) {
            System.out.println("参数错误!");
            System.out.println("格式：java distinct in.txt out.txt");
            return;
        }

        String sFile_In = args[0];
        String sFile_Out = args[1];
//            String sText = "pk小宝大小宠物店宝宝店";
        String sText = "";
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(
                    sFile_In));
            String s = lnr.readLine();
            while (s != null) {
                sText += s;
                s = lnr.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sText.length(); i++) {
            char c = sText.charAt(i);
            char[] c_arr = new char[] {c};
            String s = new String(c_arr);
            if (sb.indexOf(s) == -1) {
                sb.append(c);
            }
        }
        try {
            PrintWriter out = new PrintWriter(new FileWriter(sFile_Out, false), true);
            out.println(sb.toString());
        } catch (IOException ex) {
        }
//            System.out.println(sb.toString());
        return ;
    }
}
