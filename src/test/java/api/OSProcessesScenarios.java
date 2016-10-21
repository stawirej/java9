package api;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;

class OSProcessesScenarios {

    @Test
    public void shouldListFolderOnStandardOutput() throws IOException {
        //Given
        ProcessBuilder dir = new ProcessBuilder();

        //When
        dir
            .command("cmd.exe", "/C dir & echo example of & echo folder listing")
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .directory(Paths.get("d:\\").toFile())
            .start();
    }


    @Test
    public void shouldListFolderAndReadOutput() throws IOException {
        //Given
        ProcessBuilder dir = new ProcessBuilder();

        //When
        final Process listFolderProcess = dir
            .command("cmd.exe", "/C dir & echo example of & echo folder listing")
            .directory(Paths.get("d:\\").toFile())
            .start();

        //Then
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(listFolderProcess.getInputStream()))) {
            final List<String> listing = reader.lines().collect(toList());
            then(listing.size()).isGreaterThan(1);
        }
    }

    @Disabled
    @Test
    public void shouldListFoldersWithGreppingOnLinux() throws IOException {
        //Given
        ProcessBuilder ls = new ProcessBuilder()
            .command("ls")
            .directory(Paths.get("/tmp/books").toFile());

        ProcessBuilder grepPdf = new ProcessBuilder()
            .command("grep", "pdf")
            .redirectOutput(ProcessBuilder.Redirect.INHERIT);

        List<Process> lsThenGrep = ProcessBuilder.startPipeline(asList(ls, grepPdf));

        //When
        List<Process> processPipeline = ProcessBuilder.startPipeline(asList(ls, grepPdf));

        //Then
        final List<String> pdfFiles = getFiles(processPipeline);
        //pdfFiles.stream().forEach(System.out::println);
        then(pdfFiles.size()).isGreaterThan(10);
    }

    @Test
    public void shouldListFoldersWithGreppingOnWindows() throws IOException, ExecutionException, InterruptedException {
        //Given
        ProcessBuilder listFolder = new ProcessBuilder()
            .command("cmd.exe", "/C dir /s /b & echo")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .directory(Paths.get("d:\\!Data\\Books").toFile());

        ProcessBuilder findPdf = new ProcessBuilder()
            .command("cmd.exe", "/C findstr /r /c:\".pdf\" & echo");

        //When
        List<Process> processPipeline = ProcessBuilder.startPipeline(asList(listFolder, findPdf));

        //Then
        final List<String> pdfFiles = getFiles(processPipeline);
        //pdfFiles.stream().forEach(System.out::println);
        then(pdfFiles.size()).isGreaterThan(10);
    }

    private List<String> getFiles(final List<Process> processPipeline) throws IOException {

        Process lastProcess = processPipeline.get(processPipeline.size() - 1);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(lastProcess.getInputStream()))) {
            return reader.lines().collect(toList());
        }
    }
}
