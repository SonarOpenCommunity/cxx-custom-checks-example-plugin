package org.sonar.cxx.checks;

import com.sonar.cxx.sslr.api.AstNode;
import com.sonar.cxx.sslr.api.Grammar;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.cxx.parser.CxxGrammarImpl;
import org.sonar.cxx.squidbridge.annotations.ActivatedByDefault;
import org.sonar.cxx.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.cxx.squidbridge.checks.SquidCheck;
import org.sonar.cxx.tag.Tag;

// In case you are adding a .html description in resources, the .html file name should match the rule key.
// In this sample the name must be 'UsingNamespace.html'.
@Rule(
  key = "UsingNamespace",
   priority = Priority.BLOCKER,
   name = "Using namespace directives are not allowed",
   tags = {Tag.CONVENTION}
// second possibility to add a rule description:
//,description = "Using namespace directives are not allowed."
)
@SqaleConstantRemediation("5min")
@ActivatedByDefault
public class UsingNamespaceCheck extends SquidCheck<Grammar> {

  @Override
  public void init() {
    subscribeTo(CxxGrammarImpl.usingDirective);
  }

  @Override
  public void visitNode(AstNode node) {
    getContext().createLineViolation(this, "Using namespace are not allowed.", node);
  }

}
