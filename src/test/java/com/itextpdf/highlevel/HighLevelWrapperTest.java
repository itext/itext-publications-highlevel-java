/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2022 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.highlevel;

import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.RunnerSearchConfig;
import com.itextpdf.test.WrappedSamplesRunner;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.Parameterized;

import java.util.Collection;

@Category(SampleTest.class)
public class HighLevelWrapperTest extends WrappedSamplesRunner {

    @Parameterized.Parameters(name = "{index}: {0}")
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

    @Test(timeout = 60000)
    public void test() throws Exception {
        runSamples();
    }

    @Override
    protected void comparePdf(String outPath, String dest, String cmp) throws Exception {
        CompareTool compareTool = new CompareTool();
        addError(compareTool.compareByContent(dest, cmp, outPath, "diff_"));
        addError(compareTool.compareDocumentInfo(dest, cmp));
    }
}
