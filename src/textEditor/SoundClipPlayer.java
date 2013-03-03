package textEditor;

import java.io.*;

import javax.sound.sampled.*;

public class SoundClipPlayer {
	//functional class that is essentially just testing how
	//playing mp3's works in java. Could eventually end up co-opted into other
	//classes method depending on what the events I finally end up with are like
	public SoundClipPlayer(String filepath){
		//snippet of code found on Stack overflow to play audio
		//not using java7 means I'm limited to wav, as I can't use Media, but that shouldn't be too big of a limitation
		 try{
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    }catch(Exception e){
		        System.out.println("Error with playing sound.");
		        e.printStackTrace();
		    }
	}
}
