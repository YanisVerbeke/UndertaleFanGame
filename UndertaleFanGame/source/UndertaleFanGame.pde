//Personnages et élements du jeu sont inspirés du jeu Undertale

import ddf.minim.*;                                                       //Musique
Minim minim;                                                              //
AudioPlayer Sans;                                                         //
AudioPlayer Megalovania;                                                  //
AudioPlayer Hit;                                                          //Sfx
int x, y, dX, dY, Try;                                                    //Coord joueur, déplacement, nb d'essais
int index;                                                                //Animation du perso
boolean[] downKeys=new boolean[256];                                      //Detection des touches du clavier
int[][] Stage=new int[25][26];                                            //Plateau
String preplateau[];                                                      //
boolean jeu, stage1, stage2, stage3, stage4, stage5, End;                 //Niveaux du jeu
PImage Title, Sans1, Sans2, Sans3, Heart, Face;                           //Images du boss et des morts
PFont CSansMS;                                                            //Police des textes du boss

void setup() {
  size(1200, 800);
  background(0, 0, 30);
  noStroke();
  fill(0);
  rect(12, 12, 1175, 775);
  minim = new Minim(this);                                                //Musique
  Sans = minim.loadFile("Sans.mp3");                                      //
  Megalovania = minim.loadFile("Megalovania.mp3");                        //
  Hit = minim.loadFile("Hit.mp3");                                        //

  Sans.play();                                                            //Musiques à choisir
  Sans.loop();                                                            //Il faut également modifier la fin du End() si on change de musique
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
  Title=loadImage("Title.png");                                          //Images chargées
  Sans1=loadImage("Sans1.png");                                          //(Les charger dans le draw ralenti le programme)
  Sans2=loadImage("Sans2.png");                                          //
  Sans3=loadImage("Sans3.png");                                          //
  Face=loadImage("Face.png");                                            //
  Heart=loadImage("Heart.png");                                          //
  CSansMS=loadFont("ComicSansMS-48.vlw");                                //
}


void draw() {
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

    if (downKeys['d']) {                                                 //Si d appuyé, déplacement à droite
      dX=5;                                                              //
    }
    if (downKeys['q']) {                                                 //Si q appuyé, déplacement à droite
      dX=-5;                                                             //
    }
    if (!downKeys['d'] && !downKeys['q']) dX=0;                          //Si les deux touches appuyés, pas de déplacement


    if (Stage[(x+35)/50][(y+60)/50]==10 || Stage[(x)/50][(y+60)/50]==10 || Stage[(x+35+dX)/50][(y+59)/50]==13 || Stage[(x+dX)/50][(y+59)/50]==13) {  
      if (downKeys['z']) {                                               //Si perso sur le sol et z appuyé, saut
        dY=8;                                                            //
        dX=dX*2;                                                         //dX doublé pour donner une forme d'élan au saut
      }
    } else {
      if (Stage[(x+35)/50][(y+85)/50]==11 || Stage[(x+35)/50][(y+85)/50]==12) {
        dY=-5;                                                           //Gravité en fonction du fichier .txt
      }
    }

    if (Stage[(x+35)/50][(y+59)/50]==10 || Stage[(x)/50][(y+59)/50]==10 || Stage[(x+35)/50][(y+59)/50]==13 || Stage[(x)/50][(y+59)/50]==13) {
      y=y-5;                                                             //Empêcher le personnage d'aller sous le sol
    }
  }
  textSize(20);
  fill(255);
  text("Try : "+Try, 1000, 100);                                         //Nombre d'essais
}

void keyPressed() {
  if (key<256) {
    downKeys[key]=true;
  }
}

void keyReleased() {
  if (key<256) {
    downKeys[key]=false;
  }
}

