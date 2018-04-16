package teddserv;

import java.net.*;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import static teddserv.DB.DB;
import static teddserv.DB.dodajDane;

/**
 * 
 */

public class Serwer implements Runnable, Stale
/**
 * Implementujemy interfejsy: Runnable (do tworzenia wštków) oraz Stale (tam sš przechowywane stale uzywane w programie)
 */
{
	
	ServerSocket ss;
	// Socket serwerowy
	Socket socket;
	// Socket tworzony po polaczeniu klienta
	public ArrayList <Klient> userlist;
         List<String> nowi = new ArrayList<String>();
	// Lista podlaczonych klientow
	Thread thread;
	//glowny watek programu
	private DataOutputStream dataoutputstream;
	
	public Serwer()
	{
		try 
		{												
			ss = new ServerSocket(port);
			userlist = new ArrayList<Klient>();
			System.out.println(userlist.size());
			//tworzymy liste do obslugi uzytwkonikow
			}
		catch(IOException e) 
		{ 
			System.err.println("Nie mozna uruchomic serwera na porcie " +port);
		}



		thread = new Thread(this);
		thread.start();
		//startujemy glowny watek programu
	}			
	
	public void run()
	{
		while(thread != null)
		{
			try
			{
				SerwerWatek watek = new SerwerWatek(this,ss.accept());
				thread.sleep(THREAD_SLEEP_TIME);	
			}
			catch(Exception e) 	
			{ 
				e.printStackTrace();
			}
			
		}	
	}
	
	private void WyslijDoKlienta(Socket cs,String wiadomosc)
	{
		try 
			{
				dataoutputstream = new DataOutputStream(cs.getOutputStream());	
				dataoutputstream.write(new String(wiadomosc+"\r\n").getBytes());
			}
			catch(IOException e) 
			{ 
				System.err.println("Nie udalo sie wyslac wiadomosci do klienta");
			}
	}
            private void WyslijDoKlientaliste(Socket cs,String wiadomosc, ArrayList lista)
            {
                    try 
                            {
                                    dataoutputstream = new DataOutputStream(cs.getOutputStream());	
                                    dataoutputstream.write(new String(lista+"\r\n").getBytes());
                            }
                            catch(IOException e) 
                            { 
                                    System.err.println("Nie udalo sie wyslac wiadomosci do klienta");
                            }
            }

	private Klient ZnajdzUzytkownika(String nick)
	//metoda zwracajaca objekt klienta
	{
		for(int i=0;i<userlist.size();i++)
		{
			if(userlist.get(i).getUserName().equals(nick))
			{
				return userlist.get(i);
			}
				
		}
		return null;
	}
	
	private boolean Polaczony(String nick)
	//metoda sprawdzajaca czy dany klient jest podlaczony - czy istnieje na liscie userlist
	{		
		if(ZnajdzUzytkownika(nick) != null)
		{
			System.out.println("Uzytkownik o nicku " + nick + " jest polaczony");
			return true;
		}
		else
		{
			System.out.println("Nie ma nicka " + nick +",ok!");
			return false;
		}
	}
	

	private int ZwrocIndeks(String nick)
	//funkcja zwracajaca indeks uzytkownika na liscie userlist
	{
		for(int i = 0; i < userlist.size();i++)
		{
			if(userlist.get(i).getUserName().equals(nick))
			{
				return i;	
			}
		}
		return -1;
	}

	
	protected void DodajUzytkownika(Socket cs,String nick) throws IOException
	//dodawanie nowego uzytkownika do listy userlist
	{
			if(Polaczony(nick))
			//jezeli ktos juz jest z takim nickiem, wysylany jest komunikat do klienta
			{
				WyslijDoKlienta(cs,"EXIS ");
			}
			else
			{
                            
			userlist.add(new Klient(cs,nick));
                        nowi.add(nick);
			System.out.println("Nowy podlaczony, " + cs + " " + nick);
			String listString = "";

                       for (int i=0; i<userlist.size(); i++)
                        {
                           listString += userlist.get(i).getUserName()+"\t";
                           }
                        
                        for(int i=0;i<userlist.size();i++)
		       {
			   
                               WyslijDoKlienta(userlist.get(i).getSocket(),"LIST"+listString);
                                }

                  
                                           
///utworzenie nowego obiektu klienta i dodanie do userlist
			}
												
	}
	
	
	protected void UsunUzytkownika(String nick)
	//usuwanie klienta z listy userlist oraz wysylanie komunikatu do pozostalych klientow
	{
		//Klient removeKlient = ZnajdzUzytkownika(nick);
		if(ZwrocIndeks(nick) != -1)
		{
			userlist.remove(ZwrocIndeks(nick));	
                        System.out.println("Usunieto uzytkownika " + nick);
			userlist.trimToSize();
                        
                        String listString = "";

                        for (int i=0; i<userlist.size(); i++)
                        {
                           listString += userlist.get(i).getUserName()+"\t";
                           }
			for(int i=0; i<userlist.size();i++)
			{
                            
				WyslijDoKlienta(userlist.get(i).getSocket(),"REMO "+nick);
                            
                                WyslijDoKlienta(userlist.get(i).getSocket(),"LIST"+listString);
                                
                        }			
		}			
	}
	
	
	protected void WyslijMM(String tresc,String nick)
	{   
            dodajDane(nick,tresc);
			for(int i=0;i<userlist.size();i++)
			//wysylanie wiadomosci
			{
				System.out.println("S@194 " + userlist.size());
				
                                         
                                        System.out.println("poszla wiadomosc "+tresc);
					
					WyslijDoKlienta(userlist.get(i).getSocket(),"MESS "+nick+"    "+tresc);	
                                     
			}

	}
      
        
        protected static  void wystartuj_baze(){
        String baza1 = "Historia";
        Connection polaczenie = DB();
        
        
        }
	
	public static void main(String[] args) 
	{		
            wystartuj_baze();
		new Serwer();
	}
	
}