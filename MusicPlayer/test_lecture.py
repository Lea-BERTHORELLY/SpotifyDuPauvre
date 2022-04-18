import os,numpy

tableau_bytes=[]
morceau=''
mystring = '\n'.encode()
#f = open("D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Musiques/Villain.wav", 'rb').read() # opening a binary file and reading it
fn = 'D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Musiques/Awaken.mp3'
with open(fn, "rb") as f:
    data = f.read()
    byte=f.read(10)
    while byte:
        tableau_bytes.append(f.read(1000000))
        #tableau.append(bytes)
        #with open("test.mp3", 'wb') as output:
        #    output.write(byte)
        byte = f.read(1000000) # read another 5000 bytes
f.close()





file = open(fn,'rb')
chunkSize = 61440
offset = 0

results = []
numRequests = 5
remotePath = "D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/test.mp3"
while True:

    chuck = file.read(chunkSize) # Read a chunk

    if chuck == bytes('','utf-8') or chuck == None:
        break

    r = printer.begin_send(offset, chuck, remotePath)
    offset += len(chuck)

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

print("Finished")










    #print(data[i])

#print(len(tableau_bytes))
#print(tableau_bytes[0])

#with open("test.mp3", 'wb') as output:
    #for i in range(len(tableau_bytes)):
        #output.write(bytes(tableau_bytes[i]))
#for i in range(len(tableau_bytes)):
    #print(tableau_bytes[i])
##import sys, os
##from pydub import AudioSegment
##
##tableau=[]
##
##fn = 'D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Musiques/Villain.mp3'
##
##dest = 'D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Villain.wav'
##
##sound = AudioSegment.from_mp3(fn)
##sound.export(dest, format="wav")

#fn = "D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/texte.txt"

##kilobytes = 1024
##megabytes = kilobytes * 1000
##chunksize = int(1.4 * kilobytes)
##
##with open(fn, "rb") as in_file:
##    bytes = in_file.read(5000) # read 5000 bytes
##    while bytes:
##        tableau.append(bytes)
##        with open("test.mp3", 'wb') as output:
##            output.write(bytes)
##        bytes = in_file.read(5000) # read another 5000 bytes
##
##
##for i in range(9):
##    print(tableau[i])



# Split into 4 files
#f = open(fn, 'rb')
#while 1:
#    chunk = f.read(chunksize)
#    if not chunk: break
#    fileobj = open('test.mp3','wb')
#    fileobj.write(chunk)
#    tableau.append(chunk)
#f.close()
#fileobj.close()

#print(tableau[0])