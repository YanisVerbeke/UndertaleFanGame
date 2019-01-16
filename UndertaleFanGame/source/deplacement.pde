void deplacement() {                                                                            //Fonction de déplacement du personnage
  if ( dX==5 || dX==10) {                                                                       //Si il se déplace à droite
    if (frameCount%6==0) {
      index++;
    }
    if (frameCount%2==0) {
      if (Stage[(x+35+dX)/50][(y+59)/50]!=10 && Stage[(x+35+dX)/50][(y+59)/50]!=12) {           //Déplacement si il n'y a pas de mur
        x=x+dX;                                                                                 //Et si dX a une valeure positive
      }
    }
    if (index>1) index=0;                                                                       //Index de déplacement vers la droite
  }

  if ( dX==-5 || dX==-10) {                                                                     //Si il se déplace vers la gauche
    if (frameCount%6==0) {
      index++;
    }
    if (frameCount%2==0) {
      if (Stage[(x+dX)/50][(y+59)/50]!=10 && x>10) {                                            //Dépacement si il n'y a pas de mur
        x=x+dX;                                                                                 //Et si dX a une valeure négative
      }
    }

    if (index>3) index=2;                                                                       //Index de déplacement vers la gauche
  }
  y=y-dY;                                                                                       //Saut si dY a une valeur
}

