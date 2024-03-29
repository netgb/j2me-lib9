package demo.lib9;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import lib9.j2me.*;

public class DemoListState extends L9DialogBackground implements L9IState {
	private Lib9 lib9;
	private L9Str l9Str;

	private int dialogX;
	private int dialogY;
	private int dialogW;
	private int dialogH;
	private int dialogLineSpace;
	private int dialogLineOffsetX = 4;
	private int dialogLineColor = 0xFF000000;
	private int dialogBarColor = 0xFFffc0c0;

	private String listTitle;
	private String[] listString;
	private int listIndex=-1;

	final int K_FONT_H = 20;
	final int K_Top_H = 24;

	/**
	 * 设置列表框信息
	 * 
	 * @param title
	 *            String
	 * @param list
	 *            String[]
	 * @param listW
	 *            int
	 * @param listH
	 *            int
	 */
	public void setList(String title, String[] list, int listW, int listH) {
		listTitle = title;
		listString = list;

		dialogW = listW;
		dialogH = listString.length * (K_FONT_H + dialogLineSpace) + K_Top_H
				+ 5;
		if (dialogH < listH) {
			dialogH = listH;
		}
		dialogX = (lib9.SCR_W - listW) >> 1;
		;
		dialogY = (lib9.SCR_H - (dialogH + K_Top_H)) >> 1;
	}

	public DemoListState(Lib9 lib9) {
		super(lib9.FG);
		// TODO Auto-generated constructor stub
		this.lib9 = lib9;
	}

	/**
	 * 设置对话框格式信息，比如：textOffsetX为要绘制的文本到边框的偏移量,lineSpace为文本每一行的间距
	 * 
	 * @param textOffsetX
	 *            int
	 * @param lineSpace
	 *            int
	 */
	public void setDialogStyle(int lineOffX, int lineSpace, int lineColor,
			int barColor) {
		dialogLineOffsetX = lineOffX;
		dialogLineSpace = lineSpace;
		dialogLineColor = lineColor;
		dialogBarColor = barColor;
	}

	/**
	 * 返回用户的选择项索引，如果列表项为空或者用户没有选择直接返回的则返回-1
	 * 
	 * @return int
	 */
	public int getListIndex() {
		return listIndex;
	}

	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}

	public void Init() {
		// TODO Auto-generated method stub
		l9Str = new L9Str();
	}

	public void Update() {
		// TODO Auto-generated method stub
		if (lib9.isKeyPressed(Lib9.K_KEY_UP | Lib9.K_KEY_NUM2)) {
			listIndex--;
			if (listIndex < 0) {
				listIndex = 0;
			}
		}
		if (lib9.isKeyPressed(Lib9.K_KEY_DOWN | Lib9.K_KEY_NUM8)) {
			listIndex++;
			if (listIndex > listString.length - 1) {
				listIndex = listString.length - 1;
			}
		}

		// 按5键返回上一个状态
		if (lib9.isKeyPressed(Lib9.K_KEY_NUM5 | Lib9.K_KEY_FIRE)) {
			lib9.changeState(lib9.popState());
		}
		if (lib9.isKeyPressed(Lib9.K_KEY_SOFT_R)) {
			listIndex = -1;
			lib9.changeState(lib9.popState());
		}
	}

	public void Paint() {
		// TODO Auto-generated method stub
		// 如果使用双缓冲，则用上一个状态的画面清屏，否则使用L9Config.dialogClearScreenColor的颜色清屏
		if (L9Config.bUseDoubleBuffer) {
			lib9.drawBufferImage();
		} else {
			lib9.fillScreen(L9Config.dialogClearScreenColor);
		}
		drawDialogBackground(dialogX, dialogY, dialogW, dialogH, listTitle);
		for (int i = 0; listString != null && i < listString.length; i++) {
			// 绘制列表框内容
			int YY = dialogY + K_Top_H + i * (K_FONT_H + dialogLineSpace) + 2;
			if (i == listIndex) { // 绘制当前项的Bar
				lib9.FG.setColor(dialogBarColor);
				lib9.FG.fillRect(dialogX + 1, YY - 1,  dialogW - 2, K_FONT_H + 2);
			}
			lib9.FG.setColor(dialogLineColor);
			l9Str.drawLine(lib9.FG, listString[i], dialogX
					+ dialogLineOffsetX, YY, dialogW, l9Str.K_Line_Align_Left);
		}
	}

}
