from pickle import FALSE
import sys, Ice, os
import MusicPlayer
import vlc
from pydub import AudioSegment
import io
import shutil

import sqlite3

connectionDatabase = sqlite3.connect('MusicsDatabase.db', check_same_thread=False)
cursorDatabase = connectionDatabase.cursor()

instance = vlc.Instance()
player = instance.media_player_new()
musique_en_cours=False
partiesMusique=[]
titre=''



class PlayerI(MusicPlayer.Player):
    
    def GetNumberOfMusics(self,current=None):
        nb=0
        cursorDatabase.execute("SELECT COUNT(*) FROM Musiques")
        for row in cursorDatabase:
            nb+=row[0]
        return(nb)

    def PrintMusics(self, i, current=None):

        musiquesBDD=[]
        #print(s.replace(".mp3",'')) #On affiche seulement le nom de la musique, pas l'extension
        #print(cursorDatabase.execute("SELECT * FROM Musiques "))
        cursorDatabase.execute("SELECT Titre, Artistes, Album, UrlStream FROM Musiques LIMIT 1 OFFSET " + str(i))
        #cursorDatabase.execute(" SELECT * FROM Musiques WHERE Id =? " ,(1))
        for row in cursorDatabase:
            print(row[0], ' ', row[1],' ', row[2],' ', row[3])
            musiquesBDD.append(str(row[0]))
            #print(musiquesBDD[0])
            musiquesBDD.append(str(row[1]))
            #print(musiquesBDD[1])
            musiquesBDD.append(str(row[2]))
            musiquesBDD.append(str(row[3]))
            #print(musiquesBDD[0], ' ', musiquesBDD[1],' ', musiquesBDD[2],' ', musiquesBDD[3])
        return musiquesBDD

    def AddMusic(self,offset,partiesMusique:bytes, path, titre, artistes, album, current=None):
        #url = "/mnt/d/Documents/Cours/CERI/M1/S2/Middleware/MusicPlayer/Musiques/"
        #Création du fichier sur le pc
        musiqueAjoutee = open(path,'ab+')
        musiqueAjoutee.seek(offset)
        musiqueAjoutee.write(partiesMusique)
        musiqueAjoutee.close()

    def AddMusicDatabase(self, titre, artistes, album, current=None):
        #Création du fichier dans la bdd
        urlStream = "http://192.168.68.112:8080/"+titre.replace(" ",'')
        cursorDatabase.execute("INSERT INTO Musiques(Titre,Artistes,Album,UrlStream) VALUES(?,?,?,?) ", (titre,artistes,album,urlStream))
        connectionDatabase.commit()
        print("Musique ajoutée !")

    def Play(self, s, current=None): 
        global musique_en_cours
        if musique_en_cours:
            player.play()
        else:
            media = instance.media_new('Musiques/'+s+'.mp3')
            media.add_option("sout=#transcode{vcodec=none,acodec=mp3,ab=128,channels=2,samplerate=44100,scodec=none}:http{mux=mp3,dst=:8080/"+s.replace(".mp3",'').replace(" ",'')+"}")
            media.add_option(":no-sout-all")
            media.add_option(":sout-keep")
            player.set_media(media)
            player.play()
            musique_en_cours=True
            print("Musique en cours : "+s.replace(".mp3",''))
        return musique_en_cours

    def Pause(self, current=None):
        player.pause()
        return ("La musique a été mise en pause ")

    def ModifyMusic(self,musiqueAModifier,  nouveauTitre,  nouveauxArtistes, nouvelAlbum, current=None):
        if nouveauTitre:
            cursorDatabase.execute("UPDATE Musiques SET Titre=? WHERE Titre=?", (nouveauTitre,musiqueAModifier))
            connectionDatabase.commit()
            old_file = os.path.join("Musiques/", musiqueAModifier+".mp3")
            new_file = os.path.join("Musiques/", nouveauTitre+".mp3")
            os.rename(old_file, new_file)
        if nouveauxArtistes:
            cursorDatabase.execute("UPDATE Musiques SET Artistes=? WHERE Titre=?", (nouveauxArtistes,musiqueAModifier))
            connectionDatabase.commit()
        if nouvelAlbum:
            cursorDatabase.execute("UPDATE Musiques SET Album=? WHERE Titre=?", (nouvelAlbum,musiqueAModifier))
            connectionDatabase.commit()


    def Stop(self, current=None):
        global musique_en_cours
        player.stop()
        musique_en_cours=False
        return musique_en_cours


    def DeleteMusic(self, s, current=None):
        print("Musiques/"+s+".mp3")
        if os.path.exists("Musiques/"+s+".mp3"):
            cursorDatabase.execute("DELETE FROM Musiques WHERE Titre=?", (s,))
            connectionDatabase.commit()
            os.remove("Musiques/"+s+".mp3")
            print("Musique ", s+".mp3"," supprimée")
            
        else:
            print("La musique n'existe pas et ne peut par conséquent être supprimée") 

    def Avancer(self, current=None):
        print(player.get_time())
        player.set_time(player.get_time()+10000)
        print(player.get_time())

    def Reculer(self,s, current=None):
        print(player.get_time())
        player.set_time(player.get_time()-(s*1000))
        print(player.get_time())



with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "tcp -h 192.168.1.45 -p 10000")
    object = PlayerI()
    adapter.add(object, communicator.stringToIdentity("SimplePrinter"))
    adapter.activate()
    communicator.waitForShutdown()