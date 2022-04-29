import os
from os import path

import speech_recognition as sr
#print('__file__:    ', __file__) #nom du fichier .py
#print( __file__.replace('asr.py','') + 'commande.txt')

def demande_audio():
    print('Entrez le chemin d\'accès du fichier audio')
    path=input()
    return path

def ASR(path):
    r = sr.Recognizer()
    with sr.AudioFile(path) as source: #on ouvre le fichier choisi
        audio_data = r.record(source)
        text = r.recognize_google(audio_data,language='fr') #on transforme l'audio en texte, et on stocke ça dans un fichier texte
        with open('commande.txt', 'w') as f:
            f.write(text)
    return __file__.replace('asr.py','') + 'commande.txt' #on renvoie le chemin d'accès complet du fichier txt contenant la requête