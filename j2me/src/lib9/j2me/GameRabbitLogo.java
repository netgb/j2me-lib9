package lib9.j2me;

public class GameRabbitLogo implements L9IState {

	private Lib9 pLib9;
	private long iLogoTime;
	private L9Sprite pLogoSprite;

	public GameRabbitLogo(Lib9 pLib9) {
		// TODO Auto-generated constructor stub
		this.pLib9 = pLib9;
		iLogoTime = 0;
		pLogoSprite = new L9Sprite("/logo");
	}

	public void Init() {
		// TODO Auto-generated method stub

	}

	public void Update() {
		// TODO Auto-generated method stub
		iLogoTime += pLib9.getTicks();
		if (iLogoTime > 3000) {
			pLib9.changeState(pLib9.popState());
		}
	}

	public void Paint() {
		// TODO Auto-generated method stub
		if (pLogoSprite != null) {//¾ÓÖÐ»æÖÆlogo
			int w = pLogoSprite.getFrameWidth(0);
			int h = pLogoSprite.getFrameHeight(0);
			pLogoSprite.paintFrame(pLib9.pFG, 0, (pLib9.SCR_W - w) >> 1,
					(pLib9.SCR_H - h) >> 1);
		}
	}

}
