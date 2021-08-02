package com.niit.daoImpl;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlaymusicDAOImpl
{
    AudioInputStream audioInputStream;
    Long currentFrame;
    Clip clip;
    String status;
    String filePath;
    Scanner sc = new Scanner(System.in);

    public PlaymusicDAOImpl(String  location) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.filePath=location;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void MusicPlay()
    {
        try
        {
            int choice;
            do
            {
                System.out.println("1. Play\n2. Pause\n3. Resume\n4. Restart\n5. Stop");
                System.out.println("Enter your choice : ");
                choice = sc.nextInt();
                switch (choice)
                {
                    case 1:
                        clip.start();
                        status = "play";
                        System.out.println("Song started playing....!");
                        break;
                    case 2:
                            if (status.equals("paused"))
                            {
                                System.out.println("audio is already paused");
                                 //return;
                            }
                            else
                            {
                                this.currentFrame = this.clip.getMicrosecondPosition();
                                clip.stop();
                                status = "paused";
                                long duration =TimeUnit.MICROSECONDS.toMinutes(this.clip.getMicrosecondPosition());
                                System.out.println("Music is paused at "+this.clip.getMicrosecondPosition() + " Position ");
                            }

                        break;
                    case 3:
                            if (status.equals("play"))
                            {
                                System.out.println("Audio is already being played");
                               // return;
                            }
                            else
                            {
                                System.out.println("Music is Resumed in "+this.clip.getMicrosecondPosition()+ " Position");
                                clip.close();
                                audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                                clip.open(audioInputStream);
                                clip.setMicrosecondPosition(currentFrame);
                                clip.start();
                                status = "play";
                            }

                        break;
                    case 4:
                            clip.stop();
                            clip.close();
                            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                            clip.open(audioInputStream);
                            currentFrame = 0L;
                            clip.setMicrosecondPosition(0);
                            clip.start();
                            status = "play";
                        break;
                    default:
                            currentFrame = 0L;
                            clip.stop();
                            clip.close();
                        System.out.println(" Song playing Stopped.....!");
                        break;
                }

            }while (choice<=4);

        }
        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
