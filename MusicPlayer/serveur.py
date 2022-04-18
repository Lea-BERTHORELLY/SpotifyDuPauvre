from pickle import FALSE
import sys, Ice, os
import MusicPlayer
import vlc
from pydub import AudioSegment
import io
import shutil

Ice.MessageSizeMax=6000

instance = vlc.Instance()
player = instance.media_player_new()
musique_en_cours=False
partiesMusique=[]
titre=''

class PlayerI(MusicPlayer.Player):
    
    def SendParts(self, titreMusique, partie:bytes, current=None):
        titre=titreMusique
        partiesMusique.append(partie)
        return partiesMusique
    
    def PrintMusics(self, s, current=None):
        print(s.replace(".mp3",'')) #On affiche seulement le nom de la musique, pas l'extension

    def AddMusic(self,offset,partiesMusique:bytes, path,current=None):
        url = "D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer\Musiques\\"
        
        #print("test1")
        

        #with open("D:/Documents/Cours/CERI/M1/S2/Middleware/MusicPlayer/Musiques/"+"Godsent"+".mp3", "wb") as f:
        #    f.write(musique)
        #f.close()

        #audio_segment = AudioSegment.from_file(io.BytesIO(partiesMusique))
        #audio_segment.export(path, format='mp3')

        # Open the file in Byte mode
        musicFile = open(path,'ab+')

        # Move in the file
        musicFile.seek(offset)
        
        # Write the bytes
        musicFile.write(partiesMusique)

        # Close the stream
        musicFile.close()


        #audio_segment = AudioSegment.from_file(io.BytesIO(musique))
        #audio_segment.export(url+titre+'.mp3', 'mp3')
        print("Musique ajoutée !")

        



        #out_file = open(url+titre+".mp3", "wb") # open for [w]riting as [b]inary
        #out_file.write(partiesMusique[0])
        #out_file.close()


        #print("on va tester l'écriture")
        #out_file = open("D:/Documents/Cours/CERI/M1/S2/Middleware/MusicPlayer/Musiques/"+titre+".mp3", "wb") # open for [w]riting as [b]inary
        #print("le fichier est ouvert")
        #out_file.write(musique)
        #print("ça écrit")
        #out_file.close()

    def Play(self, s, current=None): 
        global musique_en_cours
        if musique_en_cours:
            player.play()
        else:
            media = instance.media_new('Musiques/'+s)
            #media.add_option("sout=#rtp{vcodec=none,acodec=mp3,ttl=10,port=10000,sdp=rtsp://:10000/"+s.replace(".mp3",'')+ "}")
            media.add_option("sout=#transcode{vcodec=none,acodec=mp3,ab=128,channels=2,samplerate=44100,scodec=none}:http{mux=mp3,dst=:8080/"+s.replace(".mp3",'')+"}")
            media.add_option(":no-sout-all")
            media.add_option(":sout-keep")
            player.set_media(media)
            player.play()
            musique_en_cours=True
            print("Musique en cours : "+s.replace(".mp3",''))
        return musique_en_cours

    def Pause(self, current=None):
        player.pause()

    def RenameMusic(self,oldName,newName,current=None):
        old_file = os.path.join("Musiques/", oldName+".mp3")
        new_file = os.path.join("Musiques/", newName+".mp3")
        print("ancien nom : ", oldName, " nouveau nom : ", newName)
        os.rename(old_file, new_file)

    def Stop(self, current=None):
        global musique_en_cours
        player.stop()
        musique_en_cours=False
        return musique_en_cours


    def DeleteMusic(self, s, current=None):
        print("Musiques/"+s+".mp3")
        if os.path.exists("Musiques/"+s+".mp3"):
            os.remove("Musiques/"+s+".mp3")
            print("Musique ", s+".mp3"," supprimée")
        else:
            print("La musique n'existe pas et ne peut par conséquent être supprimée") 



with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000")
    object = PlayerI()
    adapter.add(object, communicator.stringToIdentity("SimplePrinter"))
    adapter.activate()
    communicator.waitForShutdown()