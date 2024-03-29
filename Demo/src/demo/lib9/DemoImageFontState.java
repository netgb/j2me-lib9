package demo.lib9;

import java.io.InputStream;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoImageFontState extends Lib9 implements L9IState {

	public DemoImageFontState(MIDlet context) {
		super(context);
		// TODO Auto-generated constructor stub
		//初始化应用的状态
		changeState(this);
	}
    public L9Sprite spriteImgFont;
    public L9Str l9Str;
	public void Init() {
		// TODO Auto-generated method stub
        //创建图片字体sprite对象 
		spriteImgFont = new L9Sprite("/imgfont");
        
        //创建字符串处理对象
        l9Str = new L9Str();
        //设置图片字体映射字符串，注意这个字符串字符的顺序应该与图片sprite资源中的module顺序保持一致哦
        String mapChar = "菜单返回取消";
        l9Str.setImageFont(spriteImgFont, mapChar);
        //\n将使得绘制字符串时强制换行，l9Str还支持在资源字符串中使用0p1的方式来设置某一部分字符串的颜色值
        String str = "0p0菜单\n0p1返回菜单\n0p2取消返回菜单";
        //设置字符串的分页信息
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
