package demo.lib9;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoFirst extends Lib9 implements L9IState {

	public DemoFirst(MIDlet context) {
		super(context);
		// TODO Auto-generated constructor stub
		//初始化应用的状态
		changeState(this);
	}

	public void Init() {
		// TODO Auto-generated method stub

	}

	public void Update() {
		// TODO Auto-generated method stub
		//按5键或者D键盘的中间键将状态切换到消息对话框
		if(isKeyPressed(K_KEY_NUM5|K_KEY_FIRE)){
			//showMsgDialog函数中已经将当前状态入栈了
//			pushState();
			showMsgDialog("消息对话框", "你能0xFF0000看见0x000000这个消息对话框吗？", "确认", 180);
		}
	}
	private L9Str l9Str=new L9Str();
	public void Paint() {
		// TODO Auto-generated method stub
		//填充屏幕，在android中注意设置的颜色是严格按照0xAARRGGBB格式，与传统的0xRRGGBB不同
		//FG和FP是lib9引擎android版本中重要的对象
		fillScreen(0xFFFFFFFF);
        FG.setColor( 0xFFFF0000); //画笔颜色
        l9Str.drawLine(FG,"按5键显示消息对话框", 0, SCR_H/2, SCR_W, L9Str.K_Line_Align_Center);
        
	}

}
