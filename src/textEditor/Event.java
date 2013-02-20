package textEditor;

import java.awt.event.*;
import javax.swing.*;

public class Event {
	private Timer timer;
	public Event(int delay, ActionListener a){
		System.out.print("new event\n");
		timer = new Timer(delay, a);
		timer.setRepeats(false);
		timer.start();
		System.out.print("timer started\n");
	}
	public void StopTimer(){
		timer.stop();
	}
	public void RestartTimer(){
		timer.restart();
	}
}
