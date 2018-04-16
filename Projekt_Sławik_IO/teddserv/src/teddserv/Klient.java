package teddserv;

import java.net.*;
/**
 *
 */
public class Klient
{
	private Socket socket;
	private String nickname;
	
	Klient(Socket socket,String nick)
	{
		this.socket = socket;
		this.nickname = nick;
	}
		
	protected Socket getSocket()
	{
		System.out.println("K@23 " + this.socket);
		return this.socket;
	}
	protected String getUserName()
	{
		return this.nickname;
	}
	
	

}