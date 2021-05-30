# Nemesis - Projet Android - Jérémy Rodrigues - ESIEA 3A CFA

Projet d'Android Esiea 3A CFA - Application utilisant l'API REST lié au site de Myanimelist (https://myanimelist.net/) nommé JikanAPI.

Fonctionnalitées utilisées :
  - Liste d'éléments via recyclerview
  - Détail d'un élément
  - API REST JikanAPI (API REST qui reçoit nos requêtes)
  - Firebase
    - FirebaseAuth (Authentification via Google)
    - Firestore (Stockage de données en ligne)
  - Singletons
  - 
  

## Fonctionnement

L'utilisateur accède à une page de login lui demandant de se connecter avec un compte google.

![login_screen](https://user-images.githubusercontent.com/66209329/120110636-8fea6a80-c16e-11eb-886f-5e018e387652.PNG)

Si l'utilisateur ne s'est jamais connecté auparavant, celui va être redirigé vers l'interface de connexion de google. L'application Némésis va récupérer l'adresse mail et généré un UID unique si l'authentification via l'interface de google est réussi.
Le compte utilisateur est alors créé sur FirebaseAuth 

![FirebaseAuth](https://user-images.githubusercontent.com/66209329/120110741-130bc080-c16f-11eb-971d-f5aa2566810a.PNG)

Si le compte utilisateur est déjà créé alors l'interface google n'est pas appelé et l'utilisateur accède directement au home screen lorsqu'il clique sur le bouton "Sign In".

![home_screen](https://user-images.githubusercontent.com/66209329/120111914-063d9b80-c174-11eb-9a4b-3ad69e2044a1.PNG)

Une fois l'utilisateur sur le home screen, divers choix s'offrent à lui :
  - Accéder à la liste d'anime qui sort actuellement "Airing Now".
  
  ![airing_now](https://user-images.githubusercontent.com/66209329/120110763-346cac80-c16f-11eb-87df-8843b047b5c0.PNG)
  
  - Accéder à la liste d'anime qui ont été annoncé "Upcoming".
  
  ![upcoming](https://user-images.githubusercontent.com/66209329/120110822-7a297500-c16f-11eb-8bf4-471bd0243d9b.PNG)
  
  - Choisir d'afficher une liste d'anime en fonction de l'année et de la saison choisi dans les spinners par l'utilisateur.
  
  ![search_part](https://user-images.githubusercontent.com/66209329/120110835-89102780-c16f-11eb-9d04-87356eaccbc3.PNG)
  
  Par exemple pour cette recherche, l'utilisateur tombera sur la liste d'anime ci-dessous :
  
  ![search_list](https://user-images.githubusercontent.com/66209329/120110850-a0e7ab80-c16f-11eb-90ac-8516ffc6e5b5.PNG)
  
  - Accéder à sa Watchlist personnelle et la gérer.
  
  Il peut y accéder à partir du home screen en cliquant sur le bouton "My Watchlist" ![watchlist_button](https://user-images.githubusercontent.com/66209329/120110890-d1c7e080-c16f-11eb-87fa-9537099cfde6.PNG)
 qui va le rediriger vers la liste d'anime ajouter à sa watchlist qui ont été stocké dans la base de données FireStore. Les données sont lié à l'UID unique de l'utilisateur authentifié
 Par exemple pour ma watchlist, mon programme va get toutes les informations stocké dans la collection lié à mon UID et les affichés sous forme d'une liste d'anime.
 
  Représentation de ma Watchlist sur Firestore :
  
  ![FireStore](https://user-images.githubusercontent.com/66209329/120111015-6df1e780-c170-11eb-823d-0407f0585ec3.PNG)
  
  Représentation de ma Watchlist en tant que liste d'anime :
  
  ![Watchlist_représentation](https://user-images.githubusercontent.com/66209329/120111084-c88b4380-c170-11eb-80c5-c5efe5e4a460.PNG)
  
 Les listes d'anime s'affichent toujours à l'aide d'une recyclerview.
 L'utilisateur peut cliquer sur n'importe quel anime d'une liste afin d'en voir les détails et pouvoir l'ajouter/supprimer de sa watchlist personnelle.
 
 Clique de l'utilisateur sur l'anime Ijiranaide, Nagatoro-san, l'utilisateur arrive sur la page de détail de l'anime: 
 
 ![add_detail](https://user-images.githubusercontent.com/66209329/120111221-641cb400-c171-11eb-9f3d-64eeb2f51fbd.PNG)
 
 Lorsque l'utilisateur accède aux détails d'un anime, le programme va faire un check-up de la Watchlist de l'utilisateur pour savoir s'il doit lui proposer de l'ajouter ou de le supprimer de sa Watchlist.
 
 Cas n°1 : l'utilisateur a déjà ajouté l'anime dans sa watchlist, le programme lui propose donc de le supprimer :
 
 ![detail_delete](https://user-images.githubusercontent.com/66209329/120111359-e9a06400-c171-11eb-9fa1-b04a64c17010.PNG)
 
 Cas n°2: l'utilisateur n'a pas l'anime dans sa watchlist, le programme lui propose donc de l'ajouter :
 
 ![add_detail](https://user-images.githubusercontent.com/66209329/120111384-0b015000-c172-11eb-8a79-ce8f1a16a499.PNG)
 
 De plus si l'utilisateur supprime directement l'anime depuis sa Watchlist, celle-ci se met à jour automatiquement :
 
 Supprimons l'anime nommé Hakujaden depuis ma Watchlist.
 
 Avant suppression :
 
 List :
 
 ![hakujaden_inlist](https://user-images.githubusercontent.com/66209329/120111733-6253f000-c173-11eb-8a21-1c7b1a380683.PNG)
 
 FirestoreData :
 
 ![hakujaden_inlistDB](https://user-images.githubusercontent.com/66209329/120111736-697afe00-c173-11eb-893b-3c32254ec23c.PNG)
 
 Après suppression :
 
 List :
 
 ![hakujaden_deleted](https://user-images.githubusercontent.com/66209329/120111758-7f88be80-c173-11eb-86ca-eb8a3c546793.PNG)
 
 FirestoreData :
  
 ![hakujaden_deletedDB](https://user-images.githubusercontent.com/66209329/120111763-84e60900-c173-11eb-802c-ec8508014a8b.PNG)
 
 
 ## Téléchargement
 
 L'application est disponible en téléchargement depuis le lien GoogleDrive suivant :
 
 https://drive.google.com/file/d/1bMqzTSu9x3auoWIte2PBBoW1lLJDnzDF/view?usp=sharing
 
 
