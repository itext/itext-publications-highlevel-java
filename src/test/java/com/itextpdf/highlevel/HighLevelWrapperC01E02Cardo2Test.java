package com.itextpdf.highlevel;

import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.RunnerSearchConfig;
import com.itextpdf.test.WrappedSamplesRunner;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


@Tag("SampleTest")
public class HighLevelWrapperC01E02Cardo2Test extends WrappedSamplesRunner {
    public static Collection<Object[]> data() {
        RunnerSearchConfig searchConfig = new RunnerSearchConfig();
        searchConfig.addClassToRunnerSearchPath("com.itextpdf.highlevel.chapter01.C01E02_Text_Paragraph_Cardo2");
        return generateTestsList(searchConfig);
    }

    @Timeout(unit = TimeUnit.MILLISECONDS, value = 60000)
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("data")
    public void test(RunnerParams data) throws Exception {
        this.sampleClassParams = data;
        runSamples();
    }

    @Override
    protected void comparePdf(String outPath, String dest, String cmp) throws Exception {
        for (int i = 1; i < 4; i++) {
            String currentDest = String.format(dest, i);
            String currentOutPath = String.format(outPath, i);
            String currentCmp = String.format(cmp, i);

            CompareTool compareTool = new CompareTool();
            addError(compareTool.compareByContent(currentDest, currentCmp, currentOutPath, "diff_"));
            addError(compareTool.compareDocumentInfo(currentDest, currentCmp));
        }
    }
}
