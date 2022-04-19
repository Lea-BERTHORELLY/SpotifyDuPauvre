import sys, Ice, os
import MusicPlayer


import sqlite3

connectionDatabase = sqlite3.connect('MusicsDatabase.db', check_same_thread=False)
cursorDatabase = connectionDatabase.cursor()


def MusiquesClient():
	listeMusiques= os.listdir('/mnt/d/Documents/Cours/CERI/M1/S2/AAD/Musique_projet')
	for name in listeMusiques:
		print(name.replace(".mp3",''))



with Ice.initialize(sys.argv) as communicator:
	base = communicator.stringToProxy("SimplePrinter:default -p 10000")
	printer = MusicPlayer.PlayerPrx.checkedCast(base)
	if not printer:
		raise RuntimeError("Invalid proxy")
	else:
		cursorDatabase.execute("SELECT Titre FROM Musiques ")
		for row in cursorDatabase:
			print(row[0])
		while True:
			print("Que voulez vous faire ? -> Afficher, Ajouter, Avancer, Changer, Modifier, Play, Pause, Reculer, Stop, Supprimer, Quitter")
			choice = input()
			if choice.lower() == "afficher":
				cursorDatabase.execute("SELECT Titre FROM Musiques ")
				for row in cursorDatabase:
					print(row[0])

			elif choice.lower()== "ajouter":
				MusiquesClient()
				#musique=[]
				titre=input("Titre de la chanson à ajouter : ")
				artistes=input("Artistes : ")
				album=input("Album : ")
				fichier = "/mnt/d/Documents/Cours/CERI/M1/S2/AAD/Musique_projet/"+titre+".mp3"
				urlStream = "sout=#transcode{vcodec=none,acodec=mp3,ab=128,channels=2,samplerate=44100,scodec=none}:http{mux=mp3,dst=:8080/"+titre.replace(".mp3",'')+"}"

				file = open(fichier,'rb')
				chunkSize = 10000
				offset = 0
				results = []
				numRequests = 5    
				remotePath = "/mnt/d/Documents/Cours/CERI/M1/S2/Middleware/MusicPlayer/Musiques/" + titre + ".mp3"

				while True:
					
					chunck = file.read(chunkSize)
					if chunck == bytes('','utf-8') or chunck == None:
						break
					r = printer.begin_AddMusic(offset, chunck, remotePath, titre, artistes, album)
					offset += len(chunck)

					r.waitForSent()
					results.append(r)
				
					while len(results) > numRequests:
						r = results[0]
						del results[0]
						r.waitForCompleted()
				
				while len(results) > 0:
					r = results[0]
					del results[0]
					r.waitForCompleted()

				print("Musique ajoutée avec succès !")

			elif choice.lower() == "play":
				cursorDatabase.execute("SELECT Titre FROM Musiques ")
				for row in cursorDatabase:
					print(row[0])
				musique_a_jouer = input("Entrez le nom de la musique à jouer : ")
				print(printer.Play(musique_a_jouer+".mp3"))

			elif choice.lower() == "pause":
				print(printer.Pause())

			elif choice.lower() == "stop":
				print(printer.Stop())

			#elif choice.lower()=="changer":
			#elif choice.lower()=="avancer":
			#elif choice.lower()=="reculer":

			elif choice.lower()=="modifier":
				cursorDatabase.execute("SELECT Titre FROM Musiques ")
				for row in cursorDatabase:
					print(row[0])
				musique = input("Entrez le nom de la musique à modifier : ")
				nouveauTitre = input("Entrez le nouveau titre , ou laissez vide si vous voulez le laisser comme ceci : ")
				nouveauxArtistes = input("Entrez le nom des artistes , ou laissez vide si vous voulez les laisser comme ceci : ")
				nouvelAlbum = input("Entrez le nouvel album , ou laissez vide si vous voulez le laisser comme ceci : ")
				printer.ModifyMusic(musique, nouveauTitre, nouveauxArtistes, nouvelAlbum)

			elif choice.lower() == "supprimer":
				cursorDatabase.execute("SELECT Titre FROM Musiques ")
				for row in cursorDatabase:
					print(row[0])
				a_supprimer=input("Entrez le nom de la musique à supprimer : ")
				printer.DeleteMusic(a_supprimer)

			else:
				break