package BLL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * 
 * @author Christopher, Mark, Martin & Rasmus
 */
public class AudioPlayer {


    private Player p;
    private Thread w;
    private boolean isPlayingNow = false;

    private boolean m_errorState; // true means everything is ok
    private String m_errorMessage;

    public AudioPlayer(String fileName)
    {
        m_errorState = true;
        m_errorMessage = "OK";
  
        
            try
            {
                p = new Player(new FileInputStream(fileName));
            } catch (FileNotFoundException ex)
            {
                m_errorState = false;
                m_errorMessage = ex.getMessage();
            } catch (JavaLayerException ex) {
                m_errorState = false;
                m_errorMessage = ex.getMessage();
        }
             
    }

    /**
     * 
     * @void start to play
     */
    public void startToPlay()
    {   
        w = new PlayerThread();
        w.start(); 
        isPlayingNow = true;
    }

     /**
     * 
     * @void pause the song
     */
    public void pause()
    {
        w.suspend();
        isPlayingNow = false;
    }
    
     /**
     * 
     * @void resume the song
     */
    public void resume()
    {
        w.resume();
        isPlayingNow = true;
    }
    
     /**
     * 
     * @void stop the song
     */
    public void stop()
    { 
        w.stop();
        isPlayingNow = false;
    }


    public boolean isValid() 
    { return m_errorState; }
    
    public String getErrorMessage() 
    { return m_errorMessage; }
    
    public boolean isPlaying()
    { return isPlayingNow; }

    private class PlayerThread extends Thread
    {
        
        @Override
        public void run()
        {
            try
            {
                p.play();
                
            } catch (JavaLayerException ex)
            {
                m_errorState = false;
                m_errorMessage = ex.getMessage();
            }
        }
        
    }
}
