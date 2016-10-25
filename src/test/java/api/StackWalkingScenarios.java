package api;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

class StackWalkingScenarios {

    private static String simpleStack;

    @Test
    public void shouldGetSimpleStackTraceByPackageName(){
        //When
        first();

        //Then
        then(simpleStack)
            .hasLineCount(4)
            .containsSequence("Line: 38 Method name: third")
            .containsSequence("Line: 32 Method name: second")
            .containsSequence("Line: 28 Method name: first")
            .containsSequence("Line: 16 Method name: shouldGetSimpleStackTraceByPackageName");
    }

    private static void first() {
        second();
    }

    private static void second() {
        third();
    }

    private static void third() {
        simpleStack = StackWalker
            .getInstance()
            .walk(StackWalkingScenarios::walk);
    }

    private static String walk(Stream<StackWalker.StackFrame> stackFrameStream) {
        String PACKAGE_NAME = "api";
        String NEW_LINE = System.getProperty("line.separator");

        return stackFrameStream
            .filter(frame -> frame.getClassName().contains(PACKAGE_NAME))
            .map(frame -> "Line: " + frame.getLineNumber() + " Method name: " + frame.getMethodName())
            .collect(joining(NEW_LINE));
    }
}
