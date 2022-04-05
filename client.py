import sys, Ice, os
import MusicPlayer


	


mp3files=[]
files = os.listdir('Musiques')
for name in files:
	print(name.replace(".mp3",''))
	mp3files.append(name)



with Ice.initialize(sys.argv) as communicator:
	base = communicator.stringToProxy("SimplePrinter:default -p 10000")
	printer = MusicPlayer.PlayerPrx.checkedCast(base)
	if not printer:
		raise RuntimeError("Invalid proxy")
	else:
		for i in range(0, len(mp3files)):
			printer.PrintMusics(mp3files[i])
		#printer.Play("Rise.mp3")
		while True:
			print("Que voulez vous faire ? -> Afficher, Play, Pause, Stop, Changer, Avancer, Modifier, Supprimer, Quitter")
			choice = input()
			if choice.lower() == "afficher":
				for i in range(0, len(mp3files)):
					printer.PrintMusics(mp3files[i])	
					print(mp3files[i].replace(".mp3",''))

			elif choice.lower() == "play":
				musique_a_jouer = input("Entrez le nom de la musique à jouer : ")
				printer.Play(musique_a_jouer+".mp3")

			elif choice.lower() == "pause":
				printer.Pause()

			elif choice.lower() == "stop":
				printer.Stop()

			elif choice.lower()== "modifier":
				ancien_nom=input("Entrez le nom de la musique à modifier : ")
				nouveau_nom=input("Entrez le nouveau nom de la musique : ")
				printer.ModifyMusic(ancien_nom + ".mp3", nouveau_nom + ".mp3")

			elif choice.lower() == "supprimer":
				a_supprimer=input("Entrez le nom de la musique à supprimer : ")
				printer.DeleteMusic(a_supprimer)
				
			else:
				break
		#printer.ModifyMusic('RISE (Lyrics) ft. The Glitch Mob, Mako, and The Word Alive.mp3','Rise.mp3')