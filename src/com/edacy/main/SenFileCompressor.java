package com.edacy.main;

import java.io.File;

import com.edacy.archiving.Archiving;
import com.edacy.archiving.UnPackager;
import com.edacy.compression.Compression;
import com.edacy.compression.Decompression;

public class SenFileCompressor {
    public static void help()
    {
    	   System.out.println("SenFileCompressor est un programme permettant d'archiver et de compresser des fichiers");
		   System.out.println("1.Archivage et compression\nSi vous voulez archiver et compresser des fichiers , c'est simple , faites : ");
		   System.out.println("java SenFileCompressor -c \"chemin absolu du dossier ou se trouvent les fichiers a compresser\"");
		   System.out.println("Apres la compression vous aurez dans le meme repertoire ou se trouve le dossier qui contient vos fichiers a compresser");
		   System.out.println("un fichier qui s'appelle \"leNomDuDossier.sfc\" , c'est le fichier archiver sans la compression et un");
		   System.out.println("autre fichier qui s'appelle \"leNomDuDossier_deflated.sfc\" qui est le fichier d'archive mais compresse cette fois-ci");
           System.out.println("2. Decompression\n Si vous voulez decompresser les fichiers , c'est tres simple aussi , faites : ");
           System.out.println("java SenFileCompressor -d \"chemin absolu du fichier nomFichier.sfc\"");
           System.out.println("Ainsi dans un repertoire qui s'appelle ayant le meme nom que le dossier de depart contenant vos fichiers , vous trouverez l'ensemble de vos fichiers");
    }
	public static void main(String[] args) throws Exception {
		    Archiving a = null;
		    Compression c = null;
		    Decompression d = null;
		    UnPackager u =null;
		   if(args.length >= 1)
		   {
			  if(args.length == 1)
			  {
			   help();
			  }
			  else if(args.length == 2)
			  {
				   if(args[0].equalsIgnoreCase("-c"))
				   {
					   a = new Archiving(new File(args[1]));
					   a.start();
					   c = new Compression(new File(args[1]+".sfc"));
					   c.deflate();
					   System.out.println("Fichier de sorti : "+args[1]+"_deflated.sfc");
				   }
				   else if(args[0].equalsIgnoreCase("-d"))
				   {
					   d = new Decompression(new File(args[1]));
					   d.inflate();
					   u = new UnPackager(new File(args[1].substring(0, args[1].indexOf("_deflated"))+".sfc"));
					   u.start();
					   System.out.println("Dossier de sorti : "+args[1].substring(0,args[1].indexOf("_deflated")));
					   
				   }
				   else help();
			  }
			  else if(args.length == 3)
				  help();
			  else if(args.length == 4)
			  {
				  if(args[0].equalsIgnoreCase("-c"))
				  {
					  if(args[2].equalsIgnoreCase("-r"))
					  {
						  String repCible = args[3];
						  File f = new File(repCible);
						  if(!f.exists())
							  System.out.println("Le repertoire cible n'existe pas !");
						  else
						  {
							   a = new Archiving(new File(args[1]));
							   a.start(args[3]);
							   c = new Compression(new File(args[3]+"\\"+args[3].substring(args[3].lastIndexOf("\\")+1, args[3].length())+".sfc"));
							   c.deflate();
							   System.out.println("Fichier de sorti : "+args[3]+"\\"+args[3].substring(args[3].lastIndexOf("\\")+1, args[3].length())+"_deflated.sfc");
						  }
					  }
					  else help();
				  }
				  else if(args[0].equalsIgnoreCase("-d"))
				  {
					  if(args[2].equalsIgnoreCase("-r"))
					  {
						  String repCible = args[3];
						  File f = new File(repCible);
						  if(!f.exists())
							  System.out.println("Le repertoire cible n'existe pas !");
						  else
						  {
							   d = new Decompression(new File(args[1]));
							   d.inflate();
							   u = new UnPackager(new File(args[1].substring(0, args[1].indexOf("_deflated"))+".sfc"));
							   u.start(args[3]);
							   System.out.println("Repertoire de sorti : "+args[3]);
						  }
					  }
					  else help();
				  }
			  }
			  else if(args.length == 5)
			  {
				  if(args[0].equalsIgnoreCase("-c"))
				  {
					  if(args[2].equalsIgnoreCase("-r"))
					  {
						  String repCible = args[3];
						  File f = new File(repCible);
						  if(!f.exists())
						  {
							  
							  if(args[4].equalsIgnoreCase("-f"))
							  {
								  a = new Archiving(new File(args[1]));
							      a.start(args[3]);
							      c = new Compression(new File(args[3]+"\\"+args[3].substring(args[3].lastIndexOf("\\")+1, args[3].length())+".sfc"));
							      c.deflate();
							      System.out.println("Fichier de sorti : "+args[3]+"\\"+args[3].substring(args[3].lastIndexOf("\\")+1, args[3].length())+"_deflated.sfc");
							     
							  }
							  else
							  {
								  System.out.println("Le repertoire cible n'existe pas !");
							  }
							}
							  
						  
					  }
					  else help();
				  }
				  else if(args[0].equalsIgnoreCase("-d"))
				  {
					  if(args[2].equalsIgnoreCase("-r"))
					  {
						  String repCible = args[3];
						  File f = new File(repCible);
						  if(!f.exists())
						  {
							  
							  if(args[4].equalsIgnoreCase("-f"))
							  {
								  d = new Decompression(new File(args[1]));
								   d.inflate();
								   u = new UnPackager(new File(args[1].substring(0, args[1].indexOf("_deflated"))+".sfc"));
								   u.start(args[3]);
								   System.out.println("Repertoire de sorti : "+args[3]);
							  }
							  else
							  {
								  System.out.println("Le repertoire cible n'existe pas !");
							  }
							}
							  
						  
					  }
					  else help();
				  }
			  }
			  else if(args.length == 6)
			  {
				  if(args[0].equalsIgnoreCase("-c"))
				  {
					  if(args[2].equalsIgnoreCase("-r"))
					  {
						  String repCible = args[3];
						  File f = new File(repCible);
						  if(!f.exists())
						  {
							  
							  if(args[4].equalsIgnoreCase("-f"))
							  {
								  if(args[5].equalsIgnoreCase("-v"))
								  {
									  System.out.println("Compression de "+ args[1]+ " vers le repertoire "+repCible+" , Si il n'existe pas , il sera cree");
								  }
								  a = new Archiving(new File(args[1]));
							      a.start(args[3]);
							      c = new Compression(new File(args[3]+"\\"+args[3].substring(args[3].lastIndexOf("\\")+1, args[3].length())+".sfc"));
							      c.deflate();
							      System.out.println("Fichier de sorti : "+args[3]+"\\"+args[3].substring(args[3].lastIndexOf("\\")+1, args[3].length())+"_deflated.sfc");
							     
							  }
							  else
							  {
								  System.out.println("Le repertoire cible n'existe pas !");
							  }
							}
							  
						  
					  }
					  else help();
				  }
				  else if(args[0].equalsIgnoreCase("-d"))
				  {
					  if(args[2].equalsIgnoreCase("-r"))
					  {
						  String repCible = args[3];
						  File f = new File(repCible);
						  if(!f.exists())
						  {
							  
							  if(args[4].equalsIgnoreCase("-f"))
							  {
								  if(args[5].equalsIgnoreCase("-v"))
								  {
									  System.out.println("Decompression de fichiers du repertoire"+ args[1]+ " vers le repertoire "+repCible+" , Si il n'existe pas , il sera cree");
								  }
								   d = new Decompression(new File(args[1]));
								   d.inflate();
								   u = new UnPackager(new File(args[1].substring(0, args[1].indexOf("_deflated"))+".sfc"));
								   u.start(args[3]);
								   System.out.println("Repertoire de sorti : "+args[3]);
							  }
							  else
							  {
								  System.out.println("Le repertoire cible n'existe pas !");
							  }
							}
							  
						  
					  }
					  else help();
				  }
			  }
			  
			   
		   }
		   else help();
		   //compress
		   /*Archiving a = new Archiving (new File("C:\\Users\\Coumbiss\\Desktop\\Edacy"));
		   a.start();
           Compression c = new Compression(new File("C:\\Users\\Coumbiss\\Desktop\\Edacy.sfc"));
           c.deflate();
           //decompress
           c.inflate();
           UnPackager u = new UnPackager(new File("C:\\Users\\Coumbiss\\Desktop\\Edacy.sfc"));
           u.start();*/
	}

}
