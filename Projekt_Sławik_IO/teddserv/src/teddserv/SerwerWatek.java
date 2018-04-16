package teddserv;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */

public class SerwerWatek implements Runnable, Stale
{
	Thread watek;
	public Socket socket;
	DataInputStream inputstream;
	String wiadomosc;
	Serwer rodzic;

	SerwerWatek(Serwer serwer,Socket cs)
	{				
		//System.out.println("SW@21 "+cs);
		this.rodzic = serwer;
		this.socket = cs;
		try 
		{	
			inputstream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));		
		}
		catch(IOException e) 
		{ 
			e.printStackTrace();
		}
		watek = new Thread(this);
		watek.start();	
	}
	
	public void run()
	{
		System.out.println(watek);
		while(watek != null)
		{
			try 
			{				
				wiadomosc = inputstream.readLine();	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(wiadomosc.startsWith("HELO"))
			{			
					String nick=wiadomosc.substring(5);
                            try {
                                rodzic.DodajUzytkownika(socket,nick);
                                /*
                                for(int i=0;i<rodzic.userlist.size();i++)
                                {
                                System.out.println("SW@56 "+rodzic.userlist.get(i).getUserName() + " " + rodzic.userlist.get(i).getSocket());
                                }
                                */
                            } catch (IOException ex) {
                                Logger.getLogger(SerwerWatek.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}

			if(wiadomosc.startsWith("QUIT"))
			{
				rodzic.UsunUzytkownika(wiadomosc.substring(5));
				QuitConnection();	
			}


			if(wiadomosc.startsWith("MESS"))
			{
				try
				{
					rodzic.WyslijMM(wiadomosc.substring(wiadomosc.indexOf(":")),wiadomosc.substring(5,wiadomosc.indexOf(":")));	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}		

			}
	}
	
	private void QuitConnection()
	{
		watek.stop();
		watek = null;		
		try 
		{
			socket.close();
		}
		catch(IOException e) 
		{ 
			System.err.println("Nie udalo sie zamknac socketu");
		}
		socket = null;	
	}	
}