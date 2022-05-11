package com.example.lecteuraudio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import com.zeroc.Ice.Util;
import com.zeroc.Ice.*;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


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
    //private Media media;
    private MediaRecorder mediaRecorder;

    private AudioManager audioManager;
    
    Handler handler;

    String urlMusique;
    String urlMusiqueEnCours;
    boolean isPaused=false;
    int idMusiqueEnCours;

    //int debut=0;
    Runnable runnable;

    com.zeroc.Ice.Communicator communicator;
    com.zeroc.Ice.ObjectPrx obj;
    MusicPlayer.PlayerPrx player;

    int nbMusiques=0;
    String[] recuperationMusique;
    String[][] listeMusiquesIce;

    Musique[] ListMusiques;

    String[] requete = new String[2];


    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //on récupère la date du jour + l'heure d'enregistrement
    //@RequiresApi(api = Build.VERSION_CODES.N)
    boolean isRecording =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
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



        try {
            communicator = Util.initialize();
            obj = communicator.stringToProxy("SimplePrinter:tcp -h 10.126.5.158 -p 10000"); //192.168.68.113
            player = MusicPlayer.PlayerPrx.checkedCast(obj);
            //player.test();
            Toast.makeText(this, "Connexion au serveur de streaming...", Toast.LENGTH_LONG).show();
            //recuperationMusique[0]= player.GetMusics();


        }
        catch (java.lang.Exception e){
            Toast.makeText(this, "Erreur de connection au serveur de streaming", Toast.LENGTH_LONG).show();
        }

        if (player.GetNumberOfMusics()==0){
            Toast.makeText(this, "0 musiques disponibles", Toast.LENGTH_LONG).show();
        }
        nbMusiques=player.GetNumberOfMusics();
        recuperationMusique = new String[5];
        listeMusiquesIce = new String[nbMusiques][5];
        ListMusiques = new Musique[nbMusiques];


        for (int i=0; i<nbMusiques;i++){
            recuperationMusique= player.PrintMusics(i);
            Log.v("Musiques", String.valueOf(i));
            listeMusiquesIce[i][0]= recuperationMusique[0];
            listeMusiquesIce[i][1]= recuperationMusique[1];
            listeMusiquesIce[i][2]= recuperationMusique[2];
            listeMusiquesIce[i][3]= recuperationMusique[3];
            listeMusiquesIce[i][4]= recuperationMusique[4];
            Musique musique = new Musique(listeMusiquesIce[i][0],listeMusiquesIce[i][1],listeMusiquesIce[i][2],listeMusiquesIce[i][3],Integer.parseInt(listeMusiquesIce[i][4]));
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
                seekBar.setMax(musicsArrayAdapter.getItem(i).getDuree()*1000);
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
                    String endTime = createDuration(musicsArrayAdapter.getItem(idMusiqueEnCours).getDuree());
                    temps_total.setText(endTime);
                }
                if(!mediaPlayer.isPlaying()){
                    urlMusiqueEnCours=urlMusique;
                    //Log.i("testPlay",musicsArrayAdapter.getItem(i).getTitre());
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
                    String endTime = createDuration(musicsArrayAdapter.getItem(idMusiqueEnCours).getDuree());
                    temps_total.setText(endTime);
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
                    player.Avancer(10);
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
                    String endTime = createDuration(musicsArrayAdapter.getItem(idMusiqueEnCours).getDuree());
                    temps_total.setText(endTime);
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

                    String endTime = createDuration(musicsArrayAdapter.getItem(idMusiqueEnCours).getDuree());
                    temps_total.setText(endTime);

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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {
                if (!isRecording){
                    isRecording = true;
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setOutputFile(getRecordingFilePath());
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setMaxDuration(8000);
                    try {
                        mediaRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaRecorder.start();
                    Toast.makeText(MainActivity.this, "Enregistrement en cours",Toast.LENGTH_SHORT).show();
                }
                else{
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording = false;

                    rechercheVocale.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Enregistrement terminé",Toast.LENGTH_SHORT).show();

                    int offset = 0;
                    //File file = new File(getRecordingFilePath());
                    try {
                        byte[] fileContent = Files.readAllBytes(Paths.get(getRecordingFilePath()));
                        player.send(offset,fileContent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    requete=player.AsrNlp();
                    //Toast.makeText(MainActivity.this, "La requete est : "+requete[0]+" "+requete[1],Toast.LENGTH_SHORT).show();
                    if (requete[1].equalsIgnoreCase("suivante")){
                        Toast.makeText(MainActivity.this, " Vous avez demandé à passer à la musique suivante ",Toast.LENGTH_SHORT).show();
                        musiqueSuivante.performClick();
                        rechercheVocale.setEnabled(true);
                    }
                    else if (requete[1].equalsIgnoreCase("précédente")){
                        Toast.makeText(MainActivity.this, " Vous avez demandé à passer à la musique précédente ",Toast.LENGTH_SHORT).show();
                        musiquePrecedente.performClick();
                        rechercheVocale.setEnabled(true);
                    }
                    else if (requete[1].equalsIgnoreCase("pause")){
                        Toast.makeText(MainActivity.this, " Vous avez demandé à mettre pause ",Toast.LENGTH_SHORT).show();
                        playPause.performClick();
                        rechercheVocale.setEnabled(true);
                    }
                    else if (requete[0].equalsIgnoreCase("relance")){
                        Toast.makeText(MainActivity.this, " Vous avez demandé à relancer la musique ",Toast.LENGTH_SHORT).show();
                        playPause.performClick();
                        rechercheVocale.setEnabled(true);
                    }
                    else if(requete[0].equalsIgnoreCase("joue")){
                        if (requete[1].equalsIgnoreCase("ennemi")){
                            requete[1]="enemy";
                        }
                        if ((requete[1].equalsIgnoreCase("vilain")) || requete[1].equalsIgnoreCase("vilaine")){
                            requete[1]="villain";
                        }
                        for(int i=0;i<musicsArrayAdapter.getCount();i++){
                            if(musicsArrayAdapter.getItem(i).getTitre().equalsIgnoreCase(requete[1])){
                                //Log.i("equality","equals");
                                //musicsListView.getAdapter().getView(i, null, null).performClick();
                                //musicsListView.getAdapter().getView(i,null,null).callOnClick();
                                musicsListView.performItemClick(
                                        musicsListView.getAdapter().getView(i, null, null),
                                        i,
                                        musicsListView.getAdapter().getItemId(i));

                                Toast.makeText(MainActivity.this, " Vous avez demandé à jouer la musique : "+requete[1],Toast.LENGTH_SHORT).show();
                                rechercheVocale.setEnabled(true);
                            }
                        }
                    }
                    else if(requete[0].equalsIgnoreCase("avance")) {
                        plus10.performClick();
                        rechercheVocale.setEnabled(true);
                    }
                    else if(requete[0].equalsIgnoreCase("recule")){
                        moins10.performClick();
                        rechercheVocale.setEnabled(true);
                    }
                    else if(requete[0].equalsIgnoreCase("monte")){
                        monterVolume();
                        rechercheVocale.setEnabled(true);
                    }
                    else if(requete[0].equalsIgnoreCase("baisse")){
                        baisserVolume();
                        rechercheVocale.setEnabled(true);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "La requête "+requete[0]+" "+requete[1]+" n'a pas pu être lancée, veuillez la reformuler",Toast.LENGTH_SHORT).show();
                        rechercheVocale.setEnabled(true);
                    }

                }


                /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Dictez votre commande");
                try {
                    startActivityForResult(intent, MIC_PERMISSION_CODE);
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, " " + e.getMessage(),Toast.LENGTH_SHORT).show();
                }*/
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

                    if(i>seekBar.getProgress()){
                        player.Avancer(i*2);
                    }

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
        //if (mediaPlayer.getTime()>0)
        //seekBar.setProgress((int) ((mediaPlayer.getTime()/1000)/2));
        //Log.i("test", String.valueOf(mediaPlayer.getTime()/1000));
        temps_ecoule.setText(String.valueOf(currentPos/1000/60)+":"+String.valueOf((currentPos/1000)%60));

        //Log.i("test3", String.valueOf(mediaPlayer.getTime()));
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
        int min = duration / 60;
        int sec = duration % 60;
        time = time + min + ":";
        if (sec < 10) {
            time += "0";
        }
        time += sec;
        return time;
    }



    public String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "Enregistrement"+ timeStamp + ".3gpp");
        return file.getPath();
    }

    public void monterVolume(){
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
    }

    public void baisserVolume(){
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
    }

}