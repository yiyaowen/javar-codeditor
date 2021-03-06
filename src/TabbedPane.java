package com.yiyaowen.javar;

import com.yiyaowen.javar.JavarConstants;
import com.yiyaowen.javar.JavarTranslator;
import com.yiyaowen.javar.CodePane;
import com.yiyaowen.javar.Javar;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class TabbedPane extends JTabbedPane
{
    //////////////
    // Property //
    //////////////

    public static JTextArea outputTextArea = new JTextArea();
    public static JTextArea debugTextArea = new JTextArea();
    public static JLabel previewLabel = new JLabel();

    /////////////////
    // Constructor //
    /////////////////

    public TabbedPane(int type)
    {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        switch (type)
        {
            case JavarConstants.CodePane:
                initTabbedCodePane();
                break;
            case JavarConstants.OutputArea:
                initTabbedOutputArea();
                break;
        }
    }

    ////////////
    // Method //
    ////////////

    /**
     * Initialize tabbed output area
     *
     * @param
     * @return
     */
    public void initTabbedOutputArea()
    {
        outputTextArea.setEditable(false);
        debugTextArea.setEditable(false);
        Font font1 = outputTextArea.getFont();
        outputTextArea.setFont(new Font(font1.getName(), font1.getStyle(), 14));
        Font font2 = debugTextArea.getFont();
        debugTextArea.setFont(new Font(font2.getName(), font2.getStyle(), 14));
        previewLabel.setText(JavarTranslator.translate(JavarConstants.mainWindowPrefix, JavarConstants.previewLabelContent));
        previewLabel.setVerticalAlignment(SwingConstants.TOP);
        this.addTab(JavarTranslator.translate(JavarConstants.mainWindowPrefix, "Output"), new JScrollPane(outputTextArea));
        this.addTab(JavarTranslator.translate(JavarConstants.mainWindowPrefix, "Debug"), new JScrollPane(debugTextArea));
        this.addTab(JavarTranslator.translate(JavarConstants.mainWindowPrefix, "Preview"), new JScrollPane(previewLabel));
    }

    /**
     * Initialize tabbed code pane
     *
     * @param
     * @return
     */
    public void initTabbedCodePane()
    {
        JLabel navigatorLabel = new JLabel();
        navigatorLabel.setText(JavarTranslator.translate(JavarConstants.mainWindowPrefix, JavarConstants.navigatorLabelContent));
        navigatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navigatorLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.addTab(JavarTranslator.translate(JavarConstants.mainWindowPrefix, JavarConstants.navigatorLabelName), navigatorLabel);
        this.addChangeListener(e -> {
            var index = this.getSelectedIndex() - 1;
            if (index < 0)
            {
                Javar.fileList.clearSelection();
                return;
            }
            if (index >= Javar.fileList.getModel().getSize())
                index = Javar.fileList.getModel().getSize() - 1;
            Javar.fileList.setSelectedIndex(index);
        });
    }
}
