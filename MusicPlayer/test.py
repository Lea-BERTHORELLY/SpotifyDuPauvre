import os,io
import array
byte_array = array.array('B')


titre=input("Titre de la chanson à ajouter : ")
fn = "D:\Documents\Cours\CERI\M1\S2\Application architectures distribuees\Musique_projet/"+titre+".mp3"
tableau=[]



#in_file = open(fn, "rb") # opening for [r]eading as [b]inary
#data = in_file.read() # if you only wanted to read 512 bytes, do .read(512)
#in_file.close()

#print(len(data))
#out_file = open("D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Musiques/"+titre+".mp3", "wb") # open for [w]riting as [b]inary
#out_file.write(data)
#out_file.close()


audio_file = open(fn, 'rb')
file_eof = audio_file.read()
tableau.append(file_eof)


byte_array = bytearray(file_eof)
print(len(byte_array))
#file = bytearray(audio_file.read())

#byte_array.fromstring(audio_file.read())
#print(len(byte_array))
audio_file.close()
print(len(tableau))

out_file = open("D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Musiques/"+titre+".mp3", "wb") # open for [w]riting as [b]inary
out_file.write(byte_array)
out_file.close()