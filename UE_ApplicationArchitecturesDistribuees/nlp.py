import os
import sys
sys.path.append("/asr")
import asr

audio = asr.demande_audio()
commande = asr.ASR(audio)

with open(commande) as f:
    lines = f.read() 
    mots = lines.split(" ") #on récupère chaque mot dans un tableau


objets=''
requete = mots[0] #La requête est le 1er mot du fichier
for i in range(1,len(mots)-1):
    objets+=mots[i]+' '
objets+=mots[len(mots)-1]
print("La requête est : ", requete, " et elle s'applique sur : ", objets)