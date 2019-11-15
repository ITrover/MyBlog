package com.mayh.blog.utils;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class MarkdownUtil {
    public static String multiMarkdown(String markdown) {
        MutableDataHolder options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MULTI_MARKDOWN);

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(markdown);
        String render = renderer.render(document);// "<p>This is <em>Sparta</em></p>\n"
        return render;
    }
}
