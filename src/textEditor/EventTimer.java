package textEditor;

import java.awt.event.*;
import javax.swing.*;

public class EventTimer {
	private Timer _timer;
	//Takes in an event and delay, and fires that event after given delat
	public EventTimer(int delay, ActionListener a){
		_timer = new Timer(delay, a);
		_timer.setRepeats(false);
		_timer.start();
	}
	public void StopTimer(){
		_timer.stop();
	}
	public void RestartTimer(){
		_timer.restart();
	}
}
