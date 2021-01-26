package util;

import com.javaops.webapp.model.AbstractSection;
import com.javaops.webapp.model.Resume;
import com.javaops.webapp.model.TextSection;
import com.javaops.webapp.util.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import static com.javaops.webapp.ResumeTestData.R1;

public class JsonParserTest {
    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(R1, resume);
    }

    @Test
    public void write() throws Exception {
        AbstractSection section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}
