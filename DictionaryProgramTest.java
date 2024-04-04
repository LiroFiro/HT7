import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class DictionaryProgramTest {

    @Test
    public void testLoadDictionary() {
        // Crear una instancia del programa
        DictionaryProgram program = new DictionaryProgram();
        
        // Intentar cargar el diccionario desde un archivo existente
        program.loadDictionary("diccionario.txt");
        
        // Verificar que los árboles no están vacíos
        assertFalse(program.englishTree.isEmpty());
        assertFalse(program.spanishTree.isEmpty());
        assertFalse(program.frenchTree.isEmpty());
    }

    @Test
    public void testSearchWord() {
        // Crear una instancia del programa
        DictionaryProgram program = new DictionaryProgram();
        
        // Cargar el diccionario
        program.loadDictionary("diccionario.txt");
        
        // Verificar que una palabra existente se puede encontrar en el diccionario
        assertTrue(program.searchWord("hello"));
        
        // Verificar que una palabra inexistente no se puede encontrar en el diccionario
        assertFalse(program.searchWord("nonexistent"));
    }

    @Test
    public void testTranslateText() {
        // Crear una instancia del programa
        DictionaryProgram program = new DictionaryProgram();
        
        // Cargar el diccionario
        program.loadDictionary("diccionario.txt");
        
        // Crear un archivo de texto de prueba con una oración
        File file = new File("texto.txt");
        assertTrue(file.exists());
        
        // Traducir el texto del archivo a diferentes idiomas
        program.translateText("texto.txt");
        
        // No hay excepciones durante la traducción
        assertTrue(true);
    }
}
