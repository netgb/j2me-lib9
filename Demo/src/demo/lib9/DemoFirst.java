package demo.lib9;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoFirst extends Lib9 implements L9IState {

	public DemoFirst(MIDlet context) {
		super(context);
		// TODO Auto-generated constructor stub
		//��ʼ��Ӧ�õ�״̬
		changeState(this);
	}

	public void Init() {
		// TODO Auto-generated method stub

	}

	public void Update() {
		// TODO Auto-generated method stub
		//��5������D���̵��м����״̬�л�����Ϣ�Ի���
		if(isKeyPressed(K_KEY_NUM5|K_KEY_FIRE)){
			//showMsgDialog�������Ѿ�����ǰ״̬��ջ��
//			pushState();
			showMsgDialog("��Ϣ�Ի���", "����0xFF0000����0x000000�����Ϣ�Ի�����", "ȷ��", 180);
		}
	}
	private L9Str l9Str=new L9Str();
	public void Paint() {
		// TODO Auto-generated method stub
		//�����Ļ����android��ע�����õ���ɫ���ϸ���0xAARRGGBB��ʽ���봫ͳ��0xRRGGBB��ͬ
		//FG��FP��lib9����android�汾����Ҫ�Ķ���
		fillScreen(0xFFFFFFFF);
        FG.setColor( 0xFFFF0000); //������ɫ
        l9Str.drawLine(FG,"��5����ʾ��Ϣ�Ի���", 0, SCR_H/2, SCR_W, L9Str.K_Line_Align_Center);
        
	}

}