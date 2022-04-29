package com.example.lecteuraudio;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
//import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.videolan.libvlc.*;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import com.zeroc.Ice.Util;

public class MainActivity extends Activity {

    private static int MIC_PERMISSION_CODE=200;
    private Button rechercheVocale;
    private TextView music_currently_playing;
    private LibVLC libVlc;
    private MediaPlayer mediaPlayer;
    private Button playPause;
    private Button titreBtn;
    private Button artisteBtn;
    private Button albumBtn;
    private Button plus10;
    private Button moins10;
    private Button musiquePrecedente;
    private Button musiqueSuivante;
    private SeekBar seekBar;
    private EditText searchMusique;
    private TextView temps_total;
    private TextView temps_ecoule;
    private int affichage;
    private Media media;
    
    Handler handler;

    String urlMusique;
    String urlMusiqueEnCours;
    boolean isPaused=false;
    int idMusiqueEnCours;

    int debut=0;
    Runnable runnable;

    com.zeroc.Ice.Communicator communicator;
    com.zeroc.Ice.ObjectPrx obj;
    MusicPlayer.PlayerPrx player;

    int nbMusiques=0;
    String[] recuperationMusique;
    String[][] listeMusiquesIce;

    Musique[] ListMusiques;


    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //on récupère la date du jour + l'heure d'enregistrement
    //@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestPermissions();
        handler = new Handler();

        rechercheVocale = findViewById(R.id.rechercheVocale);
        music_currently_playing = findViewById(R.id.currently_playing);
        playPause = findViewById(R.id.play_pause);
        titreBtn = findViewById(R.id.titre);
        artisteBtn = findViewById(R.id.artiste);
        albumBtn = findViewById(R.id.album);
        plus10 = findViewById(R.id.plus10);
        moins10 = findViewById(R.id.moins10);
        musiquePrecedente = findViewById(R.id.precedent);
        musiqueSuivante = findViewById(R.id.suivant);
        //mediaPlayer = new MediaPlayer();
        seekBar = findViewById(R.id.seekBar);
        searchMusique = findViewById(R.id.searchEditText);
        temps_total = findViewById(R.id.temps_total);
        temps_ecoule = findViewById(R.id.temps_ecoule);

        playPause.setBackgroundResource(R.drawable.pause_logo);


        libVlc = new LibVLC(this);
        mediaPlayer = new MediaPlayer(libVlc);

        ListView musicsListView = (ListView)findViewById(R.id.listeMusiques);


       /* Musique WCHB = new Musique("What Could Have Been","Sting", "Arcane","https://www.cjoint.com/doc/22_02/LBvn7BKDNtn_sting-what-could-have-been-arcane-league-of-legends-riot-games-music.mp3");
        Musique Playground = new Musique("Playground","Bea Miller", "Arcane","https://www.cjoint.com/doc/22_02/LBvoawqgrSn_bea-miller-playground-arcane-league-of-legends-riot-games-music.mp3");
        Musique PopStars = new Musique("Pop Stars","KDA", "All Out","https://www.cjoint.com/doc/22_02/LBvl5xEnZNn_K-DA---POP-STARS-LYRICS-ft-G-I-DLE-Madison-Beer-Jaira-Burns-Color-Coded-Eng-Rom-Han-%EA%B0%80%EC%82%AC-.mp3");
        Musique More = new Musique("More", "KDA", "All Out" ,"https://www.cjoint.com/doc/22_03/LCcqcABmHOE_MORE.mp3");
        Musique TheBridge = new Musique("The Bridge", "Ray Chen", "Arcane " ,"https://www.cjoint.com/doc/22_03/LCcqfuJuvEE_The-Bridge.mp3");
        Musique Villain = new Musique("Villain", "KDA", " " ,"https://www.cjoint.com/doc/22_03/LCcqf5hc5LE_Villain.mp3");
        Musique DLA = new Musique("Dirty Little Animals", "Bones UK", "Arcane" ,"https://www.cjoint.com/doc/22_03/LCdrOiGNxTE_Dirty-Little-Animals.mp3");
        Musique Enemy = new Musique("Enemy", "Imagine Dragons", "Arcane" ,"https://www.cjoint.com/doc/22_03/LCdrO7YhuIE_Enemy.mp3");
        Musique MisfitToys = new Musique("Misfit Toys", "Pusha T & Mako", "Arcane" ,"https://www.cjoint.com/doc/22_03/LCdrQwSgW5E_Misfit-Toys.mp3");
        Musique Goodbye = new Musique("Goodbye", "Ramsey", "Arcane" ,"https://www.cjoint.com/doc/22_03/LCdrPUgZC7E_Goodbye.mp3");
        Musique Awaken = new Musique("Awaken","Valerie Broussard","LoL","http://192.168.68.110:8080/Awaken");
        Musique[] musiques = new Musique[]{WCHB,Playground, PopStars, More, TheBridge, Villain, DLA, Enemy, MisfitToys, Goodbye,Awaken};*/


       /* ArrayAdapter<Musique> musicsArrayAdapter = new ArrayAdapter<Musique>(this, android.R.layout.simple_list_item_1 , musiques);
        musicsArrayAdapter.sort((m1, m2) ->
                m1.getTitre().compareToIgnoreCase(m2.getTitre())); //on affiche les musiques par ordre alphabétique au lancement de l'appli
        affichage=1;*/



        try {
            communicator = Util.initialize();
            obj = communicator.stringToProxy("SimplePrinter:tcp -h 192.168.1.45 -p 10000");
            player = MusicPlayer.PlayerPrx.checkedCast(obj);
            //player.test();
            Toast.makeText(this, "Connexion au serveur de streaming...", Toast.LENGTH_LONG).show();
            //recuperationMusique[0]= player.GetMusics();


        }
        catch (Exception e){
            Toast.makeText(this, "Erreur de connection au serveur de streaming", Toast.LENGTH_LONG).show();
        }

        if (player.GetNumberOfMusics()==0){
            Toast.makeText(this, "0 musiques disponibles", Toast.LENGTH_LONG).show();
        }
        nbMusiques=player.GetNumberOfMusics();
        recuperationMusique = new String[4];
        listeMusiquesIce = new String[nbMusiques][4];
        ListMusiques = new Musique[nbMusiques];


        for (int i=0; i<nbMusiques;i++){
            recuperationMusique= player.PrintMusics(i);
            Log.v("Musiques", String.valueOf(i));
            listeMusiquesIce[i][0]= recuperationMusique[0];
            listeMusiquesIce[i][1]= recuperationMusique[1];
            listeMusiquesIce[i][2]= recuperationMusique[2];
            listeMusiquesIce[i][3]= recuperationMusique[3];
            Musique musique =new Musique(listeMusiquesIce[i][0],listeMusiquesIce[i][1],listeMusiquesIce[i][2],listeMusiquesIce[i][3]);
            ListMusiques[i]=musique;
        }


        Toast.makeText(this, nbMusiques+" musiques disponibles", Toast.LENGTH_LONG).show();
        Log.i("Musiques", String.valueOf(nbMusiques));
        for(int i=0;i<nbMusiques;i++){
            for(int j=0; j<4;j++){
                Log.i("Liste",listeMusiquesIce[i][j]);
            }
            Log.i("test",ListMusiques[i].getTitre());
        }




        ArrayAdapter<Musique> musicsArrayAdapter = new ArrayAdapter<Musique>(MainActivity.this, android.R.layout.simple_list_item_1 , ListMusiques);
        musicsArrayAdapter.sort((m1, m2) ->
                m1.getTitre().compareToIgnoreCase(m2.getTitre())); //on affiche les musiques par ordre alphabétique au lancement de l'appli
        affichage=1;

        //musicsListView.setAdapter(musicsArrayAdapter);
        musicsListView.setAdapter(musicsArrayAdapter);

        musicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(MainActivity.this,"Musique lancée : " + musicsArrayAdapter.getItem(i).getTitre(),Toast.LENGTH_SHORT).show();
                idMusiqueEnCours=i;
                urlMusique=musicsArrayAdapter.getItem(i).getUrl();
                music_currently_playing.setText(musicsArrayAdapter.getItem(i).getTitre());
                long duree = mediaPlayer.getLength();
                long seconds = duree/1000;
                long minutes = seconds/60;
                seconds = seconds % 60;
                temps_ecoule.setText("00:00");
                temps_total.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                seekBar.setMax((int) mediaPlayer.getLength());
                updateSeekBar();

                if (isPaused==true){
                    stopPlaying();
                }
                if(mediaPlayer.isPlaying()&&urlMusiqueEnCours!=urlMusique){ // si le lecteur est déjà lancé et qu'on veut changer de chanson, on arrete le lecteur
                    stopPlaying();
                    player.Stop();
                    mediaPlayer.release();
                    mediaPlayer = new MediaPlayer(libVlc);
                    String titre = musicsArrayAdapter.getItem(i).getTitre();
                    player.Play("Awaken");
                    urlMusiqueEnCours=urlMusique;
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); //on récupère les musiques via une/des url
                    Media media = new Media(libVlc, Uri.parse(urlMusique));
                    media.setHWDecoderEnabled(true, false);
                    media.addOption(":network-caching=600");

                    mediaPlayer.setMedia(media);
                    Log.i("test", String.valueOf(media.getDuration()));
                    media.release();
                    mediaPlayer.play();

                    //mediaPlayer.play();
                    seekBar.setProgress(0);
                    putDuration();
                }
                if(!mediaPlayer.isPlaying()){
                    urlMusiqueEnCours=urlMusique;
                    Log.i("testPlay",musicsArrayAdapter.getItem(i).getTitre());
                    stopPlaying();
                    player.Stop();
                    //mediaPlayer.release();

                    player.Play(String.valueOf(musicsArrayAdapter.getItem(i).getTitre()));
                    Media media = new Media(libVlc, Uri.parse(urlMusique));
                    media.setHWDecoderEnabled(true, false);
                    media.addOption(":network-caching=600");

                    mediaPlayer.setMedia(media);
                    media.release();
                    mediaPlayer.play();
                    //mediaPlayer.play();
                    seekBar.setProgress(0);
                    putDuration();
                }
            }
        });

//  -----------------------------------------------------------------------------------------------------------------------------------------------------
        //Gestion des différents boutons

        titreBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                musicsArrayAdapter.sort((m1, m2) ->
                        m1.getTitre().compareToIgnoreCase(m2.getTitre()));
                musicsListView.setAdapter(musicsArrayAdapter);
                affichage=1;
            }
        });

        artisteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                musicsArrayAdapter.sort((m1, m2) ->
                        m1.getAuteurs().compareToIgnoreCase(m2.getAuteurs()));
                musicsListView.setAdapter(musicsArrayAdapter);
                affichage=2;
            }
        });

        albumBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                musicsArrayAdapter.sort((m1, m2) ->
                        m1.getAlbum().compareToIgnoreCase(m2.getAlbum()));
                musicsListView.setAdapter(musicsArrayAdapter);
                affichage=3;
            }
        });

        playPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mediaPlayer.isPlaying()) {
                    playPause.setBackground(getResources().getDrawable(R.drawable.circle_button));
                    playPause.setBackgroundResource(R.drawable.play_logo);
                    mettrePause();
                    player.Pause();
                    isPaused=true;
                }
                else{
                    playPause.setBackground(getResources().getDrawable(R.drawable.circle_button));
                    playPause.setBackgroundResource(R.drawable.pause_logo);
                    mediaPlayer.play();
                    player.Play(musicsArrayAdapter.getItem(idMusiqueEnCours).getTitre());
                    isPaused=false;
                }

            }
        });

        plus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying() /*&& (mediaPlayer.getTime()+10000 <= mediaPlayer.getLength())*/) {
                    player.Avancer();
                    mediaPlayer.setTime((long) (mediaPlayer.getTime() + 10000));

                }
            }
        });

        moins10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying() && (mediaPlayer.getTime()-10000 >=0)) {
                    player.Reculer(10);
                    mediaPlayer.setTime(mediaPlayer.getTime() - 10000);

                }
                if (mediaPlayer.isPlaying() && (mediaPlayer.getTime()-10000 <0)) {
                    player.Reculer((int) mediaPlayer.getTime());
                    mediaPlayer.setTime(mediaPlayer.getTime() - mediaPlayer.getTime());

                }
            }
        });

        musiquePrecedente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idMusiqueEnCours--;
                stopPlaying();
                player.Stop();
                //mediaPlayer.release();


                //mediaPlayer.release();
                //mediaPlayer = new MediaPlayer(libVlc);
                if(idMusiqueEnCours>=0){ //si on n'est pas en train de jouer la première musique
                    urlMusique=musicsArrayAdapter.getItem(idMusiqueEnCours).getUrl();
                    music_currently_playing.setText(musicsArrayAdapter.getItem(idMusiqueEnCours).getTitre());
                    Media media = new Media(libVlc, Uri.parse(urlMusique));
                    media.setHWDecoderEnabled(true, false);
                    media.addOption(":network-caching=600");
                    mediaPlayer.release();
                    mediaPlayer= new MediaPlayer(libVlc);
                    mediaPlayer.setMedia(media);
                    media.release();
                    player.Play(String.valueOf(musicsArrayAdapter.getItem(idMusiqueEnCours).getTitre()));
                    mediaPlayer.play();
                    seekBar.setProgress(0);
                    putDuration();
                    playPause.setBackgroundResource(R.drawable.pause_logo);
                }
                else{
                    mediaPlayer.setPosition(0);
                }
            }
        });

        musiqueSuivante.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                idMusiqueEnCours++;
                stopPlaying();
                player.Stop();
                //mediaPlayer.release();
                // mediaPlayer= new MediaPlayer(libVlc);
                if(idMusiqueEnCours<musicsArrayAdapter.getCount()){ //si on n'est pas à la fin de la liste de musiques
                    urlMusique=musicsArrayAdapter.getItem(idMusiqueEnCours).getUrl();
                    music_currently_playing.setText(musicsArrayAdapter.getItem(idMusiqueEnCours).getTitre());
                    mediaPlayer.release();
                    mediaPlayer= new MediaPlayer(libVlc);
                    Media media = new Media(libVlc, Uri.parse(urlMusique));
                    media.setHWDecoderEnabled(true, false);
                    media.addOption(":network-caching=600");

                    mediaPlayer.setMedia(media);
                    media.release();
                    player.Play(String.valueOf(musicsArrayAdapter.getItem(idMusiqueEnCours).getTitre()));
                    mediaPlayer.play();
                    seekBar.setProgress(0);
                    putDuration();
                    playPause.setBackgroundResource(R.drawable.pause_logo);
                }
                else{ //si on est à la fin on arrête le lecteur
                    stopPlaying();
                    player.Stop();
                    mediaPlayer.release();
                }
            }

        });


        rechercheVocale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Dictez votre commande");
                try {
                    startActivityForResult(intent, MIC_PERMISSION_CODE);
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, " " + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        // ---------------------------------------------------------
        //Gestion de la searchBar

        searchMusique.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                musicsArrayAdapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (affichage==1){
                    titreBtn.callOnClick();
                }
                else if (affichage==2){
                    artisteBtn.callOnClick();
                }
                else{
                    albumBtn.callOnClick();
                }
            }
        });

// -------------------------------- gestion de la seekbar -------------------------------
        //gestion du déplacement dans la musique au clic sur la seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                if(fromUser){ //si l'utilisateur a clické sur la seekbar
                    mediaPlayer.setTime(i); //on avance/recule la musique à l'endroit demandé
                    seekBar.setProgress(i);
                    temps_ecoule.setText(String.valueOf(i/1000/60)+":"+String.valueOf((i/1000)%60));
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void updateSeekBar(){
        int currentPos = (int) mediaPlayer.getTime();
        seekBar.setProgress(currentPos);
        temps_ecoule.setText(String.valueOf(currentPos/1000/60)+":"+String.valueOf((currentPos/1000)%60));
        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        };
        handler.postDelayed(runnable,1000);
    }

    private void stopPlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()==true) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void mettrePause(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()==true) {
            mediaPlayer.pause();
            //changeProgressBar(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MIC_PERMISSION_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //music_currently_playing.setText(Objects.requireNonNull(result).get(0));
                FileOutputStream fos = null;
                try {
                    writeToFile(Objects.requireNonNull(result).get(0).toString(),this);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /*
    Cette méthode permet de demander les permissions à l'utilisateur (micro + stockage)
     */
    private void RequestPermissions() {
        if(ContextCompat.checkSelfPermission(this, RECORD_AUDIO)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[] {RECORD_AUDIO},MIC_PERMISSION_CODE);
        }
        if(ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[] {WRITE_EXTERNAL_STORAGE},MIC_PERMISSION_CODE);
        }
    }


    /*
     * cette fonction permet de sauvegarder la requete dans un fichier txt
     */
    private void writeToFile(String data, Context context) throws IOException {
        File path = context.getExternalFilesDir(null);
        String fileName= "Text"+timeStamp+".txt";
        File file = new File(path,fileName);
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(data.getBytes());
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            stream.close();
        }
    }

    public String createDuration(int duration) {
        String time = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;
        time = time + min + ":";
        if (sec < 10) {
            time += "0";
        }
        time += sec;
        return time;
    }

    private void putDuration(){
        String endTime = createDuration((int) mediaPlayer.getMedia().getDuration());
        Log.i("myTag", endTime);
        Log.i("myTag", String.valueOf(mediaPlayer.getPosition()));
        Log.i("myTag", String.valueOf(mediaPlayer.getTime()));
        Log.i("myTag", String.valueOf(mediaPlayer.getPosition()*mediaPlayer.getLength()));
        temps_total.setText(endTime);
    }

}