PImage Frisk;                                                    //Animation du personnage
PImage[] animation;

void animation() {
  Frisk=loadImage("Frisk.png");
  Frisk.loadPixels();
  animation=new PImage[5];                                        //L'index représente un mouvement différent
  animation[0]=Frisk.get(55, 130, 35, 59);                        //
  animation[1]=Frisk.get(51, 189, 37, 58);                        //
  animation[2]=Frisk.get(6, 4, 35, 60);                           //
  animation[3]=Frisk.get(6, 64, 35, 57);                          //
  animation[4]=Frisk.get(1, 1, 1, 1);                             //
}

