package audio_player;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio_play {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		File mainfile=new File("Sounds");
//		main folder which contain the song 
		
		File afile[]=mainfile.listFiles();
//		array of file which contain all the song of *mainfile*
		
		int count=0;
//		it will contain the count of song in fonder
		
		for(int i=1;i<=afile.length;i++) {
//			System.out.println(i+" "+afile[i-1].getName());
			System.out.printf("%-3d%s\n",i,afile[i-1].getName());
//			if(i%2==0) {
//				System.out.println();
//				}
			count++;
		}
//		print all the song name in the file
		
		
		
		int c=0;
//		int for choosing the song
		System.out.println();
		System.out.println("**********************For end program press -1*********************");
		System.out.println();
		while(c!=-1) {
			System.out.print("Choose the song Enter no. :");
			
			c=sc.nextInt();
			
			if(c==-1){
				System.out.println("\n");
				System.out.println("------------+-------+------+------+-----Program End------+-------+-------+--------+---------");
				break;
				
			}
			else if(c==0||c>count){
				System.out.println("Doesnot Exist");
			}
			
			else if(c<=count) {
				ftoplay(c,afile[c-1]);
//				ftoplay(int , file) it is mathod to play the file 
			}
			
		}
		
		sc.close();	
		
		
		
	}
	public static void ftoplay(int fileno,File file) {
//		File file=new File("D:\\Bum Bum Tam Tam.wav");
		Scanner sc=new Scanner(System.in);
		
		try {
			AudioInputStream audiostreame = AudioSystem.getAudioInputStream(file);
//			this will create a audiostreme betn program and the file
			
/*			audiosystem The AudioSystem class acts as the entry point to the sampled-audiosystem resources.
 * 			 This class lets you query and access the mixers that are installed on the system.
 *   		AudioSystem includes a number of methods for converting audio data between different formats,
 *    		and for translating between audio files and streams. 
 *    		It also provides a method for obtaining a Line directly from the AudioSystem 
 *    		without dealing explicitly with mixers.
 *     		Properties can be used to specify the default mixer for specific line types.
 *     		Both system properties and a properties file are considered. The"sound.properties" properties
 *      	file is read from an implementation-specificlocation 
 *      	(typically it is the conf directory in the Java installation directory). 
 *      	The optional "javax.sound.config.file" system property can be used to specify 
 *      	the properties file that will be read as the initial configuration. 
 *      	If a property exists both as a system property and in the properties file, 
 *     		the system property takes precedence. 
 *      	If none is specified,a suitable default is chosen among the available devices.
 *          The syntax of the properties file is specified in Properties.load. The following table lists 
 *          the available property keys and which methods consider them: 
            Audio System Property Keys*/

			Clip clip= AudioSystem.getClip();
//			we made a clip 
			
/*			clip
 * 			The Clip interface represents a special kind of data line whose audio data can be 
 * 			loaded prior to play back, instead of being streamed in real time. 

 *			Because the data is pre-loaded and has a known length, 
 *			you can set a clip to start playing at any position in its audio data. 
 *			You can also create a loop,so that when the clip is played it will cycle repeatedly.
 *			Loops are specified with a starting and ending sample frame, 
 *			along with the number of times that the loop should be played. 
*/
 
			clip.open(audiostreame);
			
			String c="";
			System.out.println();
			System.out.println("------------p:play-------c:choose_song------r:reset--------s:pouse-------t:playfrom(1--100)");
			System.out.println();
//			System.out.println("0.............."+(clip.getMicrosecondLength()/1000000)/60+":"+(clip.getMicrosecondLength()/1000000)%60);
			while(!c.equals("c")) {
			    System.out.print(fileno+" : ");
				c=sc.nextLine();
				if(c.equals("t")) {
					System.out.print("t : ");
					Double t=sc.nextDouble();
				    sc.nextLine();
				    
				    if(t>=0||t<=100) { 
				    	clip.setMicrosecondPosition((long)(((double)t/100)*clip.getMicrosecondLength())); 
				    	System.out.println("     from ["+(clip.getMicrosecondPosition()/1000000)/60+" : "+(clip.getMicrosecondPosition()/1000000)%60+"]");
				    	clip.start();
				    } 
				}
				else {
				switch(c) {
						case "p" : clip.start();
						break;
					
						case "c" : clip.close(); break;
	
					
						case "r" : clip.setMicrosecondPosition(0); clip.start();
						break;
					
						case "s" : clip.stop();
						break;
					
						default : System.out.println("Wrong Keyword"); //c="c";clip.close();
						break;
					
					}
				}
			}
			
			clip.close();
//			sc.close();
			
//			System.out.println("Shut Down");
			
		}
		catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			System.out.println("Unsupported Audio File");
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
