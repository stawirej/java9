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
import java.util.concurrent.CompletableFuture;

class OSProcessesScenarios {

    @Test
    public void shouldListFolderOnStandardOutput() throws IOException {
        //Given
        ProcessBuilder dir = new ProcessBuilder();

        //Then
        final Process listFolderProcess = dir
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
    public void shouldListFoldersWithGrepping() throws IOException {
        //Given
        ProcessBuilder ls = new ProcessBuilder()
            .command("ls")
            .directory(Paths.get("/home/nipa/tmp").toFile());

        ProcessBuilder grepPdf = new ProcessBuilder()
            .command("grep", "pdf")
            .redirectOutput(ProcessBuilder.Redirect.INHERIT);

        List<Process> lsThenGrep = ProcessBuilder
            .startPipeline(asList(ls, grepPdf));

        //When
        CompletableFuture[] lsThenGrepFutures = lsThenGrep.stream()
            // onExit returns a CompletableFuture<Process>
            .map(Process::onExit)
            .map(processFuture -> processFuture.thenAccept(process -> System.out.println("PID: " + process.getPid())))
            .toArray(CompletableFuture[]::new);

        // wait until all processes are finished
        CompletableFuture
            .allOf(lsThenGrepFutures)
            .join();
    }

    @Disabled
    @Test
    public void shouldListFoldersWithGreppingOnWindows(){
        //Given
        //dir /s/ b | findstr /r /c:".pdf"

        //When

        //Then
    }
}
