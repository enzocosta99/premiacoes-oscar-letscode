
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraArquivo {

    public List<Premiacoes> premiacoesList;

    public LeituraArquivo(String fileName) {
        this.premiacoesList = lerArquivo(fileName);
    }

    private List<Premiacoes> lerArquivo(String filepath) {
        try (Stream<String> fileLines = Files.lines(Path.of(filepath))) {
            return fileLines
            		.skip(1)
                    .map(Premiacoes::of)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Premiacoes> getPremiacoesList() {
        return premiacoesList;
    }
}
