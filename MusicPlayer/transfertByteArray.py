tableau=[]
fn = "D:\Documents\Cours\CERI\M1\S2\Application architectures distribuees\Musique_projet/"+"Godsent"+".mp3"
audio_file = open(fn, 'rb')
file_eof = audio_file.read()
tableau.append(file_eof)
byte_array = bytearray(file_eof)
audio_file.close()


def Test(test):
    out_file = open("D:\Documents\Cours\CERI\M1\S2\Middleware\MusicPlayer/Musiques/"+"Godsent"+".mp3", "wb") # open for [w]riting as [b]inary
    out_file.write(test)
    out_file.close()

Test(byte_array)
