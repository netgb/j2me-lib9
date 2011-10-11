package demo.lib9;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import lib9.j2me.*;

public class DemoActivity extends MIDlet {
    /** Called when the activity is first created. */
    static Lib9 lib9;
    public DemoActivity() {
  	lib9 =new DemoFirst(this);
//  	new DemoImageAndAnimationState(this);
//  	new DemoImageFontState(this);
//  	new DemoMapState(this);
//  	new DemoMultiState(this);
//  	new DemoHttpState(this);
//  	new DemoUseListState(this);
//  	new DemoChangeState(this);    	
    }

    public void startApp() {
        lib9.resumeApp();
    }

    public void pauseApp() {
        lib9.pauseApp();
    }

    public void destroyApp(boolean unconditional) {
        if (lib9 != null) {
            lib9.quitApp();
        }
    }
}