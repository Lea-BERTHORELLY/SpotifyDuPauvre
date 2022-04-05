from pickle import FALSE
import sys, Ice, os
import MusicPlayer
import vlc

instance = vlc.Instance()
player = instance.media_player_new()
musique_en_cours=False


class PlayerI(MusicPlayer.Player):
    
    
    def PrintMusics(self, s, current=None):
        print(s.replace(".mp3",'')) #On affiche seulement le nom de la musique, pas l'extension

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

    def ModifyMusic(self,oldName,newName,current=None):
        old_file = os.path.join("Musiques/", oldName)
        new_file = os.path.join("Musiques/", newName)
        os.rename(old_file, new_file)

    def Stop(self, current=None):
        global musique_en_cours
        player.stop()
        musique_en_cours=False
        return musique_en_cours


    def DeleteMusic(self, s, current=None):
        if os.path.exists(s+".mp3"):
            os.remove(s+".mp3")
        else:
            print("La chanson n'existe pas et ne peut par conséquent être supprimée") 



with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000")
    object = PlayerI()
    adapter.add(object, communicator.stringToIdentity("SimplePrinter"))
    adapter.activate()
    communicator.waitForShutdown()