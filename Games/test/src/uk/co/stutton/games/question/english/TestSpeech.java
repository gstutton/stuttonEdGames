package uk.co.stutton.games.question.english;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class TestSpeech {

	public static void main(String[] args){
	    VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        String voiceName = voices[2].getName();
	    Voice helloVoice = voiceManager.getVoice(voiceName);

	    if (helloVoice == null) {
	        System.err.println(
	            "Cannot find a voice named "
	            + voiceName + ".  Please specify a different voice.");
	        System.exit(1);
	    }
	    
	    /* Allocates the resources for the voice.
	     */
	    helloVoice.allocate();
	    
	    /* Synthesize speech.
	     */
	    helloVoice.speak("here here");
	
	    /* Clean up and leave.
	     */
	    helloVoice.deallocate();
	    System.exit(0);
	}
}
