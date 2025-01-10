// Un fichier JavaScript d'exemple avec des problèmes de qualité

// Une variable globale (mauvaise pratique)
globalVariable = "Hello, SonarQube!";

// Une fonction avec un problème potentiel : pas de retour explicite
function sayHello(name) {
    console.log("Hello " + name); // Concaténation au lieu de template literals
}

// Une fonction inutilement complexe
function calculateSum(a, b, c) {
    if (a && b && c) {
        return a + b + c;
    } else if (a && b) {
        return a + b;
    } else if (a) {
        return a;
    }
    return 0;
}

// Une boucle inutile
for (var i = 0; i < 10; i++) {
    console.log(i); // Pas utilisé après
}

// Fonction non utilisée
function unusedFunction() {
    console.log("Je ne suis jamais appelée.");
}

// Appeler une fonction avec un paramètre manquant
sayHello();
