package com.itextpdf.highlevel;

import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.RunnerSearchConfig;
import com.itextpdf.test.WrappedSamplesRunner;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Collection;

@Tag("SampleTest")
public class HighLevelWrapperTest extends WrappedSamplesRunner {

    public static Collection<Object[]> data() {
        RunnerSearchConfig searchConfig = new RunnerSearchConfig();
        searchConfig.addPackageToRunnerSearchPath("com.itextpdf.highlevel");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.chapter07.C07E14_Encrypted");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.chapter01.C01E02_Text_Paragraph_Cardo2");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.HighLevelWrapperTest");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.HighLevelWrapperWithEncryptionTest");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.HighLevelWrapperC01E02Cardo2Test");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.notused");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.util");
        searchConfig.ignorePackageOrClass("com.itextpdf.highlevel.chapter05.AlternatingBackgroundTableRenderer");
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
        CompareTool compareTool = new CompareTool();
        addError(compareTool.compareByContent(dest, cmp, outPath, "diff_"));
        addError(compareTool.compareDocumentInfo(dest, cmp));
    }
}
