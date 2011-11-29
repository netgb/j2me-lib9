package lib9.j2me;

import javax.microedition.midlet.MIDlet;

public class GameRabbit extends Lib9 implements L9IState {
	// 文字资源,由L9Eidtor工具导出的文本字符串常量,在制作多语言版本非常方便
	static int K_TEXT_GAME_RULE = 0;
	static int K_TEXT_EXIT_GAME = 1;
	static int K_TEXT_HIGH_SCORE = 2;
	static int K_TEXT_OVER_LAST_HIGH_SCORE = 3;
	static int K_TEXT_GAME_OVER = 4;
	static int K_TEXT_GAME_OVER2 = 5;
	static int K_TEXT_DIALOG_TITLE = 6;
	static int K_TEXT_BUTTON_PAUSE = 7;
	static int K_TEXT_BUTTON_BACK = 8;
	static int K_TEXT_BUTTON_COMFIRE = 9;
	static int K_TEXT_PAUSE_TIP = 10;
	static int K_TEXT_GAME_TOP_TITLE = 11;

	// L9Editor导出的帧、动画、地图等资源常量
	static int K_MODULE_小熊 = 0;
	static int K_MODULE_兔子 = 1;
	static int K_MODULE_锤子 = 2;
	static int K_MODULE_锤子1 = 3;
	static int K_FRAME_BEAR = 0;
	static int K_FRAME_RABBIT = 1;
	static int K_FRAME_BACK = 4;
	static int K_FRAME_PAUSE = 5;
	static int K_ANIM_HAMMER = 0;

	// //////////////////////////////////////兔子、熊和锤子出现的坐标,参照树桩的坐标///////////////////////
	static int K_Minigame_Rabbit_Rabbit_X_Off = 12; // 相对于树桩的偏移量
	static int K_Minigame_Rabbit_Rabbit_Y_Off = -38;
	static int K_Minigame_Rabbit_Rabbit_X_Off2 = 0; // 锤子相对于兔子的偏移量
	static int K_Minigame_Rabbit_Rabbit_Y_Off2 = 0;

	public int getRabbitX() {
		int XX = spriteRabbit_Map.getModuleX(Minigame_Rabbit_Index);
		return XX + K_Minigame_Rabbit_Rabbit_X_Off;
	}

	public int getRabbitY() {
		int YY = spriteRabbit_Map.getModuleY(Minigame_Rabbit_Index);
		return YY + K_Minigame_Rabbit_Rabbit_Y_Off;
	}

	public int getHammerX() {
		return getRabbitX() + K_Minigame_Rabbit_Rabbit_X_Off2;
	}

	public int getHammerY() {
		return getRabbitY() + K_Minigame_Rabbit_Rabbit_Y_Off2;
	}

	// 游戏资源对象
	public L9Map spriteRabbit_Map;
	public L9Animation spriteHammer;
	public L9Sprite spriteRabbit;
	public L9ResStr pL9ResStr;

	final int K_Default_FPS = 15;

	public GameRabbitLogo pGameLogo;

	public GameRabbit(MIDlet app) {
		super(app);
		// TODO Auto-generated constructor stub

		// L9Sprite spritetest=new L9Sprite("/rabbitMap");
		spriteRabbit = new L9Sprite("/rabbit");
		spriteHammer = new L9Animation(spriteRabbit, 0);

		spriteRabbit_Map = new L9Map("/rabbitMap", SCR_W, SCR_H);

		// 创建字符串资源对象,使用XXXX为Token,","为分割符号
		pL9ResStr = new L9ResStr("/text", "gb2312");
		pL9ResStr.setTokenAndSep("XXXX", ",");

		Rms_Config_Read();
		
		pGameLogo = new GameRabbitLogo(this);

		// 先进入Logo
		pushState(this);
		changeState(pGameLogo);
	}

	public long Minigame_Time;
	public boolean Minigame_IsOver;
	public int Minigame_Score;
	public boolean Minigame_bEnter;
	public int Minigame_XiongFail_Count;
	public boolean bDontRestartMinigame = false;

	public void exitGame() {
		bDontRestartMinigame = true;
		String strTitle = pL9ResStr.getResStr(K_TEXT_DIALOG_TITLE);
		String strMsg = pL9ResStr.getResStr(K_TEXT_EXIT_GAME);
		String strYes = pL9ResStr.getResStr(K_TEXT_BUTTON_COMFIRE);
		String strNo = pL9ResStr.getResStr(K_TEXT_BUTTON_BACK);
		showYesNoDialog(strTitle, strMsg, strYes, strNo, 180);
	}

	public long K_Minigame_Game_Time = 5 * 1000;// 2 * 60 * 1000;

	// public String[][] Minigame_HigeScore_Array;
	public void updateGame() {
		Minigame_Time += getTicks();

		if (Minigame_Time > K_Minigame_Game_Time) {
			Minigame_IsOver = true;
		}
		if (Minigame_XiongFail_Count > 5) {
			Minigame_IsOver = true;
		}

		if (Minigame_IsOver) {

			String sLine = "";
			if (Minigame_XiongFail_Count > 5) {
				sLine = pL9ResStr.getResStr(K_TEXT_GAME_OVER);
			} else {
				if (Last_Time == 0) { // 第一次
					sLine = pL9ResStr.getResStr(K_TEXT_HIGH_SCORE, ""
							+ Minigame_Score + ","
							+ ((int) (Minigame_Time / 1000)) + ","
							+ Last_Scores + "," + Last_Time);
				} else {
					// 只要大于上次的记录就应该存储
					double last = ((double) Last_Scores) / ((double) Last_Time);
					double rs = ((double) Minigame_Score)
							/ ((double) Minigame_Time);
					if (rs > last) {
						sLine = pL9ResStr.getResStr(
								K_TEXT_OVER_LAST_HIGH_SCORE, ""
										+ Minigame_Score + ","
										+ ((int) (Minigame_Time / 1000)) + ","
										+ Last_Scores + "," + Last_Time);
					} else {
						sLine = pL9ResStr.getResStr(K_TEXT_GAME_OVER, ""
								+ Minigame_Score + ","
								+ ((int) (Minigame_Time / 1000)) + ","
								+ Last_Scores + "," + Last_Time);
					}
				}
			}

			bDontRestartMinigame=false;
			
			Minigame_Save();
			
			showMsgDialog(pL9ResStr.getResStr(K_TEXT_DIALOG_TITLE), sLine,
					pL9ResStr.getResStr(K_TEXT_BUTTON_COMFIRE), 180);
		}
	}

	public void drawTimeAndScore() {
		// 将毫秒时间转换为 小时：分：秒 这种形式
		long leftTime = K_Minigame_Game_Time - Minigame_Time;
		long hour = leftTime / 3600000;
		long minute = (leftTime - hour * 3600000) / 60000;
		long second = (leftTime - hour * 3600000 - minute * 60000) / 1000;

		String sTime = hour + ":" + minute + ":" + second;

		String Line = pL9ResStr.getResStr(K_TEXT_GAME_TOP_TITLE, ""
				+ Minigame_Score + "," + sTime);

		pL9Str.drawLine(pFG, Line, 0, 4, SCR_W, L9Str.K_Line_Align_Center);
	}

	final static int K_Minigame_Rabbit_Type_Xiong = 0;
	final static int K_Minigame_Rabbit_Type_Rabbit = 1;

	public int Minigame_Rabbit_Index = -1;
	public int Minigame_Rabbit_Type = 0;
	public int Minigame_Rabbit_Press_Index = -1;
	public int Minigame_Rabbit_Speed;
	public int Minigame_Rabbit_Begin_Frame;

	public void Init() {
		// TODO Auto-generated method stub
		if (!bDontRestartMinigame) {
			Minigame_Time=0;
			Minigame_IsOver = false;
			Minigame_Score = 0;
			Minigame_XiongFail_Count = 0;
			// K_Minigame_Game_Time = 2 * 60 * 1000; // 小游戏玩耍时间为2分钟
		}
		bDontRestartMinigame = false;

		setSoftKey(pL9ResStr.getResStr(K_TEXT_BUTTON_PAUSE),
				pL9ResStr.getResStr(K_TEXT_BUTTON_BACK));

		Minigame_Rabbit_Begin_Frame = frameCount;
	}

	public void Update() {
		// TODO Auto-generated method stub
		Minigame_Rabbit_Press_Index = -1;

		if (!spriteHammer.isPlayOver()) {
			return;
		}

		// 控制兔子或者熊出现的速度
		Minigame_Rabbit_Speed = Minigame_Score / 10;
		if (Minigame_Rabbit_Speed >= K_Default_FPS) {
			Minigame_Rabbit_Speed = K_Default_FPS - 1;
		}

		if ((frameCount - Minigame_Rabbit_Begin_Frame)
				% (K_Default_FPS - Minigame_Rabbit_Speed) == 0) {
			Minigame_Rabbit_Index = L9Util.getDiffRandValue(
					Minigame_Rabbit_Index, 0, 100) % 9;
			Minigame_Rabbit_Type = L9Util.getRandValue(0, 100) % 2;
		}

		if (Minigame_Rabbit_Index == -1) {
			return;
		}

		if (isLeftSoft(getPointerX(), getPointerY())) {
			pauseGame();
		} else if (isRightSoft(getPointerX(), getPointerY())) {
			exitGame();
		} else if (isMiddleSoft(getPointerX(), getPointerY())) {
			gameTip();
		} else {
			if (getPointerX() > 0) {
				int XX = getRabbitX();
				int YY = getRabbitY();

				int w = spriteRabbit.getFrameWidth(Minigame_Rabbit_Type);
				int h = spriteRabbit.getFrameHeight(Minigame_Rabbit_Type);

				if (isInRect(getPointerX(), getPointerY(), new L9Rect(XX, XX
						+ w, YY, YY + h))) {
					Minigame_Rabbit_Press_Index = Minigame_Rabbit_Index;
				}

			}
		}

		updateGame();

		if (isKeyPressed(K_KEY_SOFT_L)) {
			pauseGame();
		}

		if (isKeyPressed(K_KEY_SOFT_R)) {
			exitGame();
		}

		if (isKeyPressed(KEY_NUM1)) {
			Minigame_Rabbit_Press_Index = 0;
		} else if (isKeyPressed(KEY_NUM2)) {
			Minigame_Rabbit_Press_Index = 1;
		} else if (isKeyPressed(KEY_NUM3)) {
			Minigame_Rabbit_Press_Index = 2;
		} else if (isKeyPressed(KEY_NUM4)) {
			Minigame_Rabbit_Press_Index = 3;
		} else if (isKeyPressed(KEY_NUM5)) {
			Minigame_Rabbit_Press_Index = 4;
		} else if (isKeyPressed(KEY_NUM6)) {
			Minigame_Rabbit_Press_Index = 5;
		} else if (isKeyPressed(KEY_NUM7)) {
			Minigame_Rabbit_Press_Index = 6;
		} else if (isKeyPressed(KEY_NUM8)) {
			Minigame_Rabbit_Press_Index = 7;
		} else if (isKeyPressed(KEY_NUM9)) {
			Minigame_Rabbit_Press_Index = 8;
		}

		if (Minigame_Rabbit_Press_Index == Minigame_Rabbit_Index) {
			spriteHammer.rePlay(getHammerX(), getHammerY());
			// System.out.println("动画");

			if (Minigame_Rabbit_Type == K_Minigame_Rabbit_Type_Rabbit) {
				Minigame_Score++;
			} else {
				Minigame_Score--;
				if (Minigame_Score < 0) {
					Minigame_Score = 0;
				}

				Minigame_XiongFail_Count++; // 记录打到熊的次数
			}
			Minigame_Rabbit_Index = -1;
		}
	}

	public void Paint() {
		// TODO Auto-generated method stub
		if (spriteRabbit_Map == null) {
			return;
		}
		// 绘制背景和树桩
		spriteRabbit_Map.updateMap(pFG);
		spriteRabbit_Map.updateMapObjects(pFG);

		// 绘制得分和时间
		drawTimeAndScore();

		// 绘制兔子或者熊和锤子
		if (Minigame_Rabbit_Index != -1) {
			spriteRabbit.paintFrame(pFG, Minigame_Rabbit_Type, getRabbitX(),
					getRabbitY());
		}
		spriteHammer.updateAnimation(pFG);

		// 绘制左右软件
		drawSoftKey(Str_Left_Soft, Str_Right_Soft);
	}

	public void pauseGame() {
		bDontRestartMinigame = true;
		showMsgDialog(pL9ResStr.getResStr(K_TEXT_DIALOG_TITLE),
				pL9ResStr.getResStr(K_TEXT_PAUSE_TIP),
				pL9ResStr.getResStr(K_TEXT_BUTTON_COMFIRE), 180);
	}

	public void gameTip() {
		bDontRestartMinigame = true;
		String sMsg = pL9ResStr.getResStr(K_TEXT_HIGH_SCORE, "" + Last_Scores
				+ "," + Last_Time);

		showMsgDialog(pL9ResStr.getResStr(K_TEXT_DIALOG_TITLE), sMsg,
				pL9ResStr.getResStr(K_TEXT_BUTTON_COMFIRE), 180);
	}

	// ////////////////////////////////////////绘制左右软键//////////////////////////////////////////////////
	final static int K_Soft_Key_H = 24;
	public int K_Soft_Key_Text_Y;

	private String Str_Left_Soft;
	private String Str_Right_Soft;

	public void setSoftKey(String sLeft, String sRight) {
		Str_Left_Soft = sLeft;
		Str_Right_Soft = sRight;
	}

	public int getSoftKeyIcon(String Key) {
		int keyID = -1;
		if (Key.equals(pL9ResStr.getResStr(K_TEXT_BUTTON_BACK))) {
			keyID = K_FRAME_BACK;
		} else if (Key.equals(pL9ResStr.getResStr(K_TEXT_BUTTON_PAUSE))) {
			keyID = K_FRAME_PAUSE;
		}
		return keyID;
	}

	public void drawSoftKey(String left, String right) {
		if (left != null) {
			int keyID = getSoftKeyIcon(left);
			if (keyID != -1) {
				spriteRabbit.paintFrame(pFG, keyID, 0,
						SCR_H - spriteRabbit.getFrameHeight(keyID));
			}
			// System.out.println("left="+left);
		}
		if (right != null) {
			int keyID = getSoftKeyIcon(right);
			if (keyID != -1) {
				spriteRabbit.paintFrame(pFG, keyID,
						SCR_W - spriteRabbit.getFrameWidth(keyID), SCR_H
								- spriteRabbit.getFrameHeight(keyID));
			}
			// System.out.println("right="+right);
		}
	}

	public boolean isLeftSoft(int pX, int pY) {
		if (pX > 0 && pX < 54 && pY > SCR_H - K_Soft_Key_H && pY < SCR_H) {
			return true;
		}
		return false;
	}

	public boolean isRightSoft(int pX, int pY) {
		if (pX > SCR_W - 54 && pX < SCR_W && pY > SCR_H - K_Soft_Key_H
				&& pY < SCR_H) {
			return true;
		}
		return false;
	}

	public boolean isMiddleSoft(int pX, int pY) {
		if (pX > 54 && pX < SCR_W - 54 && pY > SCR_H - K_Soft_Key_H
				&& pY < SCR_H) {
			return true;
		}
		return false;
	}

	// ///////////////////////////////记录数据/////////////////////////////////////////////
	public int Last_Scores;
	public int Last_Time;
	final public String K_Rabbit_DB = "_rms_config";

	public void Rms_Config_Read() {
		L9Store db = new L9Store(K_Rabbit_DB, L9Store.K_Store_Mode_Read);
		// 第一次读取可能由于没有保存记录将会读取失败，所以这次进行了异常处理
		try {
			Last_Scores = db.readInt();
			Last_Time = db.readInt();
		} catch (Exception ex) {
		}
	}

	public void Rms_Config_Write() {
		L9Store db = new L9Store(K_Rabbit_DB, L9Store.K_Store_Mode_Write);
		db.writeInt(Last_Scores);
		db.writeInt(Last_Time);
		db.Save();
	}

	public void Minigame_Save() {
		if (Minigame_Time > 0) {
			if (Last_Time == 0) { // 第一次
				Last_Scores = Minigame_Score;
				Last_Time = (int) (Minigame_Time / 1000);
				Rms_Config_Write();
			} else {
				// 只要大于上次的记录就应该存储
				double last = ((double) Last_Scores) / ((double) Last_Time);
				double rs = ((double) Minigame_Score)
						/ ((double) Minigame_Time);
				if (rs > last) {
					Last_Scores = Minigame_Score;
					Last_Time = (int) (Minigame_Time / 1000);
					Rms_Config_Write();
				}
			}
		}
	}
}
