import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class UndertaleFanGame extends PApplet {

//Personnages et \u00e9lements du jeu sont inspir\u00e9s du jeu Undertale

                                                       //Musique
Minim minim;                                                              //
AudioPlayer Sans;                                                         //
AudioPlayer Megalovania;                                                  //
AudioPlayer Hit;                                                          //Sfx
int x, y, dX, dY, Try;                                                    //Coord joueur, d\u00e9placement, nb d'essais
int index;                                                                //Animation du perso
boolean[] downKeys=new boolean[256];                                      //Detection des touches du clavier
int[][] Stage=new int[25][26];                                            //Plateau
String preplateau[];                                                      //
boolean jeu, stage1, stage2, stage3, stage4, stage5, End;                 //Niveaux du jeu
PImage Title, Sans1, Sans2, Sans3, Heart, Face;                           //Images du boss et des morts
PFont CSansMS;                                                            //Police des textes du boss

public void setup() {
  size(1200, 800);
  background(0, 0, 30);
  noStroke();
  fill(0);
  rect(12, 12, 1175, 775);
  minim = new Minim(this);                                                //Musique
  Sans = minim.loadFile("Sans.mp3");                                      //
  Megalovania = minim.loadFile("Megalovania.mp3");                        //
  Hit = minim.loadFile("Hit.mp3");                                        //

  Sans.play();                                                            //Musiques \u00e0 choisir
  Sans.loop();                                                            //Il faut \u00e9galement modifier la fin du End() si on change de musique
  //Megalovania.play();                                                     //
  //Megalovania.loop();                                                     //
  index=0;
  x=15;
  y=690;
  dX=0;
  dY=0;
  jeu=true;                                                              //Boolean pour savoir si le joueur peut aller au niveau suivant
  stage1=true;                                                           //
  stage2=false;                                                          //
  stage3=false;                                                          //
  stage4=false;                                                          //
  stage5=false;                                                          //
  End=false;                                                             //
  Title=loadImage("Title.png");                                          //Images charg\u00e9es
  Sans1=loadImage("Sans1.png");                                          //(Les charger dans le draw ralenti le programme)
  Sans2=loadImage("Sans2.png");                                          //
  Sans3=loadImage("Sans3.png");                                          //
  Face=loadImage("Face.png");                                            //
  Heart=loadImage("Heart.png");                                          //
  CSansMS=loadFont("ComicSansMS-48.vlw");                                //
}


public void draw() {
  if (jeu=true) {

    animation();
    if (stage1) stage1();                                                //Tests boolean pour les niveaux
    if (stage2) stage2();                                                //
    if (stage3) stage3();                                                //
    if (stage4) stage4();                                                //
    if (stage5) stage5();                                                //
    if (End) End();                                                      //

    image(animation[index], x, y);                                       //Personnage du joueur
    deplacement(); 

    if (downKeys['d']) {                                                 //Si d appuy\u00e9, d\u00e9placement \u00e0 droite
      dX=5;                                                              //
    }
    if (downKeys['q']) {                                                 //Si q appuy\u00e9, d\u00e9placement \u00e0 droite
      dX=-5;                                                             //
    }
    if (!downKeys['d'] && !downKeys['q']) dX=0;                          //Si les deux touches appuy\u00e9s, pas de d\u00e9placement


    if (Stage[(x+35)/50][(y+60)/50]==10 || Stage[(x)/50][(y+60)/50]==10 || Stage[(x+35+dX)/50][(y+59)/50]==13 || Stage[(x+dX)/50][(y+59)/50]==13) {  
      if (downKeys['z']) {                                               //Si perso sur le sol et z appuy\u00e9, saut
        dY=8;                                                            //
        dX=dX*2;                                                         //dX doubl\u00e9 pour donner une forme d'\u00e9lan au saut
      }
    } else {
      if (Stage[(x+35)/50][(y+85)/50]==11 || Stage[(x+35)/50][(y+85)/50]==12) {
        dY=-5;                                                           //Gravit\u00e9 en fonction du fichier .txt
      }
    }

    if (Stage[(x+35)/50][(y+59)/50]==10 || Stage[(x)/50][(y+59)/50]==10 || Stage[(x+35)/50][(y+59)/50]==13 || Stage[(x)/50][(y+59)/50]==13) {
      y=y-5;                                                             //Emp\u00eacher le personnage d'aller sous le sol
    }
  }
  textSize(20);
  fill(255);
  text("Try : "+Try, 1000, 100);                                         //Nombre d'essais
}

public void keyPressed() {
  if (key<256) {
    downKeys[key]=true;
  }
}

public void keyReleased() {
  if (key<256) {
    downKeys[key]=false;
  }
}

PImage Bones;

public void Bones(int xb, int yb) {                                //Place des os auc coords entr\u00e9es qui tuent le joueur
  Bones=loadImage("Bones.png");                             //
  if (x+20>=xb-5 && y+24>=yb && x<xb+50 ) {                 //
    Hit.play();                                             //Son
    image(Bones, xb, yb);                                   //
    gameOver();                                             //(A desactiver si difficult\u00e9s)
  }
}

public void End() {                                                                            //Dernier plateau
  if (End) {
    preplateau=loadStrings("End.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }

    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {
          fill(0, 0, 70);
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
        if (Stage[(x+35+dX)/50][(y+59)/50]==12) {
          if (Stage[i][j]==12) {
            fill(0, 0, 70);
            stroke(0, 0, 70);
            rect(i*50, j*50, 50, 50);
          }
        }
      }
    }
    Sans.pause();                                                                         //Arr\u00eat de la musique
    Megalovania.pause();                                                                  //

    if (x+dX>1150) dX=0;                                                                  //Emp\u00eache le personnage de sortir de l'\u00e9cran

    if (x>50 && x<=300) {                                                                 //Premier dialogue
      image(Sans1, 700, 300);                                                             //
      fill(255);                                                                          //
      textFont(CSansMS);                                                                  //
      textSize(15);                                                                       //
      text("It's a beautiful day outside.", 520, 275, 200, 200);                          //(Ces r\u00e9pliques viennent du personnage dans le jeu de base)  
      text("Birds are singing, flowers are blooming...", 520, 300, 200, 200);             //
      text("On days like these, kids like you...", 520, 350, 200, 200);                   //
    }
    if (x>300 && x<=500) {                                                                //Deuxi\u00e8me dialogue
      image(Sans2, 700, 300);                                                             //
      fill(255);                                                                          //
      textFont(CSansMS);                                                                  //  
      textSize(20);                                                                       //
      text("Should be burn in hell.", 520, 350, 200, 200);                                //
    }
    if (x>500 && y>=535) {                                                                //Fin du jeu
      dX=0;                                                                               //
      dY=0;                                                                               //
      for (int i=0; i<24; i++) {                                                          //
        image(Bones, 50*i, 560);                                                          //
      }                                                                                   //
      image(Sans3, 700, 300);                                                             //
      index=4;                                                                            //
      image(Heart, x+10, y+20);                                                           //
      Hit.play();                                                                         // 
      fill(0);                                                                            //
      stroke(255);                                                                        //
      rect(50, 200, 500, 300);                                                            //
      fill(0, 255, 255);                                                                  //
      noStroke();                                                                         //
      textSize(60);                                                                       //
      text("GAME OVER", 100, 300);                                                        //
      fill(255);                                                                          //
      textSize(40);                                                                       //
      text("geeettttttt dunked on!!!", 100, 400);                                         //
      fill(255);                                                                          //
      textSize(20);                                                                       //
      text("Press R to reset", 200, 450);                                                 //
      Face=loadImage("Face.png");                                                         //
      Face.loadPixels();                                                                  //
      image(Face, 430, 400, 100, 100);                                                    //


      if (downKeys['r']) {                                                                //r pour revivre
        Hit.pause();
        Hit.rewind();
        size(1200, 800);
        background(0, 0, 30);
        fill(0);
        rect(12, 12, 1175, 775);

        preplateau=loadStrings("Stage1.txt");
        for (int j=0; j<16; j++) {
          String tab[]=split(preplateau[j], " ");
          for (int i=0; i<24; i++) {
            Stage[i][j]=PApplet.parseInt(tab[i]);
          }
        }

        index=0;
        x=15;
        y=690;
        dX=0;
        dY=0;
        jeu=true;
        stage1=true; 
        stage2=false; 
        stage3=false; 
        stage4=false; 
        stage5=false;
        End=false;
        Try++;
        Sans.rewind();                                                                     //Lignes \u00e0 modifier si changement de musique
        Sans.play();                                                                       //
        //Megalovania.rewind();                                                              //
        //Megalovania.play();                                                                //
      }
    }
  }
}

PImage Frisk;                                                    //Animation du personnage
PImage[] animation;

public void animation() {
  Frisk=loadImage("Frisk.png");
  Frisk.loadPixels();
  animation=new PImage[5];                                        //L'index repr\u00e9sente un mouvement diff\u00e9rent
  animation[0]=Frisk.get(55, 130, 35, 59);                        //
  animation[1]=Frisk.get(51, 189, 37, 58);                        //
  animation[2]=Frisk.get(6, 4, 35, 60);                           //
  animation[3]=Frisk.get(6, 64, 35, 57);                          //
  animation[4]=Frisk.get(1, 1, 1, 1);                             //
}

public void deplacement() {                                                                            //Fonction de d\u00e9placement du personnage
  if ( dX==5 || dX==10) {                                                                       //Si il se d\u00e9place \u00e0 droite
    if (frameCount%6==0) {
      index++;
    }
    if (frameCount%2==0) {
      if (Stage[(x+35+dX)/50][(y+59)/50]!=10 && Stage[(x+35+dX)/50][(y+59)/50]!=12) {           //D\u00e9placement si il n'y a pas de mur
        x=x+dX;                                                                                 //Et si dX a une valeure positive
      }
    }
    if (index>1) index=0;                                                                       //Index de d\u00e9placement vers la droite
  }

  if ( dX==-5 || dX==-10) {                                                                     //Si il se d\u00e9place vers la gauche
    if (frameCount%6==0) {
      index++;
    }
    if (frameCount%2==0) {
      if (Stage[(x+dX)/50][(y+59)/50]!=10 && x>10) {                                            //D\u00e9pacement si il n'y a pas de mur
        x=x+dX;                                                                                 //Et si dX a une valeure n\u00e9gative
      }
    }

    if (index>3) index=2;                                                                       //Index de d\u00e9placement vers la gauche
  }
  y=y-dY;                                                                                       //Saut si dY a une valeur
}

public void gameOver() {                                        //Fonction de fin de jeu
  dX=0;                                                  //Bloque les mouvements
  dY=0;                                                  //
  jeu=false;                                             //
  index=4;                                               //Index transparent, supprime le personnage
  if (x<width/2) {                                       //Si perso dans la 1\u00e8re moiti\u00e9 de l'\u00e9cran
    fill(0);                                             //Texte de mort dans la 2\u00e8me moiti\u00e9
    stroke(255);
    rect(650, 200, 500, 300);
    fill(0, 255, 255);
    noStroke();
    textSize(60);
    text("GAME OVER", 700, 300);
    fill(255);
    textSize(40);
    text("Stay", 700, 400);
    fill(255, 0, 0);
    text("determined", 800, 400);
    fill(255);
    textSize(20);
    text("Press R to reset", 800, 450);
    image(Face, 1030, 320, 100, 100);
  }
  if (x>width/2) {                                        //Si perso dans la 2\u00e8me moiti\u00e9 de l'\u00e9cran
    fill(0);                                              //Texte de mort dans la 1\u00e8re moiti\u00e9
    stroke(255);
    rect(50, 200, 500, 300);
    fill(0, 255, 255);
    noStroke();
    textSize(60);
    text("GAME OVER", 100, 300);
    fill(255);
    textSize(40);
    text("Stay", 100, 400);
    fill(255, 0, 0);
    text("determined", 200, 400);
    fill(255);
    textSize(20);
    text("Press R to reset", 200, 450);
    image(Face, 430, 320, 100, 100);
  }
  image(Heart, x+10, y+20);                              //Image de coeur remplace le personnage (Rf\u00e9rence au jeu de base)






  if (downKeys['r']) {                                  //r pour revivre
    Hit.pause();                                        //Son r\u00e9initiallis\u00e9
    Hit.rewind();                                       //
    size(1200, 800);                                    //Setup
    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);

    preplateau=loadStrings("Stage1.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }
    index=0;
    x=15;
    y=690;
    dX=0;
    dY=0;
    jeu=true;
    stage1=true; 
    stage2=false; 
    stage3=false; 
    stage4=false; 
    stage5=false;
    End=false;
    Try++;
  }
}

public void stage1() {                                                                                                  //Premier niveau
  if (stage1) {                                                                                                  //Test boolean
    preplateau=loadStrings("Stage1.txt");                                                                        //Plateau \u00e0 partir d'un .txt
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }
    noStroke();
    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {                                                                                   //Si 10 sur le fichier,
          fill(0, 0, 70);                                                                                        //Bloc de 50x50
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
      }
    }
    image(Title, 250, 10, 700, 300);                                                                             //Le jeu de base
    image(Sans1, 700, 300);                                                                                      //Le boss du jeu
    fill(255);
    textFont(CSansMS);
    textSize(15);
    text("Hi, I'm Sans, Sans the skeleton.", 500, 250, 200, 200);
    text("I don't know what you do here but if you continu to this way...", 500, 300, 200, 200);
    text("Walk with Q and D, jump with Z. ", 30, 650);
    textSize(25);
    text("You're gonna have a bad time...", 400, 400, 300, 200);
    textSize(12);
    text("Watch out kid, look like there is something weird right here...", 400, 650, 200, 200);
    stroke(255);
    line(500, 680, 500, 720);
    line(500, 720, 490, 710);
    line(500, 720, 510, 710);

    strokeWeight(5);
    line(1000, 400, 1150, 400);
    line(1150, 400, 1100, 380);
    line(1150, 400, 1100, 420);

    Bones(545, 710);                                                                                             //Obstacle \u00e0 \u00e9viter
    Bones(400, 710);
    Bones(790, 660);

    if (x>1150) {                                                                                                //Si il arrive \u00e0 la fin du niveau
      x=15;                                                                                                      //Niveau suivant
      y=590;                                                                                                     //
      stage1=false;                                                                                              //
      stage2=true;                                                                                               //
    }
  }
}

public void stage2() {                                                  //Niveau 2
  if (stage2) {                                                  //Identique au 1
    preplateau=loadStrings("Stage2.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }

    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {
          fill(0, 0, 70);
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
      }
    }
    Bones(200, 560);
    Bones(350, 410);
    Bones(550, 210);
    Bones(720, 460);
    Bones(1000, 360);

    if (x>1150) {
      x=15;
      y=340;
      stage2=false;
      stage3=true;
    }
  }
}

public void stage3() {                                                  //Niveau 3
  if (stage3) {
    preplateau=loadStrings("Stage3.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }

    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {
          fill(0, 0, 70);
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
        if (Stage[(x+35+dX)/50][(y+59)/50]==12) {
          if (Stage[i][j]==12) {
            fill(0, 0, 70);
            stroke(0, 0, 70);
            rect(i*50, j*50, 50, 50);
          }
        }
      }
    }
    Bones(350, 260);
    Bones(650, 610);
    Bones(900, 560);
    if (y>700) gameOver();                                        //Si le joueur tombe, il meurt

    if (x>1150) {
      x=15;
      y=400;
      stage3=false;
      stage4=true;
    }
  }
}

public void stage4() {                                                        //Niveau 4
  if (stage4) {
    preplateau=loadStrings("Stage4.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }

    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {
          fill(0, 0, 70);
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
        if (Stage[(x+35+dX)/50][(y+59)/50]==12) {
          if (Stage[i][j]==12) {
            fill(0, 0, 70);
            stroke(0, 0, 70);
            rect(i*50, j*50, 50, 50);
          }
        }
      }
    }
    Bones(200, 460);
    Bones(400, 460);
    Bones(450, 510);
    Bones(550, 610);
    Bones(650, 610);
    Bones(800, 460);
    Bones(1000, 460);
    Bones(1100, 460);


    if (x>1150) {
      x=15;
      y=400;
      stage4=false;
      stage5=true;
    }
  }
}

public void stage5() {                                                  //Niveau 5
  if (stage5) {
    preplateau=loadStrings("Stage5.txt");
    for (int j=0; j<16; j++) {
      String tab[]=split(preplateau[j], " ");
      for (int i=0; i<24; i++) {
        Stage[i][j]=PApplet.parseInt(tab[i]);
      }
    }

    background(0, 0, 30);
    fill(0);
    rect(12, 12, 1175, 775);
    for (int i=0; i<24; i++) {
      for (int j=0; j<16; j++) {
        if (Stage[i][j]==10) {
          fill(0, 0, 70);
          stroke(0, 0, 70);
          rect(i*50, j*50, 50, 50);
        }
        if (Stage[(x+35+dX)/50][(y+59)/50]==12) {
          if (Stage[i][j]==12) {
            fill(0, 0, 70);
            stroke(0, 0, 70);
            rect(i*50, j*50, 50, 50);
          }
        }
      }
    }
    Bones(100, 460);
    Bones(250, 560);
    Bones(700, 560);
    Bones(1000, 560);

    if (y>700) gameOver();                                        //Si le joueur tombe il meurt

    if (x>1150) {
      x=15;
      y=540;
      stage5=false;
      End=true;
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "UndertaleFanGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
