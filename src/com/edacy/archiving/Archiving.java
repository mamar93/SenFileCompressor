package com.edacy.archiving;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Archiving {

	  private File repertoire;
	  public Archiving(File repertoire)
	  {
		  this.repertoire = repertoire;
	  }
	  //archivage vers le repertoire par defaut(courant)
	  public void start() throws IOException
	  {
		    long start = System.currentTimeMillis();
		    DataOutputStream writer = null; //ecriture des types primitifs
		    DataInputStream reader = null;//lecture des types primitifs 
		    int numberOfFiles = 0;
		    byte[] buf; //buffer
		    String cheminAbsolu = repertoire.getAbsolutePath();
		    String nomRep = repertoire.getName();
		    String finalPath = cheminAbsolu.substring(0,cheminAbsolu.lastIndexOf("\\")+1)+nomRep+".sfc";
		    File finalFile = new File(finalPath);
		    if(!finalFile.exists())
		        finalFile.createNewFile();
		      //lecture et ecriture tamporisee pour plus de performances
		      writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(finalFile)));
		      File[] fileToArchive = repertoire.listFiles(); 
		      for(int i = 0 ; i < fileToArchive.length ; i++)
		      {
		        if(fileToArchive[i].isFile())
		         numberOfFiles++;
		      }
		      writer.writeInt(numberOfFiles); // ecriture du nombre de fichiers a archiver dans le fichier de sortie
		      System.out.println("Nombre de fichiers a archiver : "+numberOfFiles);
		      for(int i = 0 ; i < fileToArchive.length ; i++)
		      {
		    	File f = fileToArchive[i];
		        if(f.isFile())
		        {
		          //L'ecriture se deroule ainsi : nomDuFichier + tailleDuFichier + contenuDuFichier
		          System.out.print("Archiving "+f.getName()+"...");
		          String name = f.getName(); 
		          long length = f.length(); 
		          writer.writeUTF(name);
		          writer.writeLong(length);
		          reader = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
		          buf = new byte[1024]; 
		          int cpt;
		          //Ecriture du contenu de chaque fichier par ko
		          while((cpt = reader.read(buf, 0, buf.length)) != -1 )
		          {
		            writer.write(buf, 0, cpt);
		          }
		          reader.close();
		          System.out.println((double)(((int)(i+1) * 100)/numberOfFiles)+"% OK !");
		        }  
		      }
		      if(writer != null)
		      {
		          writer.flush();
		          writer.close();
		      }
		      if(reader != null)
		      {
		          reader.close();
		      }
		      System.out.println("Temps d'execution : "+(System.currentTimeMillis() - start)+" secondes");
	  }
	  //archivage vers un repertoire cible
	  public void start(String cheminCible) throws IOException
	  {
		    long start = System.currentTimeMillis();
		    DataOutputStream writer = null; //ecriture des types primitifs
		    DataInputStream reader = null;//lecture des types primitifs 
		    int numberOfFiles = 0;
		    byte[] buf; //buffer
		    File cible = new File(cheminCible);
		    String cheminAbsolu = cible.getAbsolutePath();
		    String nomRep = cible.getName();
		    String finalPath = cheminAbsolu.substring(0,cheminAbsolu.lastIndexOf("\\")+1)+nomRep+".sfc";
		    System.out.println(finalPath);
		    File finalFile = new File(finalPath);
		    if(!finalFile.exists())
		        finalFile.createNewFile();
		      //lecture et ecriture tamporisee pour plus de performances
		      writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(finalFile)));
		      File[] fileToArchive = repertoire.listFiles(); 
		      for(int i = 0 ; i < fileToArchive.length ; i++)
		      {
		        if(fileToArchive[i].isFile())
		         numberOfFiles++;
		      }
		      writer.writeInt(numberOfFiles); // ecriture du nombre de fichiers a archiver dans le fichier de sortie
		      System.out.println("Nombre de fichiers a archiver : "+numberOfFiles);
		      for(int i = 0 ; i < fileToArchive.length ; i++)
		      {
		    	File f = fileToArchive[i];
		        if(f.isFile())
		        {
		          //L'ecriture se deroule ainsi : nomDuFichier + tailleDuFichier + contenuDuFichier
		          System.out.print("Archiving "+f.getName()+"...");
		          String name = f.getName(); 
		          long length = f.length(); 
		          writer.writeUTF(name);
		          writer.writeLong(length);
		          reader = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
		          buf = new byte[1024]; 
		          int cpt;
		          //Ecriture du contenu de chaque fichier par ko
		          while((cpt = reader.read(buf, 0, buf.length)) != -1 )
		          {
		            writer.write(buf, 0, cpt);
		          }
		          reader.close();
		          System.out.println((double)(((int)(i+1) * 100)/numberOfFiles)+"% OK !");
		        }  
		      }
		      if(writer != null)
		      {
		          writer.flush();
		          writer.close();
		      }
		      if(reader != null)
		      {
		          reader.close();
		      }
		      System.out.println("Temps d'execution : "+(System.currentTimeMillis() - start)+" secondes");
	  }
	  
}