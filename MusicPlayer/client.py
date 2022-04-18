import sys, Ice, os
import MusicPlayer

print(os.listdir('/mnt/d/Documents/Cours/CERI/M1/S2/AAD/Musique_projet'))


mp3files=[]
files = os.listdir('Musiques')
for name in files:
	print(name.replace(".mp3",''))
	mp3files.append(name)


def MusiquesClient():
	listeMusiques= os.listdir('/mnt/d/Documents/Cours/CERI/M1/S2/AAD/Musique_projet')
	for name in listeMusiques:
		print(name.replace(".mp3",''))



with Ice.initialize(sys.argv) as communicator:
	#Ice.MessageSizeMax=6000
	base = communicator.stringToProxy("SimplePrinter:default -p 10000")
	printer = MusicPlayer.PlayerPrx.checkedCast(base)
	#printer.MessageSizeMax=6000
	if not printer:
		raise RuntimeError("Invalid proxy")
	else:
		for i in range(0, len(mp3files)):
			printer.PrintMusics(mp3files[i])
		#AddMusic()
		#printer.Play("Rise.mp3")
		while True:
			print("Que voulez vous faire ? -> Afficher, Ajouter, Play, Pause, Stop, Changer, Avancer, Reculer, Renommer, Remplacer, Supprimer, Quitter")
			choice = input()
			if choice.lower() == "afficher":
				mp3files=[]
				files = os.listdir('Musiques')
				for name in files:
					print(name.replace(".mp3",''))

			elif choice.lower()== "ajouter":
				MusiquesClient()
				#musique=[]
				titre=input("Titre de la chanson à ajouter : ")
				#artistes=input("Artistes : ")
				#album=input("Album : ")
				fichier = "/mnt/d/Documents/Cours/CERI/M1/S2/AAD/Musique_projet/"+titre+".mp3"
				urlStream = "sout=#transcode{vcodec=none,acodec=mp3,ab=128,channels=2,samplerate=44100,scodec=none}:http{mux=mp3,dst=:8080/"+titre.replace(".mp3",'')+"}"


				file = open(fichier,'rb')
				chunkSize = 10000  #61440
				offset = 0
				
				results = []
				numRequests = 5    
				
				remotePath = "/mnt/d/Documents/Cours/CERI/M1/S2/Middleware/MusicPlayer/Musiques/" + titre + ".mp3"

				while True:
					
					chunck = file.read(chunkSize) # Read a chunk

					if chunck == bytes('','utf-8') or chunck == None:
						break
				
					r = printer.begin_AddMusic(offset, chunck, remotePath)
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



				##in_file = open(fichier, "rb")

				##Ceci est un test, en cas de bug -> supprimer
				
				##lecture = in_file.read()
				##byte_array = bytearray(lecture)


				#file_eof = in_file.read()
				#musique.append(file_eof)

				##Fin du test
				#musique = bytearray(in_file.read())
				#musique = in_file.read()


				##in_file.close()
				##printer.AddMusic(titre,byte_array)


				#printer.AddMusic(titre, artistes, album, fichier, urlStream, bytes(byte_array))

			elif choice.lower() == "play":
				for i in range(0, len(mp3files)):
					printer.PrintMusics(mp3files[i])
				musique_a_jouer = input("Entrez le nom de la musique à jouer : ")
				printer.Play(musique_a_jouer+".mp3")

			elif choice.lower() == "pause":
				printer.Pause()

			elif choice.lower() == "stop":
				printer.Stop()

			#elif choice.lower()=="changer":
			#elif choice.lower()=="avancer":
			#elif choice.lower()=="reculer":

			elif choice.lower()== "renommer":
				for i in range(0, len(mp3files)):
					printer.PrintMusics(mp3files[i])
				ancien_nom=input("Entrez le nom de la musique à renommer : ")
				nouveau_nom=input("Entrez le nouveau nom de la musique : ")
				printer.RenameMusic(ancien_nom, nouveau_nom)

			#elif choice.lower()=="remplacer":

			elif choice.lower() == "supprimer":
				for i in range(0, len(mp3files)):
					printer.PrintMusics(mp3files[i])
				a_supprimer=input("Entrez le nom de la musique à supprimer : ")
				printer.DeleteMusic(a_supprimer)

			else:
				break
		#printer.ModifyMusic('RISE (Lyrics) ft. The Glitch Mob, Mako, and The Word Alive.mp3','Rise.mp3')