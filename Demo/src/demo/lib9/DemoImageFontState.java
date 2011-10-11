package demo.lib9;

import java.io.InputStream;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoImageFontState extends Lib9 implements L9IState {

	public DemoImageFontState(MIDlet context) {
		super(context);
		// TODO Auto-generated constructor stub
		//��ʼ��Ӧ�õ�״̬
		changeState(this);
	}
    public L9Sprite spriteImgFont;
    public L9Str l9Str;
	public void Init() {
		// TODO Auto-generated method stub
        //����ͼƬ����sprite���� 
		spriteImgFont = new L9Sprite("/imgfont");
        
        //�����ַ�����������
        l9Str = new L9Str();
        //����ͼƬ����ӳ���ַ�����ע������ַ����ַ���˳��Ӧ����ͼƬsprite��Դ�е�module˳�򱣳�һ��Ŷ
        String mapChar = "�˵�����ȡ��";
        l9Str.setImageFont(spriteImgFont, mapChar);
        //\n��ʹ�û����ַ���ʱǿ�ƻ��У�l9Str��֧������Դ�ַ�����ʹ��0p1�ķ�ʽ������ĳһ�����ַ�������ɫֵ
        String str = "0p0�˵�\n0p1���ز˵�\n0p2ȡ�����ز˵�";
        //�����ַ����ķ�ҳ��Ϣ
        l9Str.setPageSize(str, SCR_W - 8, SCR_H, 1);
	}

	public void Update() {
		// TODO Auto-generated method stub
        if (l9Str == null) {
            return;
        }
        if (isKeyPressed(Lib9.K_KEY_NUM5|Lib9.K_KEY_FIRE)) {
            quitApp();
        }
	}

	public void Paint() {
		// TODO Auto-generated method stub
        if (l9Str == null) {
            return;
        }
        FG.setColor(0xFF000000);
        l9Str.drawPage(FG,0, 4, 50, l9Str.K_Line_Align_Center);
	}

}