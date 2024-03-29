package demo.lib9;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoHttpState extends Lib9 implements L9IState {
    private final int K_Status_None = 0;
    private final int K_Status_Being_Get = 1;
    private final int K_Status_Being_Send = 2;
    private int iStatus;
    private L9Http l9http;
    private L9Str l9Str;
    private void ShowMessageBox(String msg) {
        showMsgDialog("消息对话框", msg, "确认", 180);
    }
	public DemoHttpState(MIDlet context) {
		super(context);
		// TODO Auto-generated constructor stub
		//初始化应用的状态
		changeState(this);
	}

	public void Init() {
		// TODO Auto-generated method stub
        l9http = new L9Http();
        l9Str = new L9Str();
        //\n将使得绘制字符串时强制换行，l9Str还支持在资源字符串中使用0xFF0000的方式来设置某一部分字符串的颜色值
        String str = "按1键取数据" + "\n按3键发送数据";
        //设置字符串的分页信息
        l9Str.setPageSize(str, SCR_W - 8, SCR_H, 10);
	}

	public void Update() {
		// TODO Auto-generated method stub
        if (l9Str == null) {
            return;
        }
        if (iStatus == K_Status_None) {
            if (isKeyPressed(K_KEY_NUM1)) {
                iStatus = K_Status_Being_Get;
                l9http.setFirstJudgeHttpWay(-1);
                byte[] bin = L9Util.getIntBytes(K_Status_Being_Get);
                //注意联网测试，在android模拟器中使用10.0.2.2代表本机，类似pc上的localhost
                //l9http.openHttp("http://localhost/Lib9/testHttp.aspx", bin);
                l9http.openHttp("http://localhost/lib9/testHttp.aspx", bin);
            }
            if (isKeyPressed(K_KEY_NUM3)) {
                iStatus = K_Status_Being_Send;
                //将数据发送到服务器
                L9OutputStream out = new L9OutputStream();
                out.writeInt(K_Status_Being_Send); //存储标记
                out.writeString("张三");
                out.writeInt(95);
                out.writeString("备注");
              //注意联网测试，在android模拟器中使用10.0.2.2代表本机，类似pc上的localhost
//                l9http.openHttp("http://localhost/Lib9/testHttp.aspx",
//                                out.getBytes());
                l9http.openHttp("http://localhost/lib9/testHttp.aspx",
                        out.getBytes());
            }
        } else {
            if (!l9http.isError() && !l9http.isInProcess()) { //http处理结束
                switch (iStatus) {
                case K_Status_Being_Get:
                    byte[] bin = l9http.getData();
                    L9InputStream in = new L9InputStream(bin, 0, bin.length);
                    String name = in.readString();
                    int score = in.readInt();
                    String remark = in.readString();
                    ShowMessageBox("来自服务器的消息：\n" + name + "," + score + "," +
                                   remark);
                    break;
                case K_Status_Being_Send:
                    ShowMessageBox("数据发送完毕！");
                    break;
                }
                iStatus = K_Status_None;
            } else if (l9http.isError()) {
                ShowMessageBox("联网过程中出现错误");
                iStatus = K_Status_None;
            }
        }
	}

	public void Paint() {
		// TODO Auto-generated method stub
        if (l9Str == null) {
            return;
        }
        fillScreen(0xFFFFFFFF);
        if (iStatus == K_Status_None) {
            FG.setColor(0xFF000000);
            l9Str.drawPage(FG,0, 4, (SCR_H - 50) >> 1,
                           l9Str.K_Line_Align_Center);
        } else {
            String str = "正在0xFF0000发送数据0x000000中";
            if (iStatus == K_Status_Being_Get) {
                str = "正在0xFF0000接收数据0x000000中";
            }
            FG.setColor(0xFF000000);
            l9Str.drawLine(FG,str, 0, SCR_H >> 1, SCR_W,
                           l9Str.K_Line_Align_Center);
        }
	}

}
