import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Clase BinaryTree para representar un árbol binario
class BinaryTree<E extends Comparable<E>> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        public Node(E data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node<E> root;

    public BinaryTree() {
        root = null;
    }

    // Método para insertar un elemento en el árbol
    public void insert(E data) {
        root = insertRec(root, data);
    }

    private Node<E> insertRec(Node<E> root, E data) {
        if (root == null) {
            root = new Node<>(data);
            return root;
        }

        if (data.compareTo(root.data) < 0)
            root.left = insertRec(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = insertRec(root.right, data);

        return root;
    }

    // Método para recorrer el árbol en orden y mostrar las palabras
    public void inOrderTraversal() {
        inOrderTraversalRec(root);
    }

    private void inOrderTraversalRec(Node<E> root) {
        if (root != null) {
            inOrderTraversalRec(root.left);
            System.out.print(root.data + " ");
            inOrderTraversalRec(root.right);
        }
    }

    // Método para buscar una palabra en el árbol
    public boolean search(E data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node<E> root, E data) {
        if (root == null)
            return false;

        if (root.data.compareTo(data) == 0)
            return true;

        if (data.compareTo(root.data) < 0)
            return searchRec(root.left, data);
        else
            return searchRec(root.right, data);
    }
}

// Clase Association para representar las asociaciones del diccionario
class Association<K extends Comparable<K>, V> {
    private K key;
    private V value;

    public Association(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class DictionaryProgram {
    private static BinaryTree<String> englishTree = new BinaryTree<>();
    private static BinaryTree<String> spanishTree = new BinaryTree<>();
    private static BinaryTree<String> frenchTree = new BinaryTree<>();
    private static Map<String, String> languageMap = new HashMap<>();

    public static void main(String[] args) {
        loadDictionary("diccionario.txt");
        displayDictionary();
        translateText("texto.txt");
    }

    // Método para cargar el diccionario desde un archivo
    private static void loadDictionary(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String englishWord = parts[0].trim().toLowerCase();
                    String spanishWord = parts[1].trim().toLowerCase();
                    String frenchWord = parts[2].trim().toLowerCase();

                    englishTree.insert(englishWord);
                    spanishTree.insert(spanishWord);
                    frenchTree.insert(frenchWord);

                    // Construir mapa de palabras
                    languageMap.put(englishWord, "inglés");
                    languageMap.put(spanishWord, "español");
                    languageMap.put(frenchWord, "francés");
                } else {
                    System.out.println("Error en el formato de línea: " + line);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    // Método para mostrar las palabras en orden para cada idioma
    private static void displayDictionary() {
        System.out.println("Palabras en inglés:");
        englishTree.inOrderTraversal();
        System.out.println();

        System.out.println("\nPalabras en español:");
        spanishTree.inOrderTraversal();
        System.out.println();

        System.out.println("\nPalabras en francés:");
        frenchTree.inOrderTraversal();
        System.out.println();
    }

    // Método para traducir un texto a los tres idiomas del diccionario
    private static void translateText(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Procesar la oración palabra por palabra y traducir al español
                translateAndPrint(line, "español");

                // Procesar la oración palabra por palabra y traducir al inglés
                translateAndPrint(line, "inglés");

                // Procesar la oración palabra por palabra y traducir al francés
                translateAndPrint(line, "francés");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    // Método para traducir una oración a un idioma específico
    private static void translateAndPrint(String sentence, String targetLanguage) {
        StringBuilder translatedSentence = new StringBuilder();

        String[] words = sentence.split("\\s+");

        for (String word : words) {
            String originalWord = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
            String translatedWord = translateWord(originalWord, targetLanguage);

            if (translatedWord != null) {
                translatedSentence.append(translatedWord).append(" ");
            } else {
                translatedSentence.append("*").append(word).append("* ").append(" ");
            }
        }

        System.out.println("Oración en " + targetLanguage + ": " + translatedSentence.toString().trim());
    }

    // Método para traducir una palabra a un idioma específico
    private static String translateWord(String word, String targetLanguage) {
        if (targetLanguage.equals("inglés")) {
            return word;
        } else if (targetLanguage.equals("español")) {
            return languageMap.getOrDefault(word, "*");
        } else if (targetLanguage.equals("francés")) {
            return languageMap.getOrDefault(word, "*");
        }

        return null;
    }
}
