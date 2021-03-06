/* LanguageTool, a natural language style checker
 * Copyright (C) 2020 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.TestTools;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class PunctuationMarkAtParagraphEnd2Test {
  
  @Test
  public void test() throws IOException {
    JLanguageTool lt = new JLanguageTool(TestTools.getDemoLanguage());
    for (Rule rule : lt.getAllRules()) {
      lt.disableRule(rule.getId());
    }
    PunctuationMarkAtParagraphEnd2 rule = new PunctuationMarkAtParagraphEnd2(TestTools.getEnglishMessages(), TestTools.getDemoLanguage());
    lt.addRule(rule);
    assertThat(lt.check("This is a test.").size(), is(0));
    assertThat(lt.check("This is a test").size(), is(0));  // too short
    assertThat(lt.check("This is a nice test").size(), is(0));
    assertThat(lt.check("This is a test.\n\nRegards,\nJim").size(), is(0));
    assertThat(lt.check("This is a test.\n\nRegards,\n\nJim").size(), is(0));
    assertThat(lt.check("This is a test.\n\nKind Regards,\nJim").size(), is(0));
    assertThat(lt.check("This is a test.\n\nKind Regards,\n\nJim").size(), is(0));
    assertThat(lt.check("This is a test.\n\nKind Regards,\n\nJim Tester").size(), is(0));
    assertThat(lt.check("This is a test.\n\nKind Regards,\n\nJim van Tester").size(), is(0));
  }

}
