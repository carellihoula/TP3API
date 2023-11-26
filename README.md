# Questions/Réponses
  
### Lien Github ===> https://github.com/carellihoula/TP3API.git

#### Dépendances ajoutées

- **Spring Web (`spring-boot-starter-web`)** :
  Fournit les fonctionnalités de base pour développer des applications web avec Spring, notamment la gestion des requêtes HTTP,
  la création de contrôleurs, et la manipulation des vues.

- **Spring Data JPA (`spring-boot-starter-data-jpa`)** :
  Facilite l'intégration de la couche d'accès aux données en utilisant JPA (Java Persistence API) avec Spring. 
  Il simplifie l'interaction avec les bases de données relationnelles en permettant la création de requêtes en 
  utilisant des méthodes d'interface et en fournissant des fonctionnalités de mapping objet-relationnel (ORM).

- **Hibernate Core** :
  Hibernate est un framework ORM (Object-Relational Mapping) très utilisé avec JPA pour simplifier la persistance des
  données en Java. Il permet de mapper des objets Java aux tables d'une base de données relationnelle.

- **H2 Database Engine** :
  H2 est une base de données relationnelle en mémoire. Elle est souvent utilisée pour le développement et 
  les tests, car elle peut être exécutée en mode embarqué, ce qui facilite son utilisation dans des applications Spring.

- **Spring Boot DevTools (`spring-boot-devtools`)** :
  Fournit des outils de développement pratiques pour Spring Boot. Il offre des fonctionnalités telles que 
  le redémarrage automatique de l'application en cas de modification des fichiers sources, 
  la configuration automatique des paramètres de développement, et bien plus.

- **Thymeleaf (`spring-boot-starter-thymeleaf`)** :
  Un moteur de template serveur côté pour les applications web Java. Thymeleaf facilite l'intégration de modèles HTML 
  avec des applications Spring en permettant une gestion aisée des vues et des données affichées dans les pages web.


### 1.Avec quelle partie du code avons-nous paramétré l'url d'appel /greeting ?
nous avons paramétré l'url d'appel /greeting dans le controlleur au niveau de l'annotation
GetMapping("/greeting")
Cette annotation est placée au-dessus de la méthode greeting(), 
ce qui indique que cette méthode sera invoquée lorsque l'URL /greeting sera accédée via une requête HTTP GET.

### 2.Avec quelle partie du code avons-nous choisi le fichier HTML à afficher ?
Dans return "greeting" ===>
En effet, la methode greeting renvoie une chaîne "greeting". Cette chaîne correspond au nom du 
fichier HTML à afficher. En utilisant return "greeting";, le contrôleur indique à Spring Framework 
de rechercher un fichier HTML nommé "greeting" à afficher en réponse à la requête.

### 3.Comment envoyons-nous le nom à qui nous disons bonjour avec le second lien
Il est envoyé en utilisant le paramètre nameGET dans l'URL à travers l'annotation @RequestParam. 
Le nom est transmis en tant que paramètre de requête (Optionnel). 
Par exemple, si vous souhaitez dire bonjour à quelqu'un spécifique comme "Carel", 
vous pouvez utiliser le lien suivant : http://votre_domaine/greeting?nameGET=Carel. 
Cela enverra "Carel" en tant que paramètre dans la requête GET au contrôleur, qui l'ajoutera à l'attribut du
modèle (nomTemplate) pour l'afficher dans la page HTML.

### Explication Etapes 17 && 18 
## Utilisation des annotations Hibernate pour la persistance des données
L'apparition de la nouvelle table s'explique par la classe `Address` qui illustre l'utilisation des annotations 
Hibernate pour mapper une entité Java à une table dans une base de données.

Ci-dessous une brève explication de ces annotations :
# a)Annotations utilisées dans la classe Address :

- `@Entity`: Indique à Hibernate que cette classe est une entité, à mapper à une table dans la base de données.

- `@Table(name = "address")`: Spécifie le nom de la table correspondante dans la base de données pour cette entité.

- `@Id`: Définit la propriété suivante comme l'identifiant de l'entité.

- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Précise la stratégie de génération des valeurs pour la 
clé primaire. Ici, `GenerationType.IDENTITY` indique que la base de données générera automatiquement les valeurs.

# b)Intégration avec Spring et Hibernate :
Pour que ces annotations fonctionnent correctement et que Hibernate puisse faire le mapping entre 
les entités et les tables, j'ai inclus la dependance hibernate dans pom.xml

### Explication Etape 20 (Voyez-vous tout le contenu de data.sql ?)
oui nous voyons le contenu deimport.sql dans notre base de données.
Au démarrage de l'application, Spring identifie automatiquement le fichierimport.sql dans les ressources. 
Les requêtes SQL qu'il contient sont exécutées pour alimenter la base de données. 
Ainsi, après le redémarrage, les données insérées via ces requêtes apparaissent dans la base de données

### A quoi sert l'annotation @Autowired
L'annotation @Autowired est utilisée en Spring pour réaliser l'injection de dépendances. 
En bref, elle permet à Spring de résoudre automatiquement et d'injecter les dépendances nécessaires dans les classes.
En l'occurrence, j'ai utilisé l'injection par champ avec l'annotation @Autowired
il existe trois moyens de faire une injection de dependance. 
 - `Injection par constructeur`
 - `Injection par methode`
 - `Injection par champ`

### Méthode que vous avez utilisé pour ajouter Bootstrap dans le README
J'ai ajouté la dépendance à Bootstrap dans le fichier pom.xml pour gérer les ressources externes. 
Ensuite, j'ai inséré les balises <link> pour les styles CSS et <script> pour les fonctionnalités JavaScript dans 
`navbar.html`, car il est inclus dans les deux pages (`greeting.html` et `addresses.html`). 

    - `<link href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">` ==>CSS
    - `<script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>` ===> JavaScript

J'ai récupéré bootstrap dans maven repository, ensuite, je l'ai ajouté dans pom.xml

### Lien Github ===> https://github.com/carellihoula/TP3API.git
### Lien Github ===> https://github.com/carellihoula/TP3API.git
