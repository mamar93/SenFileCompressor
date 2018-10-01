package com.edacy.archiving;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UnPackager {
     private File entree;
     public UnPackager(File entree)
     {
    	 this.entree = entree;
     }
     public void start() throws IOException
     {
    	long start = System.currentTimeMillis();
    	String cheminS = entree.getAbsolutePath().substring(0,entree.getAbsolutePath().lastIndexOf(".sfc"));
    	DataInputStream dis;
    	DataOutputStream dos;
    	File s = new File(cheminS);
    	//Si le repertoire de sortie n'existe pas , on le cree
    	if(!s.exists())
 		{
 			s.mkdir();
 			
 		}
 	    dis = new DataInputStream(new BufferedInputStream(new FileInputStream(entree)));
 	    int nombre_de_fichiers = dis.readInt();
		System.out.println("L'archive contient "+  nombre_de_fichiers + " fichiers ");
		for(int i = 0 ; i < nombre_de_fichiers ; i++)
		{
			String nom = dis.readUTF();
			long bytes = dis.readLong();
			System.out.print("Desarchivage de  : " + nom +" - " + bytes/1024 + " kb ...");
			String chemin_fichier = s.getAbsolutePath()+"\\"+nom;
			File fileExtract = new File(chemin_fichier);
			if(!fileExtract.exists())
			{
				fileExtract.createNewFile();
			}
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileExtract)));
			final byte[] buf = new byte[(int)bytes];
			int cpt;
			cpt = dis.read(buf,0,(int)bytes);
			dos.write(buf, 0, cpt);
			
			dos.flush();
			dos.close();
			System.out.println(" Ok");
		}
		dis.close();
		System.out.println("Temps d'execution : "+(System.currentTimeMillis() - start)+" secondes");
     }
     public void start(String cheminCible) throws IOException
     {
    	long start = System.currentTimeMillis();
    	String cheminS = cheminCible;
    	DataInputStream dis;
    	DataOutputStream dos;
    	File s = new File(cheminS);
    	//Si le repertoire de sortie n'existe pas , on le cree
    	if(!s.exists())
 		{
 			s.mkdir();
 			
 		}
 	    dis = new DataInputStream(new BufferedInputStream(new FileInputStream(entree)));
 	    int nombre_de_fichiers = dis.readInt();
		System.out.println("L'archive contient "+  nombre_de_fichiers + " fichiers ");
		for(int i = 0 ; i < nombre_de_fichiers ; i++)
		{
			String nom = dis.readUTF();
			long bytes = dis.readLong();
			System.out.print("Desarchivage de  : " + nom +" - " + bytes/1024 + " kb ...");
			String chemin_fichier = s.getAbsolutePath()+"\\"+nom;
			File fileExtract = new File(chemin_fichier);
			if(!fileExtract.exists())
			{
				fileExtract.createNewFile();
			}
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileExtract)));
			final byte[] buf = new byte[(int)bytes];
			int cpt;
			cpt = dis.read(buf,0,(int)bytes);
			dos.write(buf, 0, cpt);
			
			dos.flush();
			dos.close();
			System.out.println(" Ok");
		}
		dis.close();
		System.out.println("Temps d'execution : "+(System.currentTimeMillis() - start)+" secondes");
     }
     
}
