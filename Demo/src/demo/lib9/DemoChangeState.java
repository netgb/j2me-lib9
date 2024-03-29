package demo.lib9;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoChangeState extends Lib9 implements L9IState {
	public DemoChangeState(MIDlet context) {
		super(context);
		// TODO Auto-generated constructor stub
		//初始化应用的状态
		changeState(this);
	}
    final int K_Demo_First = 0;
    final int K_Demo_ImageAndAnimation = 1;
    final int K_Demo_ImageFont = 2;
    public DemoListState list;
    public DemoStateImageAndAnimation imageAndAnimation;
    public DemoStateImageFont imageFont;
	public void Init() {
		// TODO Auto-generated method stub
		l9Str=new L9Str();
	}

	public void Update() {
		// TODO Auto-generated method stub
		if(isKeyPressed(K_KEY_NUM5|K_KEY_FIRE)){
            pushState();
            list = new DemoListState(this);
            list.setList("示例列表", new String[] {
                         "第一个示例", "显示图片和播放动画", "如何使用图片字体"
            }, 180, 200);
            list.setListIndex(0);
            changeState(list);
            return;
        }
        if (list != null) {
            int listIndex = list.getListIndex();
            switch (listIndex) {
            case K_Demo_First:

//                pushState();//显示消息对话框函数将会存储当前状态
                showMsgDialog("消息对话框", "你看见了0xFF0000消息对话框0x000000了吗？", "确认",
                              180);
                break;
            case K_Demo_ImageAndAnimation:
                pushState();
                imageAndAnimation = new DemoStateImageAndAnimation(this);
                changeState(imageAndAnimation);
                break;
            case K_Demo_ImageFont:
                pushState();
                imageFont = new DemoStateImageFont(this);
                changeState(imageFont);
                break;
            }
            //这句话很重要
            list.setListIndex( -1);
        }
	}

	public void Paint() {
		// TODO Auto-generated method stub
        fillScreen(0xFFFFFFFF);
        FG.setColor(0xFF000000);
        l9Str.drawLine(FG, "按5键示例演示", 0, (SCR_H - 20) >> 1,SCR_W,L9Str.K_Line_Align_Center);
//        FG.drawString("按5键示例演示", SCR_W >> 1, (SCR_H - 20) >> 1,FG.TOP | FG.HCENTER);
	}
    public L9Str l9Str;
    public void drawSoftKey(String left, String right) {
        if (left != null) {
            l9Str.drawLine(FG,left, 0, SCR_H - 20);
        }
        if (right != null) {
            l9Str.drawLine(FG,right, 0, SCR_H - 20, SCR_W,
                           l9Str.K_Line_Align_Right);
        }
    }
}
