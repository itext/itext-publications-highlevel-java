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

import java.util.Collection;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.Parameterized;

@Category(SampleTest.class)
public class HighLevelWrapperC01E02Cardo2Test extends WrappedSamplesRunner {
    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        RunnerSearchConfig searchConfig = new RunnerSearchConfig();
        searchConfig.addClassToRunnerSearchPath("com.itextpdf.highlevel.chapter01.C01E02_Text_Paragraph_Cardo2");
        return generateTestsList(searchConfig);
    }

    @Test(timeout = 60000)
    public void test() throws Exception {
        runSamples();
    }

    @Override
    protected void comparePdf(String outPath, String dest, String cmp) throws Exception {
        for (int i = 0; i < 3; i++) {
            String currentDest = String.format(dest, ++i);
            String currentOutPath = String.format(outPath, ++i);
            String currentCmp = String.format(cmp, ++i);

            CompareTool compareTool = new CompareTool();
            addError(compareTool.compareByContent(currentDest, currentCmp, currentOutPath, "diff_"));
            addError(compareTool.compareDocumentInfo(currentDest, currentCmp));
        }
    }
}
